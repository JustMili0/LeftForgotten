
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.block.dev.CommonBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Glass extends GlassBlock {
	public Glass() {
        super(Properties
                .of().mapColor(MapColor.NONE)
                .sound(SoundType.GLASS)
                .strength(0.3f)
                .noOcclusion()
                .isRedstoneConductor(CommonBlock::never)
                .isSuffocating(CommonBlock::never)
                .isViewBlocking(CommonBlock::never));
    }

	@Override
	public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.getBlock() == this || super.skipRendering(state, adjacentBlockState, side);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}
}
