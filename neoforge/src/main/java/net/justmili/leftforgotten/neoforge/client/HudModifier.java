package net.justmili.leftforgotten.neoforge.client;

import dev.architectury.platform.Platform;
import mod.adrenix.nostalgic.tweak.config.CandyTweak;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.justmili.leftforgotten.registries.extra.LFResources;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import static net.justmili.leftforgotten.client.CommonHudModifier.Common.*;
import static net.justmili.leftforgotten.client.CommonHudModifier.NeoForge.*;

@EventBusSubscriber(value = Dist.CLIENT)
public class HudModifier {
    static final ResourceLocation
        ARMOR_EMPTY = ResourceUtil.asMinecraft("hud/armor_empty"),
        ARMOR_HALF = ResourceUtil.asMinecraft("hud/armor_half"),
        ARMOR_FULL = ResourceUtil.asMinecraft("hud/armor_full"),
        AIR = ResourceUtil.asMinecraft("hud/air"),
        AIR_BURST = ResourceUtil.asMinecraft("hud/air_bursting");

    @SubscribeEvent
    public static void onGuiOverlayPre(RenderGuiLayerEvent.Pre event) {
        if (!ClientUtil.inDimension(LFResources.ALPHA_MINECRAFT)) return;
        var minecraft = ClientUtil.client();
        var player = ClientUtil.player();
        if (player == null) return;

        int width = ClientUtil.width(), height = ClientUtil.height();
        var overlay = event.getLayer();
        var id = event.getName();
        var graphics = event.getGuiGraphics();
        var gui = ClientUtil.gui();
        var partTick = event.getPartialTick(); // NeoForge I swear to god-

        // Food disable
        if (id.equals(VanillaGuiLayers.FOOD_LEVEL)) event.setCanceled(true);
        // Experience disable
        if (id.equals(VanillaGuiLayers.EXPERIENCE_BAR)) event.setCanceled(true);
        if (id.equals(VanillaGuiLayers.EXPERIENCE_LEVEL)) event.setCanceled(true);

        // Armor move right and down, flip armor sprites
        if (id.equals(VanillaGuiLayers.ARMOR_LEVEL)) {
            event.setCanceled(true);
            if (ClientUtil.notSurvivalOrHideGui()) return;

            int armorValue = player.getArmorValue();
            for (int i = 1; armorValue > 0 && i < 20; i += 2) {
                var sprite = i < armorValue? ARMOR_FULL : i == armorValue? ARMOR_HALF : ARMOR_EMPTY;
                var atlasSprite = minecraft.getGuiSprites().getSprite(sprite);
                int origX = width / 2 - 91 + ((i - 1) / 2) * 8;
                int x1 = mirrorX(origX) + armorW;
                int y1 = height - 39 + armorH - yOffset();

                renderFlippedSprite(graphics, atlasSprite, x1, y1, 9, 9);
            }
        }
        // Player HP move down
        if (id.equals(VanillaGuiLayers.PLAYER_HEALTH)) {
            event.setCanceled(true);
            render(graphics, overlay, partTick, 0, playerHpH - yOffset());
        }
        // Air level move left and down, account for AbstractHorse jump bar when saddled
        if (id.equals(VanillaGuiLayers.AIR_LEVEL)) {
            event.setCanceled(true);
            if (ClientUtil.notSurvivalOrHideGui()) return;

            int air = Math.min(player.getAirSupply(), player.getMaxAirSupply());
            int maxAir = player.getMaxAirSupply();
            if (!player.isEyeInFluid(FluidTags.WATER) && air >= maxAir) return;

            int full = Mth.ceil((air - 2) * 10.0 / maxAir);
            int partial = Mth.ceil(air * 10.0 / maxAir) - full;
            int top = height - gui.rightHeight - airLvlH - yOffset() - extraHealthRowsOffset();
            int barEnd = width / 2 + 51;

            for (int i = 0; i < full + partial; ++i) {
                int origX = width / 2 - 9 - i * 8 - 9;
                int mirroredX = 2 * barEnd - 9 - origX - airLvlW;
                graphics.blitSprite(i < full? AIR : AIR_BURST, mirroredX, top, 9, 9);
            }
        }
        // Mount HP move down, account for AbstractHorse jump bar when saddled and Armor
        if (id.equals(VanillaGuiLayers.VEHICLE_HEALTH)) {
            event.setCanceled(true);
            if (ClientUtil.notSurvivalOrHideGui()) return; // Doesn't render in Creative

            if (player.getArmorValue() > 0) {
                // Armor on
                render(graphics, overlay, partTick, -mountHpW, -mountHpH - yOffset());
            } else {
                // Armor off
                render(graphics, overlay, partTick, -mountHpW, -mountHpH - yOffset() + mountHpH_na);
            }
        }

        // Get rid of NT's version overlay and stamina bar when in dimension
        if (Platform.isModLoaded("nostalgic_tweaks")) {
            if (ClientUtil.inDimension(LFResources.ALPHA_MINECRAFT)) {
                var namespace = id.getNamespace();
                var path = id.getPath().toLowerCase();
                if (!(namespace.equals("nostalgic_tweaks"))) return; // "Is it from NT?"
                if (path.contains("stamina")) event.setCanceled(true); // Get rid of the stamina bar
                // Get rid of NT's version overlay
                if (CandyTweak.OLD_VERSION_OVERLAY.get()) CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDiskThenSave(false);
            } else {
                if (!CandyTweak.OLD_VERSION_OVERLAY.get()) CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDiskThenSave(true);
            }
        }
    }

    private static void render(GuiGraphics graphics, LayeredDraw.Layer overlay, DeltaTracker partialTick, int x, int y) {
        graphics.pose().pushPose();
        graphics.pose().translate(x, y, 0);
        overlay.render(graphics, partialTick);
        graphics.pose().popPose();
    }
}