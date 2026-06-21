package net.justmili.leftforgotten.neoforge;

import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.core.datagen.*;
import net.justmili.leftforgotten.core.datagen.neoforge.NeoDatagen;
import net.minecraft.data.DataProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(LeftForgotten.MODID)
public final class LeftForgottenNeo {
    public LeftForgottenNeo(IEventBus modEventBus) {
        if (Platform.isModLoaded("true_end")) {
            throw new RuntimeException("""
                
                
                
                Left Forgotten and True End are incompatible! This is due duplicate code of each other's codebases
                caused by both being fully or/and partially made and managed by user JustMili.
                Please remove one of the mods from your instance.
                
                (Why would you even have both installed?)
                """);
        }

        modEventBus.addListener(NeoDatagen::onDatagenSetup);

        LeftForgotten.init();
    }
}
