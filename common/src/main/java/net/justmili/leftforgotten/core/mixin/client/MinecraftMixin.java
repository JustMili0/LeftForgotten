package net.justmili.leftforgotten.core.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Works on Forge and Fabric, but because Forge does mixins in a weird way it doesn't work in Forge Dev Environment
@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    protected int missTime;

    @ModifyReturnValue(method = "useAmbientOcclusion", at = @At("RETURN"))
    private static boolean lf$setNoAO(boolean original) {
        if (Config.blockyLighting.isNull() || !Config.blockyLighting.get()) return original;
        return !Versions.hadBlockyLighting(ClientUtil.level()) && original;
    }

    @Inject(method = "startAttack", at = @At("HEAD"))
    private void lf$setNoMissPenalty(CallbackInfoReturnable<Boolean> cir) {
        if (this.missTime > 0 && Versions.isOldVersion(ClientUtil.level())) this.missTime = 0;
    }
}