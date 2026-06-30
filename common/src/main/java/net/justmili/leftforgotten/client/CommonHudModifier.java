package net.justmili.leftforgotten.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.joml.Matrix4f;

import static net.justmili.leftforgotten.libs.v1.utils.ClientUtil.*;

public class CommonHudModifier {
    public static class Common {
        private static boolean hasSaddle() {
            return getPlayer().getVehicle() instanceof AbstractHorse horse && horse.isSaddled();
        }
        private static boolean inCreative() {
            return getPlayer().isCreative();
        }

        public static int mirrorX(int x) {
            return 2 * (getWidth() / 2-91)+72-x;
        }

        public static void renderFlippedBlit(GuiGraphics graphics, ResourceLocation texture,
                                             int x1, int y1, int width, int height, int uOffset, int vOffset) {
            int x2 = x1+width,
                y2 = y1+height,
                blitOffset = 0;
            float minU = (uOffset+width) / 256f,
                maxU = (uOffset+0.0f) / 256f,
                minV = vOffset / 256f,
                maxV = (vOffset+height) / 256f;

            RenderSystem.setShaderTexture(0, texture);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            Matrix4f matrix4f = graphics.pose().last().pose();
            BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferBuilder.vertex(matrix4f, x1, y1, blitOffset).uv(minU, minV).endVertex();
            bufferBuilder.vertex(matrix4f, x1, y2, blitOffset).uv(minU, maxV).endVertex();
            bufferBuilder.vertex(matrix4f, x2, y2, blitOffset).uv(maxU, maxV).endVertex();
            bufferBuilder.vertex(matrix4f, x2, y1, blitOffset).uv(maxU, minV).endVertex();
            BufferUploader.drawWithShader(bufferBuilder.end());
        }
    }

    public static class Fabric {
        public static final int // Defined widths and heights (X-Y pos)
            playerHpH = 7,    // Player HP Y offset
            armorW = 101,     // Armor X offset
            armorH = 17,      // Armor Y offset
            airLvlW = 102,    // Air level X offset
            airLvlH = 2,      // Air level Y offset
            horseBar = 7,     // Horse bar
            mountHpH = 2,     // Mount HP Y offset
            mountHpH_na = 7;  // Mount HP Y offset without Armor

        public static int yOffset() { // Account for horse bar and Creative, Fabric doesn't need to account for fullscreen
            int creativeOffset = Common.inCreative()? -9 : 0;
            return Common.hasSaddle()? horseBar + creativeOffset : creativeOffset;
        }
        public static int mountHpOffset() {
            return getHeight() - 39 - yOffset() - mountHpH;
        }
    }

    public static class Forge {
        public static final int
            playerHpH = 6,
            armorW = 101,
            armorH = 6,
            airLvlW = 202,
            airLvlH = 3,
            horseBar = 7,
            mountHpH = 3,
            mountHpW = 0,
            mountHpH_na = 9,
            fullscreenOffset = 1;

        public static int yOffset() {
            return (Common.hasSaddle() ? horseBar : 0) - fullscreenOffset;
        }
    }
}