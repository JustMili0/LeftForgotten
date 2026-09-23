package net.justmili.leftforgotten.core.mixin.forge;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.justmili.leftforgotten.core.util.Versions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FluidInteractionRegistry.class)
public abstract class FluidInteractionRegistryMixin {

    @Definition(id = "interact", method = "Lnet/minecraftforge/fluids/FluidInteractionRegistry$FluidInteraction;interact(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/material/FluidState;)V")
    @Expression("?.interact(?, ?, ?, ?)")
    @WrapOperation(method = "canInteract", at = @At("MIXINEXTRAS:EXPRESSION"), remap = false)
    private static void lf$wrapInteraction(FluidInteractionRegistry.FluidInteraction interaction, Level level, BlockPos pos, BlockPos relative, FluidState fluidState, Operation<Void> original) {
        original.call(interaction, level, pos, relative, fluidState);
        if (!Versions.isOldVersion(level)) return;

        var result = level.getBlockState(pos);
        if (result.is(Blocks.OBSIDIAN)) {
            level.setBlockAndUpdate(pos, BlockRegistry.OBSIDIAN.get().defaultBlockState());
        } else if (result.is(Blocks.COBBLESTONE)) {
            level.setBlockAndUpdate(pos, BlockRegistry.COBBLESTONE.get().defaultBlockState());
        }
    }
}