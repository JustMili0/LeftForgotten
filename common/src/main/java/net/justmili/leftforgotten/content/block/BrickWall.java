
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BrickWall extends WallBlock {
	public BrickWall() {
		super(Properties.ofFullCopy(LFBlocks.BRICKS.get()).forceSolidOn());
	}
}