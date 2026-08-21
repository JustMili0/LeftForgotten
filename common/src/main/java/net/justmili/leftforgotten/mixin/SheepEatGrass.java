package net.justmili.leftforgotten.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.leftforgotten.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
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
public class SheepEatGrass {
    @Shadow @Final private Mob mob;
    @Shadow @Final private Level level;

    @ModifyReturnValue(method = "canUse", at = @At("RETURN"))
    public boolean canUse(boolean original) {
        BlockPos blockPos = this.mob.blockPosition();
        if (this.level.getBlockState(blockPos.below()).is(BlockRegistry.GRASS_BLOCK.get())) {
            return true;
        } else {
            return original;
        }
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean redirectGrassCheck(BlockState state, Block block, Operation<Boolean> original) {
        // vanilla check
        if (state.is(block)) {
            return true;
        }

        BlockPos blockPos = this.mob.blockPosition();
        BlockPos blockPos2 = blockPos.below();
        if (state.is(BlockRegistry.GRASS_BLOCK.get())) {

            if (this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                this.level.levelEvent(2001, blockPos.below(), Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
                this.level.setBlock(blockPos2, Blocks.DIRT.defaultBlockState(), 2);
            }

            this.mob.ate();
        }
        // custom block support
        return false;
    }
}
