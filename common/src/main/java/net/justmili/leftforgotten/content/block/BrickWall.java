
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.world.level.block.WallBlock;

public class BrickWall extends WallBlock {
	public BrickWall() {
		super(Properties.ofFullCopy(LFBlocks.BRICKS.get()).forceSolidOn());
	}
}