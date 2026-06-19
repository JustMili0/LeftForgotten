package net.justmili.leftforgotten.core.util;

import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class ResourceUtil {
    public static ResourceLocation parse(String modId, String path) {
        return new ResourceLocation(modId, path);
    }
    public static ResourceLocation asPath(String path) {
        return new ResourceLocation(path);
    }
    public static ResourceLocation asMinecraft(String path) {
        return new ResourceLocation("minecraft", path);
    }
    public static ResourceLocation asFabric(String path) {
        return new ResourceLocation("fabric", path);
    }
    public static ResourceLocation asQuilt(String path) {
        return new ResourceLocation("quilt", path);
    }
    public static ResourceLocation asForge(String path) {
        return new ResourceLocation("forge", path);
    }
    public static ResourceLocation asNeoForge(String path) {
        return new ResourceLocation("neoforge", path);
    }

    public static ResourceLocation asBlockPath(String path) {
        return new ResourceLocation("minecraft:block/"+path);
    }
    public static ResourceLocation asItemPath(String path) {
        return new ResourceLocation("minecraft:item/"+path);
    }

    // Generic
    public static ResourceLocation mapTextureTop(Block block) {
        return TextureMapping.getBlockTexture(block, "_top");
    }
    public static ResourceLocation mapTextureBottom(Block block) {
        return TextureMapping.getBlockTexture(block, "_bottom");
    }
    public static ResourceLocation mapTextureFront(Block block) {
        return TextureMapping.getBlockTexture(block, "_front");
    }
    public static ResourceLocation mapTextureLeft(Block block) {
        return TextureMapping.getBlockTexture(block, "_left");
    }
    public static ResourceLocation mapTextureRight(Block block) {
        return TextureMapping.getBlockTexture(block, "_right");
    }
    public static ResourceLocation mapTextureSide(Block block) {
        return TextureMapping.getBlockTexture(block, "_side");
    }
    // Chest texture suffixes
    public static ResourceLocation mapTextureFrontRight(Block block) {
        return TextureMapping.getBlockTexture(block, "_front_right");
    }
    public static ResourceLocation mapTextureFrontLeft(Block block) {
        return TextureMapping.getBlockTexture(block, "_front_left");
    }
    public static ResourceLocation mapTextureBackRight(Block block) {
        return TextureMapping.getBlockTexture(block, "_back_right");
    }
    public static ResourceLocation mapTextureBackLeft(Block block) {
        return TextureMapping.getBlockTexture(block, "_back_left");
    }

    public static ResourceLocation mapTextureFrontOn(Block block) {
        return TextureMapping.getBlockTexture(block, "_front_on");
    }
    public static ResourceLocation mapTextureOn(Block block) {
        return TextureMapping.getBlockTexture(block, "_on");
    }
    public static ResourceLocation mapTextureMoist(Block block) {
        return TextureMapping.getBlockTexture(block, "_moist");
    }
}
