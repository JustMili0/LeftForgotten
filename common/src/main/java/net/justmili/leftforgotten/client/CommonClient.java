package net.justmili.leftforgotten.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.client.dimension.AlphaMinecraft;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.core.mixin.accessors.DimSpecialEffectsAccessor;
import net.justmili.leftforgotten.core.registries.client.ClientEventRegistry;
import net.minecraft.client.resources.model.ModelResourceLocation;

@Environment(EnvType.CLIENT)
public class CommonClient {

    public static void init() {
        Config.client();

        ClientEventRegistry.init();

        DimSpecialEffectsAccessor.getEffects().put(LeftForgotten.asId("alpha_minecraft"), new AlphaMinecraft());
    }

    public static boolean shouldReplaceBakedModel(ModelResourceLocation id) {
        if (id == null) return false;
        var modelId = id.id();

        return modelId.getNamespace().equals("minecraft") && !id.getVariant().equals("inventory")
            && (modelId.getPath().equals("furnace") || modelId.getPath().equals("crafting_table"));
    }
}