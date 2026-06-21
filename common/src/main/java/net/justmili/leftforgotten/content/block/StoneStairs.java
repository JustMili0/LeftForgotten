
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class StoneStairs extends StairBlock {
	public StoneStairs() {
		super(LFBlocks.STONE.get().defaultBlockState(), Properties.copy(LFBlocks.STONE.get()));
	}
}
