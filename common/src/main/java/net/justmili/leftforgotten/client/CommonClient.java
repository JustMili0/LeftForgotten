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
    public static void register() {
        Config.client();

        ClientEventRegistry.register();

        DimSpecialEffectsAccessor.getEffects().put(LeftForgotten.asId("alpha_minecraft"), new AlphaMinecraft());
    }

    public static boolean shouldReplaceBakedModel(ModelResourceLocation modelLocation) {
        if (modelLocation == null) return false;

        return modelLocation.id().getNamespace().equals("minecraft")
            && !modelLocation.getVariant().equals("inventory")
            && (modelLocation.id().getPath().equals("furnace")
            || modelLocation.id().getPath().equals("crafting_table"));
    }
}