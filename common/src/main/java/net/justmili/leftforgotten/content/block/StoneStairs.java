
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.minecraft.world.level.block.StairBlock;

public class StoneStairs extends StairBlock {
	public StoneStairs() {
		super(BlockRegistry.STONE.get().defaultBlockState(), Properties.copy(BlockRegistry.STONE.get()));
	}
}
