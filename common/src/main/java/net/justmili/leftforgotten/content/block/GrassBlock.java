package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class GrassBlock extends Block {
    public GrassBlock() {
        super(Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).sound(SoundType.GRASS).strength(0.6f).randomTicks());
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canSurvive(level, pos)) {
            if (!level.isClientSide()) {
                level.setBlock(pos, LFBlocks.DIRT.get().defaultBlockState(), 3);
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!player.getMainHandItem().is(ItemTags.HOES)) return InteractionResult.FAIL;

        BlockState aboveState = level.getBlockState(pos.above());
        if (!aboveState.isAir()) return InteractionResult.FAIL;

        level.setBlock(BlockPos.containing(pos.getX(), pos.getY(), pos.getZ()), LFBlocks.FARMLAND.get().defaultBlockState(), 3);

        float pitch = 0.9f + level.getRandom().nextFloat() * 0.2f;
        level.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0f, pitch);
        player.getMainHandItem().hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

        getSeeds(level, pos, player);

        return InteractionResult.SUCCESS;
    }

    private static boolean canSurvive(LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        BlockState aboveState = level.getBlockState(above);
        if (aboveState.is(LFBlocks.LEAVES.get())) {
            return true;
        }
        return aboveState.getLightBlock(level, above) <= 0;
    }

    private static void getSeeds(LevelReader level, BlockPos pos, Player player) {
        if (player == null) return;
        if (!player.getMainHandItem().is(ItemTags.HOES)) return;
        if (!(Math.random() < 0.125)) return;
        if (level instanceof ServerLevel serverLevel) {
            ItemEntity seedsItem = new ItemEntity(serverLevel, pos.getX(), (pos.getY() + 1.1), pos.getZ(), new ItemStack(Items.WHEAT_SEEDS));
            seedsItem.setPickUpDelay(15);
            serverLevel.addFreshEntity(seedsItem);
        }
    }
}