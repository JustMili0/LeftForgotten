package net.justmili.leftforgotten.neoforge.client;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.content.entity.renderer.LFBoatRenderer;
import net.justmili.leftforgotten.registries.LFEntities;
import net.justmili.leftforgotten.registries.LFResources;
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
        for (Block block : LFResources.getBlocksFromRegistry()) {
            ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
        }
        CommonClient.register();
    }

    @SubscribeEvent
    public static void wrapModelsForRemodelBlocks(ModelEvent.ModifyBakingResult event) {
        for (ModelResourceLocation modelLocation : event.getModels().keySet()) {
            if (CommonClient.shouldReplaceBakedModel(modelLocation)) {
                event.getModels().put(modelLocation, new ClassicBlocksModelNeo(event.getModels().get(modelLocation)));
            }
        }
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(LFEntities.BOAT.get(), LFBoatRenderer::new);
    }
}