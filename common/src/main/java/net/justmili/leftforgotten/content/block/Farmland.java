package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.block.dev.CommonBlock;
import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

public class Farmland extends FarmBlock {
	public Farmland() {
		super(Properties.of().mapColor(MapColor.DIRT).sound(SoundType.GRAVEL).strength(1.5f, 6f)
			.isViewBlocking(CommonBlock::always).isSuffocating(CommonBlock::always).randomTicks());
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ?
			LFBlocks.DIRT.get().defaultBlockState() : super.getStateForPlacement(context);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!state.canSurvive(level, pos)) {
			turnToOldDirt(null, state, level, pos);
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int i = state.getValue(MOISTURE);
		if (!isNearWater(level, pos) && !level.isRainingAt(pos.above())) {
			if (i > 0) {
				level.setBlock(pos, state.setValue(MOISTURE, i - 1), 2);
			} else if (!shouldMaintainFarmland(level, pos)) {
				turnToOldDirt(null, state, level, pos);
			}
		} else if (i < 7) {
			level.setBlock(pos, state.setValue(MOISTURE, 7), 2);
		}
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if (!level.isClientSide && level.random.nextFloat() < fallDistance - 0.5F && entity instanceof LivingEntity
			&& (entity instanceof Player || level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING))
			&& entity.getBbWidth() * entity.getBbWidth() * entity.getBbHeight() > 0.512F) {
			turnToOldDirt(entity, state, level, pos);
		}
		super.fallOn(level, state, pos, entity, fallDistance);
	}

	private static void turnToOldDirt(@Nullable Entity entity, BlockState state, Level level, BlockPos pos) {
		BlockState newState = pushEntitiesUp(state, LFBlocks.DIRT.get().defaultBlockState(), level, pos);
		level.setBlockAndUpdate(pos, newState);
		level.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(entity, newState));
	}

	private static boolean shouldMaintainFarmland(BlockGetter getter, BlockPos pos) {
		return getter.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND);
	}

	private static boolean isNearWater(LevelReader level, BlockPos pos) {
		for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
			if (level.getFluidState(blockPos).is(FluidTags.WATER)) return true;
		}
		return false;
	}
}
