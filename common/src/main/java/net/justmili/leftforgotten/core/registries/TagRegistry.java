package net.justmili.leftforgotten.core.registries;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.libs.v1.utils.common.TagUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TagRegistry {

    public static final TagKey<Block> ALPHA_NATURAL_LOGS = TagUtil.block(id("alpha_natural_logs"));
    public static final TagKey<Block> BETA_NATURAL_LOGS = TagUtil.block(id("beta_natural_logs"));

    private static ResourceLocation id(String path) {
        return LeftForgotten.asId(path);
    }
}
