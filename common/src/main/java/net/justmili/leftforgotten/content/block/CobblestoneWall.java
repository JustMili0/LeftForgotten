
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CobblestoneWall extends WallBlock {
	public CobblestoneWall() {
		super(Properties.ofFullCopy(LFBlocks.COBBLESTONE.get()).forceSolidOn());
	}
}