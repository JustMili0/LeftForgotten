package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.registries.LFBlocks;
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
import net.minecraft.world.level.LevelAccessor;
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
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!canStayGrass(world, pos)) {
            if (!world.isClientSide()) {
                world.setBlock(pos, LFBlocks.DIRT.get().defaultBlockState(), 3);
            }
        }
    }

    private static boolean canStayGrass(LevelReader world, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = world.getBlockState(abovePos);
        if (aboveState.is(LFBlocks.LEAVES.get())) {
            return true;
        }
        return aboveState.getLightBlock(world, abovePos) <= 0;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState blockstate, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        float pitch = 0.9f + world.getRandom().nextFloat() * 0.2f;

        if (player.getMainHandItem().is(ItemTags.HOES)) {
            world.setBlock(BlockPos.containing(pos.getX(), pos.getY(), pos.getZ()), LFBlocks.FARMLAND.get().defaultBlockState(), 3);
            world.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0f, pitch);
            player.getMainHandItem().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            getSeeds(world, pos, player);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private static void getSeeds(LevelAccessor world, BlockPos pos, Player player) {
        if (player == null) return;
        if (!player.getMainHandItem().is(ItemTags.HOES)) return;
        if (!(Math.random() < 0.125)) return;
        if (world instanceof ServerLevel level) {
            ItemEntity seedsItem = new ItemEntity(level, pos.getX(), (pos.getY() + 1.1), pos.getZ(), new ItemStack(Items.WHEAT_SEEDS));
            seedsItem.setPickUpDelay(15);
            level.addFreshEntity(seedsItem);
        }
    }
}