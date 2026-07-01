package net.justmili.leftforgotten.libs.v1.utils.datagen.extensions;

import net.minecraft.world.level.block.Block;

public interface KnownBlocksLootProvider {
    Iterable<Block> getKnownBlocks();
}
