package net.justmili.leftforgotten.core.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.client.ClassicItemModels;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.core.util.client.BillboardItems;
import net.justmili.leftforgotten.core.util.client.Remodels;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow
    @Final
    private ItemModelShaper itemModelShaper;

    @ModifyReturnValue(method = "getModel", at = @At("RETURN"))
    private BakedModel lf$tryRemodelBlocksInGUI(BakedModel original, ItemStack stack) {
        var item = stack.getItem();
        var remodel = Remodels.of(item);
        if (remodel == item) return original;

        var model = this.itemModelShaper.getItemModel(remodel);
        return model != null? model : original;
    }

    @ModifyVariable(method = "render", at = @At("HEAD"), argsOnly = true)
    private BakedModel lf$tryRenderAsBillboards(BakedModel model, @Local(argsOnly = true) ItemDisplayContext context) {
        if (context != ItemDisplayContext.GROUND || !Versions.hadBillboardItems(ClientUtil.level()) || model.isGui3d()) return model;
        return new ClassicItemModels(model);
    }
}