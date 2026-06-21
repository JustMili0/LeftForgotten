
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.world.level.block.WallBlock;

public class MossyCobblestoneWall extends WallBlock {
	public MossyCobblestoneWall() {
		super(Properties.copy(LFBlocks.MOSSY_COBBLESTONE.get()).forceSolidOn());
	}
}