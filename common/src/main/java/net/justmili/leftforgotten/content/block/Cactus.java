package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;

public class Cactus extends CactusBlock {

    public Cactus(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        for (Direction direction : Plane.HORIZONTAL) {
            var relative = level.getBlockState(pos.relative(direction));
            if (relative.isSolid() || level.getFluidState(pos.relative(direction)).is(FluidTags.LAVA)) return false;
        }

        var below = level.getBlockState(pos.below());
        return (below.is(Blocks.CACTUS)
            || below.is(BlockRegistry.CACTUS.get())
            || below.is(BlockTags.SAND))
            && !level.getBlockState(pos.above()).liquid();
    }
}