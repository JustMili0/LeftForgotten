package net.justmili.leftforgotten.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.libs.v1.utils.ClientUtil;
import net.justmili.leftforgotten.registries.LFBlocks;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChestRenderer.class)
public class RemodelChests {
    @Inject(method = "render(Lnet/minecraft/world/level/block/entity/BlockEntity;" +
        "FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",
        at = @At("HEAD"), cancellable = true)
    private <T extends BlockEntity & LidBlockEntity> void lf$renderAlphaChest(T blockEntity, float partialTick, PoseStack poseStack,
                                                                              MultiBufferSource buffer, int packedLight, int packedOverlay,
                                                                              CallbackInfo ci) {
        Level level = blockEntity.getLevel();
        if (level == null) return;
        if (!Config.remodelChests.get()) return;
        if (!ClientUtil.inDimension(LFResources.Levels.ALPHA_MINECRAFT)) return;
        if (!blockEntity.getBlockState().is(Blocks.CHEST)) return;

        ci.cancel();

        BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();
        var state = LFBlocks.REMODEL_CHEST.get().withPropertiesOf(blockEntity.getBlockState());
        var model = dispatcher.getBlockModel(state);

        poseStack.pushPose();
        dispatcher.getModelRenderer().renderModel(
            poseStack.last(),
            buffer.getBuffer(RenderType.solid()),
            state,
            model,
            1f, 1f, 1f,
            packedLight,
            packedOverlay
        );
        poseStack.popPose();
    }
}