package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.world.block.grower.TreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class Sapling extends SaplingBlock {

    public Sapling() {
        super(
                new TreeGrower(),
                Properties.copy(Blocks.OAK_SAPLING)
                        .mapColor(MapColor.COLOR_LIGHT_GREEN)
        );
    }

    @Override
    public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
        return groundState.is(BlockTags.DIRT);
    }

    @Override
    public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState groundState = worldIn.getBlockState(blockpos);
        return this.mayPlaceOn(groundState, worldIn, blockpos);
    }
}
