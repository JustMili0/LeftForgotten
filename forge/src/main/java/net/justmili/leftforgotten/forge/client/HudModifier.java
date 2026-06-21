package net.justmili.leftforgotten.forge.client;

import dev.architectury.platform.Platform;
import mod.adrenix.nostalgic.tweak.config.CandyTweak;
import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.core.util.ResourceUtil;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.justmili.leftforgotten.client.CommonHudModifier.Common.mirrorX;
import static net.justmili.leftforgotten.client.CommonHudModifier.Common.renderFlippedBlit;
import static net.justmili.leftforgotten.client.CommonHudModifier.Forge.*;
import static net.justmili.leftforgotten.core.util.ClientUtil.*;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HudModifier {
    private static final ResourceLocation GUI_ICONS_LOCATION = ResourceUtil.asPath("textures/gui/icons.png");

    @SubscribeEvent
    public static void onGuiOverlayPre(RenderGuiOverlayEvent.Pre event) {
        if (getPlayer() == null) return;

        NamedGuiOverlay getOverlay = event.getOverlay();
        IGuiOverlay overlay = getOverlay.overlay();
        ResourceLocation id = getOverlay.id();
        GuiGraphics graphics = event.getGuiGraphics();
        float partTick = event.getPartialTick();

        if (!inDimension(LFResources.Levels.ALPHA_MINECRAFT)) return;

        // Food disable
        if (id.equals(VanillaGuiOverlay.FOOD_LEVEL.id())) event.setCanceled(true);
        // Experience disable
        if (id.equals(VanillaGuiOverlay.EXPERIENCE_BAR.id())) event.setCanceled(true);

        // Armor move right and down, flip armor sprites
        if (id.equals(VanillaGuiOverlay.ARMOR_LEVEL.id())) {
            event.setCanceled(true);

            if (CommonClient.nonSurvivalGamemode()) return;

            int level = getPlayer().getArmorValue();
            for (int i = 1; level > 0 && i < 20; i += 2) {
                int uOffset = i < level? 34 : i == level? 25 : 16,
                    origX = getWidth() / 2 - 91 + ((i - 1) / 2) * 8,
                    x1 = mirrorX(origX) + armorW,
                    y1 = getHeight() - 39 + armorH - yOffset();

                renderFlippedBlit(graphics, GUI_ICONS_LOCATION, x1, y1, 9, 9, uOffset, 9);
            }
        }
        // Player HP move down
        if (id.equals(VanillaGuiOverlay.PLAYER_HEALTH.id())) {
            event.setCanceled(true);
            overlay.render((ForgeGui) minecraft.gui, graphics, partTick, getWidth(), getHeight() + playerHpH - yOffset());
        }
        // Air level move left and down, account for AbstractHorse jump bar when saddled
        if (id.equals(VanillaGuiOverlay.AIR_LEVEL.id())) {
            event.setCanceled(true);
            if (CommonClient.nonSurvivalGamemode()) return;

            int air = Math.min(getPlayer().getAirSupply(), getPlayer().getMaxAirSupply()),
                maxAir = getPlayer().getMaxAirSupply();
            if (!getPlayer().isEyeInFluid(FluidTags.WATER) && air >= maxAir) return;

            int full = Mth.ceil((air - 2) * 10.0 / maxAir),
                partial = Mth.ceil(air * 10.0 / maxAir) - full,
                rh = ((ForgeGui) minecraft.gui).rightHeight,
                top = getHeight() - rh - airLvlH - yOffset(),
                barEnd = getWidth() / 2 + 51;

            for (int i = 0; i < full + partial; ++i) {
                int origX = getWidth() / 2 - 9 - i * 8 - 9,
                    mirroredX = 2 * barEnd - 9 - origX - airLvlW;
                graphics.blit(GUI_ICONS_LOCATION, mirroredX, top, (i < full? 16 : 25), 18, 9, 9);
            }
        }
        // Mount HP move down, account for AbstractHorse jump bar when saddled and Armor
        if (id.equals(VanillaGuiOverlay.MOUNT_HEALTH.id())) {
            event.setCanceled(true);
            if (CommonClient.nonSurvivalGamemode()) return; // Doesn't render in Creative

            if (getPlayer().getArmorValue() > 0) {
                // Armor on
                overlay.render((ForgeGui) minecraft.gui, graphics, partTick, getWidth() - mountHpW, getHeight() - mountHpH - yOffset());
            } else {
                // Armor off
                overlay.render((ForgeGui) minecraft.gui, graphics, partTick, getWidth() - mountHpW, getHeight() - mountHpH - yOffset() + mountHpH_na);
            }
        }

        // Get rid of NT's version overlay and stamina bar when in dimension
        if (Platform.isModLoaded("nostalgic_tweaks")) {
            if (inDimension(LFResources.Levels.ALPHA_MINECRAFT)) {
                String ns = id.getNamespace(),
                    path = id.getPath().toLowerCase();
                if (!("nostalgic_tweaks".equals(ns))) return;
                if (path.contains("stamina")) event.setCanceled(true);
                // Get rid of NT's version overlay
                if (CandyTweak.OLD_VERSION_OVERLAY.get()) CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDiskThenSave(false);
            } else {
                if (!CandyTweak.OLD_VERSION_OVERLAY.get()) CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDiskThenSave(true);
            }
        }
    }
}