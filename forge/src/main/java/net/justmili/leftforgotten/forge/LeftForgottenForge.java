package net.justmili.leftforgotten.forge;

import dev.architectury.platform.forge.EventBuses;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.core.datagen.ForgeDatagen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LeftForgotten.ID)
public final class LeftForgottenForge {

    public LeftForgottenForge(FMLJavaModLoadingContext modContext) {
        var EVENT_BUS = modContext.getModEventBus();
        EventBuses.registerModEventBus(LeftForgotten.ID, EVENT_BUS);
        EVENT_BUS.addListener(ForgeDatagen::onDatagenSetup);

        LeftForgotten.init();
    }
}
