package net.justmili.leftforgotten.mixin.client;

import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Works on Forge and Fabric, but because Forge does mixins in a weird way it doesn't work in Forge Dev Environment
@Mixin(Minecraft.class)
public abstract class BlockyLighting {
    @Inject(method = "useAmbientOcclusion", at = @At("HEAD"), cancellable = true)
    private static void blockyLighting(CallbackInfoReturnable<Boolean> cir) {
        if (Config.forceBlockyLighting.isNull() || !Config.forceBlockyLighting.get()) return;

        if (ClientUtil.level() != null && CommonClient.inAlpha()) {
            cir.setReturnValue(false);
        }
    }
}