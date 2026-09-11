package net.justmili.leftforgotten.neoforge.client;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.content.entity.renderer.OldBoatRenderer;
import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.justmili.leftforgotten.core.registries.EntityRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = LeftForgotten.MODID, value = Dist.CLIENT)
public class NeoClient {
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        // DEV NOTE: DEPRECATED API USAGE
        for (Block block : BlockRegistry.getBlocksFromRegistry()) {
            ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
        }
        CommonClient.register();
    }

    @SubscribeEvent
    public static void wrapModelsForRemodelBlocks(ModelEvent.ModifyBakingResult event) {
        var models = event.getModels();
        for (ModelResourceLocation modelLocation : models.keySet()) {
            if (CommonClient.shouldReplaceBakedModel(modelLocation)) {
                models.put(modelLocation, new ClassicBlockModelsNeo(models.get(modelLocation)));
            }
        }
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.BOAT.get(), OldBoatRenderer::new);
    }
}