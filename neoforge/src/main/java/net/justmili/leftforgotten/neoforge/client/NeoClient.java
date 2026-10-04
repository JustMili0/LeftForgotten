package net.justmili.leftforgotten.neoforge.client;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.client.renderer.entity.OldBoatRenderer;
import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.justmili.leftforgotten.core.registries.EntityRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = LeftForgotten.ID, value = Dist.CLIENT)
public class NeoClient {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        // DEV NOTE: DEPRECATED API USAGE
        for (var block : BlockRegistry.getBlocksFromRegistry()) {
            ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
        }
        CommonClient.init();
    }

    @SubscribeEvent
    public static void wrapModelsForRemodelBlocks(ModelEvent.ModifyBakingResult event) {
        var models = event.getModels();
        for (var id : models.keySet()) {
            if (CommonClient.shouldReplaceBakedModel(id)) models.put(id, new ClassicBlockModelsNeo(models.get(id)));
        }
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.BOAT.get(), OldBoatRenderer::new);
    }
}