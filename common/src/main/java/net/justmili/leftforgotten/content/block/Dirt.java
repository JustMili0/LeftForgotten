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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
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
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (canBeGrass(world, pos)) {
            if (!world.isClientSide()) {
                world.setBlock(pos, LFBlocks.GRASS_BLOCK.get().defaultBlockState(), 3);
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(net.minecraft.world.item.ItemStack stack, BlockState blockstate, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        float pitch = 0.9f + world.getRandom().nextFloat() * 0.2f;

        if (player.getMainHandItem().is(ItemTags.HOES)) {
            world.setBlock(BlockPos.containing(pos.getX(), pos.getY(), pos.getZ()), LFBlocks.FARMLAND.get().defaultBlockState(), 3);
            world.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0f, pitch);
            player.getMainHandItem().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private static boolean canBeGrass(LevelAccessor world, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = world.getBlockState(abovePos);

        // Check if there's a block above, if so, don't turn into grass.
        if (!aboveState.isAir()) return false;

        // Check for adjacent grass blocks in a 3x3x3 area
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) { // Added y loop for vertical check
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue; // Skip the current block

                    BlockPos neighborPos = pos.offset(x, y, z);
                    BlockState neighborState = world.getBlockState(neighborPos);

                    if (neighborState.is(LFBlocks.GRASS_BLOCK.get())) return true;
                }
            }
        }
        return false;
    }
}