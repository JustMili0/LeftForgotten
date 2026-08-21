package net.justmili.leftforgotten.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.registries.BlockRegistry;
import net.justmili.leftforgotten.registries.extra.LFResources;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockRenderDispatcher.class)
public class RemodelBlocks {
    @WrapOperation(method = {
        "renderBatched", // fabric target
        "renderBatched*" // forge target
    }, at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/renderer/block/BlockRenderDispatcher;getBlockModel(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/client/resources/model/BakedModel;"), require = 0)
    private BakedModel lf$swapBatchedModel(BlockRenderDispatcher dispatcher, BlockState state, Operation<BakedModel> original) {
        // blockView is not necessarily Level
        if (ClientUtil.getLevel() != null && ClientUtil.inDimension(LFResources.ALPHA_MINECRAFT)) {
            if (state.is(Blocks.CRAFTING_TABLE)) {
                if (!Config.remodelCraftingTable.get()) return original.call(dispatcher, state);
                return original.call(dispatcher, BlockRegistry.REMODEL_CRAFTING_TABLE.get().defaultBlockState());
            }
            if (state.is(Blocks.FURNACE)) {
                if (!Config.remodelFurnace.get()) return original.call(dispatcher, state);
                return original.call(dispatcher, BlockRegistry.REMODEL_FURNACE.get().defaultBlockState());
            }
        }
        return original.call(dispatcher, state);
    }
}