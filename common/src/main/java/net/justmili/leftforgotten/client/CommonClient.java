package net.justmili.leftforgotten.client;

import dev.architectury.event.events.client.ClientTickEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.client.dimension.AlphaMinecraft;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.core.mixin.accessors.DimSpecialEffectsAccessor;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class CommonClient {
    public static void register() {
        Config.client();

        ClientTickEvent.CLIENT_POST.register(CommonVersionOverlay::onClientTick);

        DimSpecialEffectsAccessor.getEffects().put(LeftForgotten.asId("alpha_minecraft"), new AlphaMinecraft());
    }

    public static boolean shouldReplaceBakedModel(ResourceLocation modelLocation) {
        if (modelLocation == null) return false;

        return modelLocation instanceof ModelResourceLocation resourceLocation
            && modelLocation.getNamespace().equals("minecraft")
            && !resourceLocation.getVariant().equals("inventory")
            && (modelLocation.getPath().equals("furnace")
            || modelLocation.getPath().equals("crafting_table"));
    }
}