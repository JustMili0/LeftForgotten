package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
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
                level.setBlock(pos, BlockRegistry.DIRT.get().defaultBlockState(), 3);
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        var held = player.getMainHandItem();
        if (!held.is(ItemTags.HOES)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        var aboveState = level.getBlockState(pos.above());
        if (!aboveState.isAir()) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        level.setBlock(BlockPos.containing(pos.getX(), pos.getY(), pos.getZ()), BlockRegistry.FARMLAND.get().defaultBlockState(), UPDATE_ALL);

        float pitch = 0.9f + level.getRandom().nextFloat() * 0.2f;
        level.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0f, pitch);
        held.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

        getSeeds(level, pos, player);

        return ItemInteractionResult.SUCCESS;
    }

    static boolean canSurvive(LevelReader level, BlockPos pos) {
        var above = pos.above();
        var aboveState = level.getBlockState(above);
        if (aboveState.is(BlockRegistry.LEAVES.get())) {
            return true;
        }
        return aboveState.getLightBlock(level, above) <= 0;
    }

    static void getSeeds(LevelReader level, BlockPos pos, Player player) {
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