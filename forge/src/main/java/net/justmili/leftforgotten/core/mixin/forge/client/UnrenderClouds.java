
package net.justmili.leftforgotten.core.mixin.forge.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.client.renderer.LevelRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class UnrenderClouds {

    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
    private void lf$disableClouds(PoseStack pose, Matrix4f matrix4f, float partialTick, double camX, double camY, double camZ, CallbackInfo ci) {
        // For some reason Forge doesn't understand that if could level is set to Float.NaN then it shouldn't show 'em
        if (Versions.isOldVersion(ClientUtil.level())) ci.cancel();
    }
}