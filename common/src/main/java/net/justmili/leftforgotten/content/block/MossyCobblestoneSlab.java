
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.BlockRegistry;
import net.minecraft.world.level.block.SlabBlock;

public class MossyCobblestoneSlab extends SlabBlock {
	public MossyCobblestoneSlab() {
		super(Properties.ofFullCopy(BlockRegistry.MOSSY_COBBLESTONE.get()));
	}
}
