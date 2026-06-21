
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CobblestoneStairs extends StairBlock {
	public CobblestoneStairs() {
		super(LFBlocks.COBBLESTONE.get().defaultBlockState(), Properties.ofFullCopy(LFBlocks.COBBLESTONE.get()));
	}
}
