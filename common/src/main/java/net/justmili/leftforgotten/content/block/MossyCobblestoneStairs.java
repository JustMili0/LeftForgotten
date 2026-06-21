
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MossyCobblestoneStairs extends StairBlock {
	public MossyCobblestoneStairs() {
		super(LFBlocks.MOSSY_COBBLESTONE.get().defaultBlockState(), Properties.copy(LFBlocks.MOSSY_COBBLESTONE.get()));
	}
}
