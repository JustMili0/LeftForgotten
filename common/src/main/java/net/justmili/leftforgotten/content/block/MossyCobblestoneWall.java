
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.minecraft.world.level.block.WallBlock;

public class MossyCobblestoneWall extends WallBlock {
	public MossyCobblestoneWall() {
		super(Properties.ofFullCopy(BlockRegistry.MOSSY_COBBLESTONE.get()).forceSolidOn());
	}
}