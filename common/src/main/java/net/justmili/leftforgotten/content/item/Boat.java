package net.justmili.leftforgotten.content.item;

import net.justmili.leftforgotten.content.entity.LFBoatEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class Boat extends Item {
    public Boat() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        var hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if (hitResult.getType() == HitResult.Type.MISS) return InteractionResultHolder.pass(stack);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            var pos = hitResult.getBlockPos();
            var face = hitResult.getDirection();

            var boat = new LFBoatEntity(level, pos.getX() + 0.5, pos.getY() + face.getStepY() + 0.375, pos.getZ() + 0.5);
            boat.setYRot(player.getYRot());

            if (!level.noCollision(boat, boat.getBoundingBox())) return InteractionResultHolder.fail(stack);
            if (!level.isClientSide) level.addFreshEntity(boat);

            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) stack.shrink(1);

            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }

        return InteractionResultHolder.pass(stack);
    }
}