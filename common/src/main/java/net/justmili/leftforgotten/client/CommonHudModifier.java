package net.justmili.leftforgotten.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import org.joml.Matrix4f;

import static net.justmili.leftforgotten.core.util.ClientUtil.*;

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

        public static void renderFlippedSprite(GuiGraphics graphics, TextureAtlasSprite atlasSprite,
                                               int x1, int y1, int width, int height) {
            int x2 = x1+width,
                y2 = y1+height,
                blitOffset = 0;
            float minU = atlasSprite.getU1(),
                maxU = atlasSprite.getU0(),
                minV = atlasSprite.getV0(),
                maxV = atlasSprite.getV1();

            RenderSystem.setShaderTexture(0, atlasSprite.atlasLocation());
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            Matrix4f matrix4f = graphics.pose().last().pose();
            BufferBuilder bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferBuilder.addVertex(matrix4f, x1, y1, blitOffset).setUv(minU, minV);
            bufferBuilder.addVertex(matrix4f, x1, y2, blitOffset).setUv(minU, maxV);
            bufferBuilder.addVertex(matrix4f, x2, y2, blitOffset).setUv(maxU, maxV);
            bufferBuilder.addVertex(matrix4f, x2, y1, blitOffset).setUv(maxU, minV);
            BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());
        }

        public static int extraHealthRowsOffset() {
            Player player = getPlayer();
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
            int creativeOffset = Common.inCreative()? -9 : 0;
            return Common.hasSaddle()? horseBar + creativeOffset : creativeOffset;
        }
        public static int mountHpOffset() {
            return getHeight() - 39 - yOffset() - mountHpH;
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
