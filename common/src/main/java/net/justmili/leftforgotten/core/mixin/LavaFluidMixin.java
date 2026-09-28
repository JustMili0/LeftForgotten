package net.justmili.leftforgotten.core.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.justmili.leftforgotten.core.util.Versions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin {

    @Definition(id = "defaultBlockState", method = "Lnet/minecraft/world/level/block/Block;defaultBlockState()Lnet/minecraft/world/level/block/state/BlockState;")
    @Expression("?.defaultBlockState()")
    @ModifyExpressionValue(method = "spreadTo", at = @At("MIXINEXTRAS:EXPRESSION"))
    private BlockState lf$modifySpreadState(BlockState original, LevelAccessor accessor) {
        if (!(accessor instanceof Level level)) return original;
        return Versions.isOldVersion(level)? BlockRegistry.STONE.get().defaultBlockState() : original;
    }
}