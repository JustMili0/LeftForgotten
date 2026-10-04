package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class OldTillableBlock extends Block {

    public OldTillableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        var held = player.getItemInHand(hand);
        if (!held.is(ItemTags.HOES) || !level.getBlockState(pos.above()).isAir()) return InteractionResult.PASS;

        if (!level.isClientSide()) {
            level.setBlockAndUpdate(pos, BlockRegistry.FARMLAND.get().defaultBlockState());
            level.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1f, 0.9f + level.getRandom().nextFloat() * 0.2f);
            held.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
            onTilled((ServerLevel) level, pos, player);
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    protected void onTilled(ServerLevel level, BlockPos pos, Player player) {}
}
