package net.justmili.leftforgotten.mixin.neoforge.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.justmili.leftforgotten.libs.v1.utils.ClientUtil;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.renderer.LevelRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class UnrenderClouds {

    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
    private void unrenderClouds(PoseStack poseStack, Matrix4f frustumMatrix, Matrix4f projectionMatrix,
                                float partialTick, double camX, double camY, double camZ, CallbackInfo ci) {
        if (!ClientUtil.inDimension(LFResources.ALPHA_MINECRAFT)) return;
        ci.cancel();
    }
}