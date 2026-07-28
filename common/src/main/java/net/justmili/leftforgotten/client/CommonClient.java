package net.justmili.leftforgotten.client;

import dev.architectury.event.events.client.ClientTickEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.client.dimension.AlphaMinecraft;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.mixin.accessors.DimSpecialEffectsAccessor;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.resources.model.ModelResourceLocation;

import static net.justmili.leftforgotten.libs.v1.utils.ClientUtil.*;

@Environment(EnvType.CLIENT)
public class CommonClient {
    public static void register() {
        Config.client();

        ClientTickEvent.CLIENT_POST.register(CommonVersionOverlay::onClientTick);

        DimSpecialEffectsAccessor.getEffects().put(LeftForgotten.asResource("alpha_minecraft"), new AlphaMinecraft());
    }

    public static boolean shouldReplaceBakedModel(ModelResourceLocation modelLocation) {
        if (modelLocation == null) return false;

        return modelLocation.id().getNamespace().equals("minecraft")
            && !modelLocation.getVariant().equals("inventory")
            && (modelLocation.id().getPath().equals("furnace")
            || modelLocation.id().getPath().equals("crafting_table"));
    }

    public static boolean inAlpha() {
        return getLevel() != null && getPlayer() != null && inDimension(LFResources.ALPHA_MINECRAFT);
    }
    public static boolean notInAlpha() {
        return !inAlpha();
    }
}