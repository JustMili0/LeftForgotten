package net.justmili.leftforgotten.core.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.core.util.client.BillboardItems;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow
    protected abstract void renderQuadList(PoseStack pose, VertexConsumer consumer, List<BakedQuad> quads, ItemStack stack, int light, int overlay);

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderModelLists(Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemStack;IILcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;)V"))
    private void lf$tryRenderAsBillboards(ItemRenderer instance, BakedModel model, ItemStack stack, int light, int overlay, PoseStack pose, VertexConsumer consumer,
                                          Operation<Void> original, @Local(argsOnly = true) ItemDisplayContext context) {

        if (context != ItemDisplayContext.GROUND || !Versions.hadBillboardItems(ClientUtil.dimension()) || model.isGui3d()) {
            original.call(instance, model, stack, light, overlay, pose, consumer);
            return;
        }

        var random = RandomSource.create(42L);
        var originalQuads = model.getQuads(null, null, random);

        if (originalQuads.isEmpty()) return;

        pose.pushPose();
        List<BakedQuad> quads = new ArrayList<>(originalQuads);

        for (var quad : originalQuads) {
            if (quad.getDirection() != Direction.SOUTH) {
                quads.remove(quad);
            } else {
                BillboardItems.setUnitNormals(pose.last().normal(), quad);
            }
        }

        this.renderQuadList(pose, consumer, quads, stack, light, overlay);
        pose.popPose();
    }
}