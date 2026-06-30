package net.justmili.leftforgotten.libs;

import net.justmili.leftforgotten.libs.v1.utils.ResourceUtil;
import net.justmili.leftforgotten.libs.v1.utils.TickUtil;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreLibs {
    public static final String MODID = "corelibs";
    public static final Logger LOGGER = LoggerFactory.getLogger(CoreLibs.class);

    public static void init() {
        TickUtil.registerProcessQueue();
    }

    public static ResourceLocation asResource(String path) {
        return ResourceUtil.parse(MODID, path);
    }
}