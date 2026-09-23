package net.justmili.leftforgotten.core.mixin.fabric;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.justmili.leftforgotten.core.util.Versions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LiquidBlock.class)
public abstract class LiquidBlockMixin {

    @Definition(id = "defaultBlockState", method = "Lnet/minecraft/world/level/block/Block;defaultBlockState()Lnet/minecraft/world/level/block/state/BlockState;")
    @Expression("?.defaultBlockState()")
    @ModifyExpressionValue(method = "shouldSpreadLiquid", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private BlockState lf$modifySpreadState(BlockState original, Level level) {
        if (!Versions.isOldVersion(level)) return original;
        return original.is(Blocks.OBSIDIAN)? BlockRegistry.OBSIDIAN.get().defaultBlockState() : BlockRegistry.COBBLESTONE.get().defaultBlockState();
    }
}