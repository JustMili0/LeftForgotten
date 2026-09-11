package net.justmili.leftforgotten.core.mixin.client;

import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.core.util.Versions;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Works on Forge and Fabric, but because Forge does mixins in a weird way it doesn't work in Forge Dev Environment
@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Inject(method = "useAmbientOcclusion", at = @At("HEAD"), cancellable = true)
    private static void lf$blockyLighting(CallbackInfoReturnable<Boolean> cir) {
        if (Config.forceBlockyLighting.isNull() || !Config.forceBlockyLighting.get()) return;

        if (ClientUtil.level() != null && Versions.hadBlockyLighting(ClientUtil.dimension())) cir.setReturnValue(false);
    }
}