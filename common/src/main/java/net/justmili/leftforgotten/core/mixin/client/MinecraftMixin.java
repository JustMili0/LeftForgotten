package net.justmili.leftforgotten.core.mixin.client;

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

    @Inject(method = "useAmbientOcclusion", at = @At("HEAD"), cancellable = true)
    private static void lf$setNoAO(CallbackInfoReturnable<Boolean> cir) {
        if (Config.blockyLighting.isNull() || !Config.blockyLighting.get()) return;
        if (Versions.hadBlockyLighting(ClientUtil.level())) cir.setReturnValue(false);
    }

    @Inject(method = "startAttack", at = @At("HEAD"))
    private void lf$setNoMissPenalty(CallbackInfoReturnable<Boolean> cir) {
        if (this.missTime > 0 && Versions.isOldVersion(ClientUtil.level())) this.missTime = 0;
    }
}