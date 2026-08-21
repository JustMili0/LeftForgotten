
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.BlockRegistry;
import net.minecraft.world.level.block.StairBlock;

public class MossyCobblestoneStairs extends StairBlock {
	public MossyCobblestoneStairs() {
		super(BlockRegistry.MOSSY_COBBLESTONE.get().defaultBlockState(), Properties.copy(BlockRegistry.MOSSY_COBBLESTONE.get()));
	}
}
