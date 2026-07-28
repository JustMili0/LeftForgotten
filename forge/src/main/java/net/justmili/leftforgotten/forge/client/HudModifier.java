package net.justmili.leftforgotten.forge.client;

import dev.architectury.platform.Platform;
import mod.adrenix.nostalgic.tweak.config.CandyTweak;
import net.justmili.leftforgotten.libs.v1.utils.ClientUtil;
import net.justmili.leftforgotten.libs.v1.utils.ResourceUtil;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.justmili.leftforgotten.client.CommonHudModifier.Common.*;
import static net.justmili.leftforgotten.client.CommonHudModifier.Forge.*;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HudModifier {
    private static final ResourceLocation GUI_ICONS_LOCATION = ResourceUtil.asPath("textures/gui/icons.png");

    @SubscribeEvent
    public static void onGuiOverlayPre(RenderGuiOverlayEvent.Pre event) {
        if (!ClientUtil.inDimension(LFResources.ALPHA_MINECRAFT)) return;
        var player = ClientUtil.getPlayer();
        if (player == null) return;

        int width = ClientUtil.getWidth(), height = ClientUtil.getHeight();
        var getOverlay = event.getOverlay();
        var overlay = getOverlay.overlay();
        var id = getOverlay.id();
        var graphics = event.getGuiGraphics();
        var gui = (ForgeGui) ClientUtil.client.gui; // Forge, I fucking hate you :3
        float partTick = event.getPartialTick();

        // Food disable
        if (id.equals(VanillaGuiOverlay.FOOD_LEVEL.id())) event.setCanceled(true);
        // Experience disable
        if (id.equals(VanillaGuiOverlay.EXPERIENCE_BAR.id())) event.setCanceled(true);
        // EXP level renders with EXP bar on 1.20.1

        // Armor move right and down, flip armor sprites
        if (id.equals(VanillaGuiOverlay.ARMOR_LEVEL.id())) {
            event.setCanceled(true);

            if (ClientUtil.notSurvivalOrHideGui()) return;

            int level = player.getArmorValue();
            for (int i = 1; level > 0 && i < 20; i += 2) {
                int uOffset = i < level? 34 : i == level? 25 : 16,
                    origX = width / 2 - 91 + ((i - 1) / 2) * 8,
                    x1 = mirrorX(origX) + armorW,
                    y1 = height - 39 + armorH - yOffset();

                renderFlippedBlit(graphics, GUI_ICONS_LOCATION, x1, y1, 9, 9, uOffset, 9);
            }
        }
        // Player HP move down
        if (id.equals(VanillaGuiOverlay.PLAYER_HEALTH.id())) {
            event.setCanceled(true);
            overlay.render(gui, graphics, partTick, width, height + playerHpH - yOffset());
        }
        // Air level move left and down, account for AbstractHorse jump bar when saddled
        if (id.equals(VanillaGuiOverlay.AIR_LEVEL.id())) {
            event.setCanceled(true);
            if (ClientUtil.notSurvivalOrHideGui()) return;

            int air = Math.min(player.getAirSupply(), player.getMaxAirSupply()),
                maxAir = player.getMaxAirSupply();
            if (!player.isEyeInFluid(FluidTags.WATER) && air >= maxAir) return;

            int full = Mth.ceil((air - 2) * 10.0 / maxAir),
                partial = Mth.ceil(air * 10.0 / maxAir) - full,
                rh = gui.rightHeight,
                top = height - rh - airLvlH - yOffset() - extraHealthRowsOffset(),
                barEnd = width / 2 + 51;

            for (int i = 0; i < full + partial; ++i) {
                int origX = width / 2 - 9 - i * 8 - 9,
                    mirroredX = 2 * barEnd - 9 - origX - airLvlW;
                graphics.blit(GUI_ICONS_LOCATION, mirroredX, top, (i < full? 16 : 25), 18, 9, 9);
            }
        }
        // Mount HP move down, account for AbstractHorse jump bar when saddled and Armor
        if (id.equals(VanillaGuiOverlay.MOUNT_HEALTH.id())) {
            event.setCanceled(true);
            if (ClientUtil.notSurvivalOrHideGui()) return; // Doesn't render in Creative

            if (player.getArmorValue() > 0) {
                // Armor on
                overlay.render(gui, graphics, partTick, width - mountHpW, height - mountHpH - yOffset());
            } else {
                // Armor off
                overlay.render(gui, graphics, partTick, width - mountHpW, height - mountHpH - yOffset() + mountHpH_na);
            }
        }

        // Get rid of NT's version overlay and stamina bar when in dimension
        if (Platform.isModLoaded("nostalgic_tweaks")) {
            if (ClientUtil.inDimension(LFResources.ALPHA_MINECRAFT)) {
                var namespace = id.getNamespace();
                var path = id.getPath().toLowerCase();
                if (!(namespace.equals("nostalgic_tweaks"))) return;
                if (path.contains("stamina")) event.setCanceled(true);
                // Get rid of NT's version overlay
                if (CandyTweak.OLD_VERSION_OVERLAY.get()) CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDiskThenSave(false);
            } else {
                if (!CandyTweak.OLD_VERSION_OVERLAY.get()) CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDiskThenSave(true);
            }
        }
    }
}