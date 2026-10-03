package net.justmili.leftforgotten.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.libs.v1.utils.common.Maths;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class CommonVersionOverlay {
    private static final String BASE_TEXT = "Minecraft Alpha v1.1.2_01";
    private static final int MIN_DELAY = Maths.toMinutesInTicks(2);
    private static final int MAX_DELAY = Maths.toMinutesInTicks(5);
    private static String currentText = BASE_TEXT;
    private static int flashTickDelay = rollDelay();
    private static int flashForTicks = 6;

    private static int rollDelay() {
        return Maths.randomInt(MIN_DELAY, MAX_DELAY);
    }

    // Common tick text
    public static void onClientTick(Minecraft client) {
        if (!Versions.hadVersionOverlay(client.level)) {
            if (flashForTicks > 0) { // Reset if flash is interrupted
                flashForTicks = 0;
                currentText = BASE_TEXT;
                flashTickDelay = rollDelay();
            }
            return;
        }

        if (flashForTicks > 0) {
            // Currently flashing
            if (--flashForTicks == 0) {
                currentText = BASE_TEXT;
                flashTickDelay = rollDelay();
            }

        } else if (--flashTickDelay <= 0) {
            // Flash current Minecraft version name
            currentText = "Minecraft " + SharedConstants.getCurrentVersion().getId();
            flashForTicks = Maths.randomInt(2, 8);
        }
    }

    // Fabric/Forger render
    public static void render(GuiGraphics graphics) {
        var pose = graphics.pose();
        var font = ClientUtil.font();
        int userScale = Maths.round(8f / font.lineHeight);
        int xy = Maths.round(2f / userScale);

        pose.pushPose();
        pose.scale(userScale, userScale, 1f);
        graphics.drawString(font, Component.literal(currentText), xy, xy, 0xFFFFFF, true);
        pose.popPose();
    }
}