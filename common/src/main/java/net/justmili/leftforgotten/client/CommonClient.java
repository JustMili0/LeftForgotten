package net.justmili.leftforgotten.client;

import dev.architectury.event.events.client.ClientTickEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.client.dimension.AlphaMinecraft;
import net.justmili.leftforgotten.mixin.accessors.DimSpecialEffectsAccessor;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

import static net.justmili.leftforgotten.core.util.ClientUtil.*;

@Environment(EnvType.CLIENT)
public class CommonClient {
    public static void register() {
        ClientTickEvent.CLIENT_POST.register(CommonVersionOverlay::onClientTick);

        DimSpecialEffectsAccessor.getEffects().put(LeftForgotten.asResource("alpha_minecraft"), new AlphaMinecraft());
    }

    public static boolean shouldReplaceBakedModel(ResourceLocation modelLocation) {
        if (modelLocation == null) return false;

        return modelLocation instanceof ModelResourceLocation resourceLocation
            && modelLocation.getNamespace().equals("minecraft")
            && !resourceLocation.getVariant().equals("inventory")
            && (modelLocation.getPath().equals("furnace")
            || modelLocation.getPath().equals("crafting_table"));
    }

    public static boolean inAlpha() {
        return getLevel() != null && getPlayer() != null && inDimension(LFResources.Levels.ALPHA_MINECRAFT);
    }
    public static boolean notInAlpha() {
        return !inAlpha();
    }

    public static boolean nonSurvivalGamemode() {
        if (minecraft.gameMode == null) return false;
        return !(minecraft.gameMode.canHurtPlayer() && minecraft.getCameraEntity() instanceof Player);
    }
}
