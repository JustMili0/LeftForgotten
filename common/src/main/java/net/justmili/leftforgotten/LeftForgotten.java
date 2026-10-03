package net.justmili.leftforgotten;

import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.core.registries.*;
import net.justmili.leftforgotten.libs.CoreLibs;
import net.justmili.leftforgotten.libs.v1.utils.ModUtil;
import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LeftForgotten {
    public static final Logger LOGGER = LoggerFactory.getLogger(LeftForgotten.class);
    public static final String ID = "left_forgotten";
    public static final String NAME = "Left Forgotten";
    public static final String BUILD = "1.2.0-beta.2";

    public static void init() {
        CoreLibs.init();

        ModUtil.specialInitMessage(LOGGER, NAME, ID, BUILD, ModUtil.VersionBuildType.BETA);
        Config.common();

        BlockRegistry.init();
        ItemRegistry.init();
        TabRegistry.init();
        EntityRegistry.init();
        SoundRegistry.init();

        PacketRegistry.init();
        EventRegistry.init();
    }

    public static ResourceLocation asId(String path) {
        return ResourceUtil.parse(ID, path);
    }
}