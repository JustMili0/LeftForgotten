
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.BlockRegistry;
import net.minecraft.world.level.block.WallBlock;

public class BrickWall extends WallBlock {
	public BrickWall() {
		super(Properties.ofFullCopy(BlockRegistry.BRICKS.get()).forceSolidOn());
	}
}