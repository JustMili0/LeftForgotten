package net.justmili.leftforgotten.neoforge;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.core.datagen.NeoDatagen;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(LeftForgotten.ID)
public final class LeftForgottenNeo {

    public LeftForgottenNeo(IEventBus modEventBus) {
        modEventBus.addListener(NeoDatagen::onDatagenSetup);

        LeftForgotten.init();
    }
}