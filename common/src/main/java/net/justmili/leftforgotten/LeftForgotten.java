package net.justmili.leftforgotten;

import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.content.entity.OldBoatImpactPacket;
import net.justmili.leftforgotten.core.registries.*;
import net.justmili.leftforgotten.libs.CoreLibs;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LeftForgotten {
    public static final Logger LOGGER = LoggerFactory.getLogger(LeftForgotten.class);
    public static final String MODID = "left_forgotten";

    public static void init() {
        CoreLibs.init();
        Config.common();

        BlockRegistry.register();
        ItemRegistry.register();
        TabRegistry.register();
        EntityRegistry.register();
        SoundRegistry.register();

        OldBoatImpactPacket.register();
        EventRegistry.register();
    }

    public static ResourceLocation asId(String path) {
        return new ResourceLocation(MODID, path);
    }
}