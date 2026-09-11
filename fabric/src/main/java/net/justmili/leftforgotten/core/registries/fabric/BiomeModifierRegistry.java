package net.justmili.leftforgotten.core.registries.fabric;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BiomeModifierRegistry {
    private static final ResourceKey<PlacedFeature> BRITTLE_BEDROCK = ResourceKey.create(Registries.PLACED_FEATURE,
        LeftForgotten.asId("brittle_bedrock"));

    public static void register() {
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Decoration.UNDERGROUND_ORES,
            BRITTLE_BEDROCK
        );
    }
}