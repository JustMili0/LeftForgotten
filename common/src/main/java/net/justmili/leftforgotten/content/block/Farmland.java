package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.core.registries.BlockRegistry;
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
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import org.jetbrains.annotations.Nullable;

public class Farmland extends FarmBlock {
	public Farmland(Properties properties) {
		super(properties);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos())?
			BlockRegistry.DIRT.get().defaultBlockState() : super.getStateForPlacement(context);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!state.canSurvive(level, pos)) turnToOldDirt(null, state, level, pos);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int moisture = state.getValue(MOISTURE);
		if (!isNearWater(level, pos) && !level.isRainingAt(pos.above())) {
			if (moisture > 0) {
				level.setBlock(pos, state.setValue(MOISTURE, moisture - 1), UPDATE_CLIENTS);
			} else if (!shouldMaintainFarmland(level, pos)) {
				turnToOldDirt(null, state, level, pos);
			}
		} else if (moisture < 7) {
			level.setBlock(pos, state.setValue(MOISTURE, 7), UPDATE_CLIENTS);
		}
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		if (!level.isClientSide && level.random.nextFloat() < fallDistance - 0.5f && entity instanceof LivingEntity
			&& (entity instanceof Player || level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING))
			&& entity.getBbWidth() * entity.getBbWidth() * entity.getBbHeight() > 0.512f) {
			turnToOldDirt(entity, state, level, pos);
		}
		entity.causeFallDamage(fallDistance, 1f, entity.damageSources().fall());
	}

	private static void turnToOldDirt(@Nullable Entity entity, BlockState state, Level level, BlockPos pos) {
		var newState = pushEntitiesUp(state, BlockRegistry.DIRT.get().defaultBlockState(), level, pos);
		level.setBlockAndUpdate(pos, newState);
		level.gameEvent(GameEvent.BLOCK_CHANGE, pos, Context.of(entity, newState));
	}

	private static boolean shouldMaintainFarmland(BlockGetter getter, BlockPos pos) {
		return getter.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND);
	}

	private static boolean isNearWater(LevelReader level, BlockPos pos) {
		for (var blockPos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
			if (level.getFluidState(blockPos).is(FluidTags.WATER)) return true;
		}
		return false;
	}
}