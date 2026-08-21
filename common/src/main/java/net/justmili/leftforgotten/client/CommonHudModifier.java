package net.justmili.leftforgotten.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.joml.Matrix4f;

import static net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil.*;

public class CommonHudModifier {
    public static class Common {
        static boolean hasSaddle() {
            return player().getVehicle() instanceof AbstractHorse horse && horse.isSaddled();
        }

        public static int mirrorX(int x) {
            return 2 * (width() / 2-91)+72-x;
        }

        public static void renderFlippedBlit(GuiGraphics graphics, ResourceLocation texture,
                                             int x1, int y1, int width, int height, int uOffset, int vOffset) {
            int x2 = x1+width;
            int y2 = y1+height;
            float minU = (uOffset+width) / 256f;
            float maxU = (uOffset+0.0f) / 256f;
            float minV = vOffset / 256f;
            float maxV = (vOffset+height) / 256f;

            RenderSystem.setShaderTexture(0, texture);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);

            var matrix4f = graphics.pose().last().pose();
            var builder = Tesselator.getInstance().getBuilder();
            builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            vertex(matrix4f, builder, x1, y1, minU, minV);
            vertex(matrix4f, builder, x1, y2, minU, maxV);
            vertex(matrix4f, builder, x2, y2, maxU, maxV);
            vertex(matrix4f, builder, x2, y1, maxU, minV);

            BufferUploader.drawWithShader(builder.end());
        }

        static void vertex(Matrix4f matrix4f, BufferBuilder builder, int x, int y, float u, float v) {
            builder.vertex(matrix4f, x, y, 0).uv(u, v).endVertex();
        }

        public static int extraHealthRowsOffset() {
            var player = player();
            if (player == null) return 0;

            float maxHealth = Math.max(player.getMaxHealth(), player.getHealth());
            int absorption = Mth.ceil(player.getAbsorptionAmount());
            int rows = Mth.ceil((maxHealth + absorption) / 2.0F / 10.0F);
            if (rows <= 1) return 0;

            int rowHeight = Math.max(10 - (rows - 2), 3);
            return (rows - 1) * rowHeight;
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
            int creativeOffset = ClientUtil.isCreative()? -9 : 0;
            return Common.hasSaddle()? horseBar + creativeOffset : creativeOffset;
        }
        public static int mountHpOffset() {
            return height() - 39 - yOffset() - mountHpH;
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