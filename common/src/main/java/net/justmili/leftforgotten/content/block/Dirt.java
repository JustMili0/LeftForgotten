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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class Dirt extends Block {
    public Dirt() {
        super(Properties.of().mapColor(MapColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL).randomTicks());
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (canGrowGrass(level, pos)) {
            if (!level.isClientSide()) {
                level.setBlock(pos, BlockRegistry.GRASS_BLOCK.get().defaultBlockState(), 3);
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        var held = player.getMainHandItem();
        if (!player.getMainHandItem().is(ItemTags.HOES)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        var aboveState = level.getBlockState(pos.above());
        if (!aboveState.isAir()) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        level.setBlock(BlockPos.containing(pos.getX(), pos.getY(), pos.getZ()), BlockRegistry.FARMLAND.get().defaultBlockState(), UPDATE_ALL);

        float pitch = 0.9f + level.getRandom().nextFloat() * 0.2f;
        level.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0f, pitch);
        held.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

        return ItemInteractionResult.SUCCESS;
    }

    static boolean canGrowGrass(LevelReader level, BlockPos pos) {
        var aboveState = level.getBlockState(pos.above());
        if (!aboveState.isAir()) return false;

        for (BlockPos neighborPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            if (neighborPos.equals(pos)) continue;
            if (level.getBlockState(neighborPos).is(BlockRegistry.GRASS_BLOCK.get())) return true;
        }
        return false;
    }
}