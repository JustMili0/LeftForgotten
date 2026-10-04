package net.justmili.leftforgotten.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.client.dimension.AlphaMinecraft;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.core.mixin.accessors.DimSpecialEffectsAccessor;
import net.justmili.leftforgotten.core.registries.client.ClientEventRegistry;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class CommonClient {

    public static void init() {
        Config.initClient();

        ClientEventRegistry.init();

        DimSpecialEffectsAccessor.getEffects().put(LeftForgotten.asId("alpha_minecraft"), new AlphaMinecraft());
    }

    public static boolean shouldReplaceBakedModel(ResourceLocation id) {
        if (!(id instanceof ModelResourceLocation modelId)) return false;
        return id.getNamespace().equals("minecraft") && !modelId.getVariant().equals("inventory")
            && (id.getPath().equals("furnace") || id.getPath().equals("crafting_table"));
    }
}