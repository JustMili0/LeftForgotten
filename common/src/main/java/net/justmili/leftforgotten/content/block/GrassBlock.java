package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.justmili.leftforgotten.libs.v1.utils.common.Maths;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class GrassBlock extends OldTillableBlock {

    public GrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canSurvive(level, pos)) {
            level.setBlockAndUpdate(pos, BlockRegistry.DIRT.get().defaultBlockState());
            return;
        }

        var grass = defaultBlockState();
        for (int i = 0; i < 4; i++) {
            var target = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
            if (level.getBlockState(target).is(BlockRegistry.DIRT.get()) && canSurvive(level, target)) level.setBlockAndUpdate(target, grass);
        }
    }

    @Override
    protected void onTilled(ServerLevel level, BlockPos pos, Player player) {
        if (!Maths.chance(0.125f)) return;
        var seeds = new ItemEntity(level, pos.getX(), pos.getY() + 1.1, pos.getZ(), new ItemStack(Items.WHEAT_SEEDS));
        seeds.setPickUpDelay(15);
        level.addFreshEntity(seeds);
    }

    static boolean canSurvive(LevelReader level, BlockPos pos) {
        var above = pos.above();
        var aboveState = level.getBlockState(above);
        if (aboveState.is(BlockRegistry.LEAVES.get())) return true;
        return aboveState.getLightBlock(level, above) <= 0;
    }
}