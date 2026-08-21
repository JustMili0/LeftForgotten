package net.justmili.leftforgotten.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
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

        public static void renderFlippedSprite(GuiGraphics graphics, TextureAtlasSprite atlasSprite,
                                               int x1, int y1, int width, int height) {
            int x2 = x1+width;
            int y2 = y1+height;
            float minU = atlasSprite.getU1();
            float maxU = atlasSprite.getU0();
            float minV = atlasSprite.getV0();
            float maxV = atlasSprite.getV1();

            RenderSystem.setShaderTexture(0, atlasSprite.atlasLocation());
            RenderSystem.setShader(GameRenderer::getPositionTexShader);

            var matrix4f = graphics.pose().last().pose();
            var builder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            vertex(matrix4f, builder, x1, y1, minU, minV);
            vertex(matrix4f, builder, x1, y2, minU, maxV);
            vertex(matrix4f, builder, x2, y2, maxU, maxV);
            vertex(matrix4f, builder, x2, y1, maxU, minV);

            BufferUploader.drawWithShader(builder.buildOrThrow());
        }

        static void vertex(Matrix4f matrix4f, BufferBuilder builder, int x, int y, float u, float v) {
            builder.addVertex(matrix4f, x, y, 0).setUv(u, v);
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

    public static class NeoForge {
        public static final int // Defined widths and heights (X-Y pos)
            playerHpH = 6,    // Player HP Y offset
            armorW = 101,     // Armor X offset
            armorH = 6,       // Armor Y offset
            airLvlW = 202,    // Air level X offset
            airLvlH = 3,      // Air level Y offset
            horseBar = 7,     // Horse bar
            mountHpH = 3,     // Mount HP Y offset
            mountHpW = 0,     // Mount HP X offset - useless, but I say let it stay just in case
            mountHpH_na = 9,  // Mount HP Y offset without Armor
            fullscreenOffset = 1; // Fullscreen accountability because Forge is weird

        public static int yOffset() {
            return (Common.hasSaddle() ? horseBar : 0) - fullscreenOffset;
        }
    }
}