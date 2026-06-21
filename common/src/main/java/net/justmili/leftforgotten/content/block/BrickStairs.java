
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.world.level.block.StairBlock;

public class BrickStairs extends StairBlock {
	public BrickStairs() {
		super(LFBlocks.BRICKS.get().defaultBlockState(), Properties.ofFullCopy(LFBlocks.BRICKS.get()));
	}
}
