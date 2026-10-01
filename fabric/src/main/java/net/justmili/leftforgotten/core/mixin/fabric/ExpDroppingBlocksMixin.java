package net.justmili.leftforgotten.core.mixin.fabric;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.justmili.leftforgotten.core.util.Versions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({DropExperienceBlock.class, RedStoneOreBlock.class})
public class ExpDroppingBlocksMixin {

    @Definition(id = "dropExperience", local = @Local(type = boolean.class, argsOnly = true))
    @Expression("dropExperience")
    @ModifyExpressionValue(method = "spawnAfterBreak", at = @At(value = "MIXINEXTRAS:EXPRESSION", ordinal = 1))
    private boolean lf$preventSpawningExpOrbs(boolean original, @Local(argsOnly = true) ServerLevel level) {
        return original && !Versions.hadNoExp(level);
    }
}