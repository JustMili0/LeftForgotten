package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.block.dev.CommonBlock;
import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class Farmland extends Block {
	public static final IntegerProperty MOISTURE;
	protected static final VoxelShape SHAPE;
	public static final int MAX_MOISTURE = 7;

	public Farmland() {
		super(Properties.of().mapColor(MapColor.DIRT).sound(SoundType.GRAVEL).strength(1.5f, 6f).isViewBlocking(CommonBlock::always).isSuffocating(CommonBlock::always).randomTicks());
		this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, 0));
	}

	public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
		if (direction == Direction.UP && !blockState.canSurvive(levelAccessor, blockPos)) {
			levelAccessor.scheduleTick(blockPos, this, 1);
		}

		return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
	}

	public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
		BlockState blockState2 = levelReader.getBlockState(blockPos.above());
		return !blockState2.isSolid() || blockState2.getBlock() instanceof FenceGateBlock || blockState2.getBlock() instanceof MovingPistonBlock;
	}

	public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
		return !this.defaultBlockState().canSurvive(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos()) ? LFBlocks.DIRT.get().defaultBlockState() : super.getStateForPlacement(blockPlaceContext);
	}

	public boolean useShapeForLightOcclusion(BlockState blockState) {
		return true;
	}

	public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
		return SHAPE;
	}

	public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
		if (!blockState.canSurvive(serverLevel, blockPos)) {
			turnToDirt((Entity)null, blockState, serverLevel, blockPos);
		}

	}

	public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
		int i = blockState.getValue(MOISTURE);
		if (!isNearWater(serverLevel, blockPos) && !serverLevel.isRainingAt(blockPos.above())) {
			if (i > 0) {
				serverLevel.setBlock(blockPos, blockState.setValue(MOISTURE, i - 1), 2);
			} else if (!shouldMaintainFarmland(serverLevel, blockPos)) {
				turnToDirt(null, blockState, serverLevel, blockPos);
			}
		} else if (i < MAX_MOISTURE) {
			serverLevel.setBlock(blockPos, blockState.setValue(MOISTURE, MAX_MOISTURE), 2);
		}

	}

	public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float f) {
		if (!level.isClientSide && level.random.nextFloat() < f - 0.5F && entity instanceof LivingEntity && (entity instanceof Player || level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) && entity.getBbWidth() * entity.getBbWidth() * entity.getBbHeight() > 0.512F) {
			turnToDirt(entity, blockState, level, blockPos);
		}

		super.fallOn(level, blockState, blockPos, entity, f);
	}

	public static void turnToDirt(@Nullable Entity entity, BlockState blockState, Level level, BlockPos blockPos) {
		BlockState blockState2 = pushEntitiesUp(blockState, LFBlocks.DIRT.get().defaultBlockState(), level, blockPos);
		level.setBlockAndUpdate(blockPos, blockState2);
		level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, Context.of(entity, blockState2));
	}

	private static boolean shouldMaintainFarmland(BlockGetter blockGetter, BlockPos blockPos) {
		return blockGetter.getBlockState(blockPos.above()).is(BlockTags.MAINTAINS_FARMLAND);
	}

	private static boolean isNearWater(LevelReader levelReader, BlockPos blockPos) {
		for(BlockPos blockPos2 : BlockPos.betweenClosed(blockPos.offset(-4, 0, -4), blockPos.offset(4, 1, 4))) {
			if (levelReader.getFluidState(blockPos2).is(FluidTags.WATER)) {
				return true;
			}
		}

		return false;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(MOISTURE);
	}

	public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
		return false;
	}

	static {
		MOISTURE = BlockStateProperties.MOISTURE;
		SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 15.0F, 16.0F);
	}
}
