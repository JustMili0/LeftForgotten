package net.justmili.leftforgotten.forge;

import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.core.datagen.ForgeDatagen;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LeftForgotten.MODID)
public final class LeftForgottenForge {
    public static IEventBus EVENT_BUS;

    public LeftForgottenForge(FMLJavaModLoadingContext modContext) {
        if (Platform.isModLoaded("true_end")) {
            throw new RuntimeException("""
                
                
                
                Left Forgotten and True End are incompatible! This is due duplicate code of each other's codebases
                caused by both being fully or/and partially made and managed by user JustMili.
                Please remove one of the mods from your instance.
                
                (Why would you even have both installed?)
                """);
        }

        EVENT_BUS = modContext.getModEventBus();
        EventBuses.registerModEventBus(LeftForgotten.MODID, EVENT_BUS);
        EVENT_BUS.addListener(ForgeDatagen::onDatagenSetup);

        LeftForgotten.init();
    }
}
