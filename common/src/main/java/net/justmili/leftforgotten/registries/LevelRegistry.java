package net.justmili.leftforgotten.registries;

import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class LevelRegistry {

    public static final ResourceKey<Level> // Version Overlay main texts (and versions I'll be recreating)
        BETA = register("beta_minecraft"),        // Minecraft Beta 1.8.1
        ALPHA = register("alpha_minecraft"),       // Minecraft Alpha v1.1.2_01
        INFDEV = register("infdev_minecraft"),      // Infdev v20100227
        INDEV = register("indev_minecraft"),         // 0.31 20100110 (only displays "0.31")
        CLASSIC = register("classic_minecraft"),      // 0.30_01 (only displays "0.30")
        PRECLASSIC = register("preclassic_minecraft"); // rd-132211 (no overlay)

    private static ResourceKey<Level> register(String key) {
        return ResourceKey.create(Registries.DIMENSION, LeftForgotten.asId(key));
    }
}