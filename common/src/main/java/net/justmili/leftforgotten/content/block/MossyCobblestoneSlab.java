
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.minecraft.world.level.block.SlabBlock;

public class MossyCobblestoneSlab extends SlabBlock {
	public MossyCobblestoneSlab() {
		super(Properties.copy(BlockRegistry.MOSSY_COBBLESTONE.get()));
	}
}
