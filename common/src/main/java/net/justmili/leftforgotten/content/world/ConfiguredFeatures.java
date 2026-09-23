package net.justmili.leftforgotten.content.world;

import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class ConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE = create("tree"), FANCY_TREE = create("fancy_tree");

    public static ResourceKey<ConfiguredFeature<?, ?>> create(String path) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, LeftForgotten.asId(path));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>>
    void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}