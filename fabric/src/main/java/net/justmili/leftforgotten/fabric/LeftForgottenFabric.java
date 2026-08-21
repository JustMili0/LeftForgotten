package net.justmili.leftforgotten.fabric;

import net.fabricmc.api.ModInitializer;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.registries.fabric.BiomeModifierRegistry;

public final class LeftForgottenFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        LeftForgotten.init();
        BiomeModifierRegistry.register();
    }
}
