package net.justmili.leftforgotten.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.justmili.leftforgotten.config.Config;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = PerlinNoise.class, priority = 2000)
public class Farlands {
    @ModifyReturnValue(method = "wrap", at = @At("RETURN"))
    private static double replaceWrapReturn(double original, double value) {
        if (!Config.generateFarlands.get()) return original;
        return value;
    }
}