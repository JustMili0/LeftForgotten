
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.BlockRegistry;
import net.minecraft.world.level.block.StairBlock;

public class BrickStairs extends StairBlock {
	public BrickStairs() {
		super(BlockRegistry.BRICKS.get().defaultBlockState(), Properties.ofFullCopy(BlockRegistry.BRICKS.get()));
	}
}
