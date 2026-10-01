package net.justmili.leftforgotten.core.mixin.neoforge;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.justmili.leftforgotten.core.util.Versions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({DropExperienceBlock.class, RedStoneOreBlock.class})
public class ExpDroppingBlocksMixin {

    @ModifyReturnValue(method = "getExpDrop", at = @At("RETURN"), remap = false)
    private int lf$preventSpawningExpOrbs(int original, @Local(argsOnly = true) LevelAccessor accessor) {
        return original != 0 && accessor instanceof Level level && Versions.hadNoExp(level)? 0 : original;
    }
}