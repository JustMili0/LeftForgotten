package net.justmili.leftforgotten.forge.client;

import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.content.entity.renderer.LFBoatRenderer;
import net.justmili.leftforgotten.registries.EntityRegistry;
import net.justmili.leftforgotten.registries.extra.LFResources;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeClient {
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        /// DEV NOTE: DEPRECATED API USAGE
        for (Block block : LFResources.getBlocksFromRegistry()) {
            ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
        }
        CommonClient.register();
    }

    @SubscribeEvent
    public static void wrapModelsForRemodelBlocks(ModelEvent.ModifyBakingResult event) {
        for (ResourceLocation id : event.getModels().keySet()) {
            if (CommonClient.shouldReplaceBakedModel(id)) {
                event.getModels().put(id, new ClassicBlocksModelForge(event.getModels().get(id)));
            }
        }
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistry.BOAT.get(), LFBoatRenderer::new);
    }
}