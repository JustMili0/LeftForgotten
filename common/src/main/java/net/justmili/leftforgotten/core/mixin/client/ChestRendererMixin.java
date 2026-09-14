package net.justmili.leftforgotten.core.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.libs.v1.utils.client.RenderingUtil;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChestRenderer.class)
public class ChestRendererMixin {

    @Inject(method = "render(Lnet/minecraft/world/level/block/entity/BlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At("HEAD"), cancellable = true)
    private <T extends BlockEntity & LidBlockEntity> void lf$tryRemodelChest(T blockEntity, float partialTick, PoseStack pose, MultiBufferSource buffer,
                                                                              int light, int overlay, CallbackInfo ci) {
        if (!Config.chestRemodel.get()) return;
        if (!Versions.hadBlockyChests(ClientUtil.level())) return;
        if (!blockEntity.getBlockState().is(Blocks.CHEST)) return;

        ci.cancel();

        var renderer = RenderingUtil.getBlockRenderer();
        var state = BlockRegistry.REMODEL_CHEST.get().withPropertiesOf(blockEntity.getBlockState());
        var model = renderer.getBlockModel(state);

        pose.pushPose();

        // Fix fucked up rotation
        if (!blockEntity.hasLevel()) {
            float yRot = state.getValue(ChestBlock.FACING).toYRot();
            pose.translate(0.5f, 0.5f, 0.5f);
            pose.mulPose(Axis.YP.rotationDegrees(-yRot));
            pose.translate(-0.5f, -0.5f, -0.5f);
        }

        renderer.getModelRenderer().renderModel(pose.last(), buffer.getBuffer(RenderType.solid()), state, model, 1f, 1f, 1f, light, overlay);
        pose.popPose();
    }
}