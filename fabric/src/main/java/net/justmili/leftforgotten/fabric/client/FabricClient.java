package net.justmili.leftforgotten.fabric.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.content.entity.renderer.OldBoatRenderer;
import net.justmili.leftforgotten.registries.EntityRegistry;
import net.justmili.leftforgotten.registries.extra.LFResources;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public final class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block block : LFResources.getBlocksFromRegistry()) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        }

        EntityRendererRegistry.register(EntityRegistry.BOAT, OldBoatRenderer::new);

        CommonClient.register();

        ModelLoadingPlugin.register(context ->
            context.modifyModelAfterBake().register((model, ctx) -> {
                if (CommonClient.shouldReplaceBakedModel(ctx.id())) return new ClassicBlocksModelFabric(model);
                return model;
            })
        );
    }
}