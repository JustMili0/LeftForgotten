
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.world.level.block.StairBlock;

public class CobblestoneStairs extends StairBlock {
	public CobblestoneStairs() {
		super(LFBlocks.COBBLESTONE.get().defaultBlockState(), Properties.copy(LFBlocks.COBBLESTONE.get()));
	}
}
