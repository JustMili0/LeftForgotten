
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.world.level.block.WallBlock;

public class CobblestoneWall extends WallBlock {
	public CobblestoneWall() {
		super(Properties.ofFullCopy(LFBlocks.COBBLESTONE.get()).forceSolidOn());
	}
}