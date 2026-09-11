package net.justmili.leftforgotten.content.block.dev;

import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

public class FeatureVoid extends Block {
    public FeatureVoid() {
        super(Properties.of().replaceable().noCollission().noLootTable().noParticlesOnBreak().pushReaction(PushReaction.DESTROY));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }
    @Override
    public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!level.isClientSide()) {
            level.scheduleTick(pos, this, 1);
        }
    }

    /** DEV NOTES (Singleplayer)
     * tick is server but is fucked and just doesn't wanna work
     * animateTick is client and somehow makes this whole thing work
     * make it make sense
     *
     * DEV NOTES (Multiplayer)
     * Nevermind, animateTick works fine when running on a server
     */
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        BlockState above = level.getBlockState(pos.above());
        boolean hasLeavesAbove = above.is(BlockRegistry.LEAVES.get());

        if (!hasLeavesAbove) {
            clearAtAndBelow(level, pos);
            return;
        }

        clearAtAndBelow(level, pos);

        int roll = random.nextInt(100);
        if (roll < 1) {
            level.setBlock(pos, BlockRegistry.WOOD.get().defaultBlockState(), 3);
            level.setBlock(pos.below(), BlockRegistry.WOOD.get().defaultBlockState(), 3);
        } else if (roll < 13) {
            level.setBlock(pos, BlockRegistry.WOOD.get().defaultBlockState(), 3);
            level.setBlock(pos.below(), BlockRegistry.WOOD.get().defaultBlockState(), 3);
        } else if (roll < 38) {
            level.setBlock(pos, BlockRegistry.WOOD.get().defaultBlockState(), 3);
        }
    }

    private void clearAtAndBelow(Level level, BlockPos pos) {
        BlockPos current = pos;
        while (true) {
            BlockState state = level.getBlockState(current);
            if (state.is(BlockRegistry.DIRT.get()) || state.is(BlockRegistry.GRASS_BLOCK.get())) break;
            if (state.isAir()) break;
            if (state.is(this)) {
                level.setBlock(current, Blocks.AIR.defaultBlockState(), 3);
            }
            current = current.below();
        }
    }
}