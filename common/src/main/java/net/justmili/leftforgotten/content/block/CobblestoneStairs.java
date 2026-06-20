
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CobblestoneStairs extends StairBlock {
	public CobblestoneStairs() {
		super(LFBlocks.COBBLESTONE.get().defaultBlockState(), Properties.copy(LFBlocks.COBBLESTONE.get()));
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return false;
	}

}
