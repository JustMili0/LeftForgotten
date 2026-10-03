package net.justmili.leftforgotten.libs;

import net.justmili.leftforgotten.libs.v1.utils.ModUtil;
import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.justmili.leftforgotten.libs.v1.utils.common.TickUtil;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreLibs {

    public static final Logger LOGGER = LoggerFactory.getLogger(CoreLibs.class);
    public static final String ID = "corelibs";
    public static final String NAME = "Millie's Core Libraries";
    public static final String BUILD = "0.0.2a-alpha.1"; // Yes, alpha of an early dev alpha.

    public static void init() {
        ModUtil.specialInitMessage(LOGGER, NAME, ID, BUILD, ModUtil.VersionBuildType.CORELIBS_INDEV);
        TickUtil.registerProcessQueue();
    }

    public static ResourceLocation asId(String path) {
        return ResourceUtil.parse(ID, path);
    }
}