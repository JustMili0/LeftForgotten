package net.justmili.leftforgotten.content.world.block.grower;

import net.justmili.leftforgotten.content.world.ConfiguredFeatures;
import net.justmili.leftforgotten.libs.v1.utils.common.MathUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class AlphaTreeGrower extends AbstractTreeGrower {
    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
        if (MathUtil.chance(0.9f)) {
            return ConfiguredFeatures.TREE;
        } else {
            return ConfiguredFeatures.FANCY_TREE;
        }
    }
}
