package net.justmili.leftforgotten.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EatBlockGoal.class)
public class EatBlockGoalMixin {

    @Shadow
    @Final
    private Mob mob;

    @Shadow
    @Final
    private Level level;

    @ModifyReturnValue(method = "canUse", at = @At("RETURN"))
    public boolean lf$canUse(boolean original) {
        var below = mob.blockPosition().below();
        if (level.getBlockState(below).is(BlockRegistry.GRASS_BLOCK.get())) {
            return true;
        } else {
            return original;
        }
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean lf$redirectGrassCheck(BlockState state, Block block, Operation<Boolean> original) {
        // vanilla check
        if (state.is(block)) return true;

        var below = mob.blockPosition().below();
        if (state.is(BlockRegistry.GRASS_BLOCK.get())) {

            if (level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                level.levelEvent(2001, below, Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
                level.setBlock(below, Blocks.DIRT.defaultBlockState(), 2);
            }

            mob.ate();
        }
        // custom block support
        return false;
    }
}