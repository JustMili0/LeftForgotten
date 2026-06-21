package net.justmili.leftforgotten.registries;

import com.google.common.collect.Streams;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.Supplier;

public class LFResources {
    public static Block[] getBlocksFromRegistry() {
        return Streams.stream(LFBlocks.REGISTRY).filter(Objects::nonNull).map(Supplier::get).toArray(Block[]::new);
    }

    public static final class Levels {
        public static final ResourceKey<Level>
            BETA_MINECRAFT = newKey("beta_minecraft"),
            ALPHA_MINECRAFT = newKey("alpha_minecraft"),
            INFDEV_MINECRAFT = newKey("infdev_minecraft"),
            INDEV_MINECRAFT = newKey("indev_minecraft"),
            CLASSIC_MINECRAFT = newKey("classic_minecraft"),
            PRECLASSIC_MINECRAFT = newKey("preclassic_minecraft");
    }

    private static ResourceKey<Level> newKey(String key) {
        return ResourceKey.create(Registries.DIMENSION, LeftForgotten.asResource(key));
    }
}