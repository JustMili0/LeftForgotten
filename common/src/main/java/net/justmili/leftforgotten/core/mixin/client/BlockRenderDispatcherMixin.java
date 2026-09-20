package net.justmili.leftforgotten.core.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.leftforgotten.core.util.client.Remodels;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockRenderDispatcher.class)
public class BlockRenderDispatcherMixin {

    @WrapOperation(method = {
        "renderBatched", // fabric target
        "renderBatched*" // forge target
    }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/BlockRenderDispatcher;getBlockModel(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/client/resources/model/BakedModel;"), require = 0)
    private BakedModel lf$swapBatchedModel(BlockRenderDispatcher dispatcher, BlockState state, Operation<BakedModel> original) {
        // blockView is not necessarily Level
        var block = state.getBlock();
        var remodel = Remodels.of(block);
        return original.call(dispatcher, remodel == block? state : remodel.withPropertiesOf(state));
    }
}