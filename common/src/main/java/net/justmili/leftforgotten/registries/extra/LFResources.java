package net.justmili.leftforgotten.registries.extra;

import com.google.common.collect.Streams;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.registries.BlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.Supplier;

public class LFResources {
    public static Block[] getBlocksFromRegistry() {
        return Streams.stream(BlockRegistry.REGISTRY).filter(Objects::nonNull).map(Supplier::get).toArray(Block[]::new);
    }

    public static final ResourceKey<Level> // Version Overlay main texts (and versions I'll be recreating)
        BETA_MINECRAFT = newKey("beta_minecraft"),        // Minecraft Beta 1.8.1
        ALPHA_MINECRAFT = newKey("alpha_minecraft"),       // Minecraft Alpha v1.1.2_01
        INFDEV_MINECRAFT = newKey("infdev_minecraft"),      // Infdev v20100227
        INDEV_MINECRAFT = newKey("indev_minecraft"),         // 0.31 20100110 (only displays "0.31")
        CLASSIC_MINECRAFT = newKey("classic_minecraft"),      // 0.30_01 (only displays "0.30")
        PRECLASSIC_MINECRAFT = newKey("preclassic_minecraft"); // rd-132211 (no overlay)

    private static ResourceKey<Level> newKey(String key) {
        return ResourceKey.create(Registries.DIMENSION, LeftForgotten.asId(key));
    }
}