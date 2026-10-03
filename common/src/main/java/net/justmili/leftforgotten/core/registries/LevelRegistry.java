package net.justmili.leftforgotten.core.registries;

import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class LevelRegistry {

    public static void init() {
    }

    // Version Overlay main texts (and versions I'll be recreating)
    public static final ResourceKey<Level> BETA = level("beta_minecraft");   // Minecraft Beta 1.8.1
    public static final ResourceKey<Level> ALPHA = level("alpha_minecraft");   // Minecraft Alpha v1.1.2_01
    public static final ResourceKey<Level> INFDEV = level("infdev_minecraft");   // Infdev v20100227
    public static final ResourceKey<Level> INDEV = level("indev_minecraft");       // 0.31 20100110 (only displays "0.31")
    public static final ResourceKey<Level> CLASSIC = level("classic_minecraft");     // 0.30_01 (only displays "0.30")
    public static final ResourceKey<Level> PRECLASSIC = level("preclassic_minecraft"); // rd-132211 (no overlay)

    private static ResourceKey<Level> level(String key) {
        return ResourceKey.create(Registries.DIMENSION, LeftForgotten.asId(key));
    }
}