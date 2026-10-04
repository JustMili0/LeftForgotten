package net.justmili.leftforgotten.content.block.dev;

import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrierBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FeatureVoid extends BarrierBlock {
    private final float[] CHANCES = new float[]{0.5f, 0.3f, 0.2f};

    public FeatureVoid(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!level.isClientSide()) level.scheduleTick(pos, state.getBlock(), 1);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if (!level.isClientSide()) level.scheduleTick(pos, state.getBlock(), 1);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.getBlockState(pos.above()).is(BlockRegistry.LEAVES.get())) return;

        var current = pos;
        for (float chance : CHANCES) {
            if (!level.getBlockState(current).is(this)) break;
            if (random.nextFloat() >= chance) break;
            level.setBlockAndUpdate(current, BlockRegistry.WOOD.get().defaultBlockState());
            current = current.below();
        }
        clearAtAndBelow(level, current);

        level.scheduleTick(pos, state.getBlock(), 1);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        var player = ClientUtil.player();
        if (player == null || !player.isCreative() || !player.isHolding(this.asItem())) return;

        level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK_MARKER, state), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0, 0);
    }

    private void clearAtAndBelow(Level level, BlockPos pos) {
        var current = pos;
        while (true) {
            var state = level.getBlockState(current);
            if (!state.is(this)) break;
            if (state.is(this)) level.setBlockAndUpdate(current, Blocks.AIR.defaultBlockState());
            current = current.below();
        }
    }
}