
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.BlockRegistry;
import net.minecraft.world.level.block.StairBlock;

public class CobblestoneStairs extends StairBlock {
	public CobblestoneStairs() {
		super(BlockRegistry.COBBLESTONE.get().defaultBlockState(), Properties.copy(BlockRegistry.COBBLESTONE.get()));
	}
}
