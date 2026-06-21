package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class Cactus extends CactusBlock {
    public Cactus() {
        super(Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(0.4F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        for (Direction direction : Plane.HORIZONTAL) {
            BlockState relative = level.getBlockState(pos.relative(direction));
            if (relative.isSolid() || level.getFluidState(pos.relative(direction)).is(FluidTags.LAVA)) return false;
        }

        BlockState below = level.getBlockState(pos.below());
        return (below.is(Blocks.CACTUS)
            || below.is(LFBlocks.CACTUS.get())
            || below.is(BlockTags.SAND))
            && !level.getBlockState(pos.above()).liquid();
    }
}