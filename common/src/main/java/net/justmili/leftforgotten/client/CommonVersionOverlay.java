package net.justmili.leftforgotten.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.core.registries.LevelRegistry;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.libs.v1.utils.common.Maths;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

@Environment(EnvType.CLIENT)
public class CommonVersionOverlay {
    private static final int MIN_DELAY = Maths.toMinutesInTicks(2);
    private static final int MAX_DELAY = Maths.toMinutesInTicks(5);
    private static int flashTickDelay = rollDelay();
    private static int flashForTicks = 6; // First flash always 6 ticks
    private static ResourceKey<Level> lastKnownLevel;
    private static final String versionText = "Minecraft " + SharedConstants.getCurrentVersion().getId();
    private static String overlayText = "";
    private static boolean shouldShowOverlay;

    // Fabric/Forge render
    public static void renderTextOverlay(GuiGraphics graphics) {
        if (overlayText.isEmpty()) return;

        var font = ClientUtil.font();
        int scale = Maths.round(8f / font.lineHeight); // Get proper scale
        int xy = Maths.round(2f / scale); // X, Y offset from screen corners

        // Render
        var pose = graphics.pose();
        pose.pushPose();
        pose.scale(scale, scale, 1f);
        graphics.drawString(font, overlayText, xy, xy, 0xFFFFFF, true);
        pose.popPose();
    }

    // Common tick text
    public static void updateTextOverlay(Minecraft client) {
        var level = client.level;
        if (level == null) {
            lastKnownLevel = null; // Force a refresh on next join
            overlayText = "";
            return;
        }

        var dim = level.dimension();
        if (dim != lastKnownLevel) { // Dimension/world changed, refresh everything once
            lastKnownLevel = dim;
            shouldShowOverlay = Versions.hadVersionOverlay(level);
            resetFlash(level); // Also covers flash being interrupted
        }
        if (!shouldShowOverlay) return;
        if (flashForTicks > 0) {
            // Currently flashing
            if (--flashForTicks == 0) resetFlash(level);

        } else if (--flashTickDelay <= 0) {
            // Flash current Minecraft version name
            overlayText = versionText;
            flashForTicks = Maths.randomInt(2, 6);
        }
    }

    private static String getTextOverlay(Level level) {
        var dim = level.dimension();
        if (dim == LevelRegistry.BETA) return "Minecraft Beta 1.8.1";
        if (dim == LevelRegistry.ALPHA) return "Minecraft Alpha v1.1.2_01";
        if (dim == LevelRegistry.INFDEV) return "Infdev v20100227";
        if (dim == LevelRegistry.INDEV) return "0.31";
        if (dim == LevelRegistry.CLASSIC) return "0.30";
        return ""; // Preclassic and other
    }

    private static void resetFlash(Level level) {
        flashForTicks = 0;
        overlayText = getTextOverlay(level);
        flashTickDelay = rollDelay();
    }

    private static int rollDelay() {
        return Maths.randomInt(MIN_DELAY, MAX_DELAY);
    }
}