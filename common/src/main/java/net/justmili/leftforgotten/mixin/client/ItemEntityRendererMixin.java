package net.justmili.leftforgotten.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.math.Axis;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.libs.v1.utils.client.RenderingUtil;
import net.justmili.leftforgotten.util.Versions;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.world.entity.item.ItemEntity;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemEntityRenderer.class)
public abstract class ItemEntityRendererMixin {

    @ModifyExpressionValue(method = "render(Lnet/minecraft/world/entity/item/ItemEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/math/Axis;rotation(F)Lorg/joml/Quaternionf;"))
    private Quaternionf lf$setItemRotation(Quaternionf quaternion, ItemEntity entity) {
        if (!Versions.hadBillboardItems(ClientUtil.dimension())) return quaternion;
        if (!RenderingUtil.getItemModel(entity.getItem()).usesBlockLight()) return Axis.YP.rotationDegrees(180.0f - RenderingUtil.getMainCam().getYRot());
        return quaternion;
    }
}