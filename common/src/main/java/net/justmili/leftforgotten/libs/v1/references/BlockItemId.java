package net.justmili.leftforgotten.libs.v1.references;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public record BlockItemId(ResourceKey<Block> block, ResourceKey<Item> item) {
    // Copy of Minecraft 26.2 class BlockItemId for earlier Minecraft versions
    public static BlockItemId create(ResourceLocation blockId, ResourceLocation itemId) {
        return new BlockItemId(ResourceKey.create(Registries.BLOCK, blockId), ResourceKey.create(Registries.ITEM, itemId));
    }
}