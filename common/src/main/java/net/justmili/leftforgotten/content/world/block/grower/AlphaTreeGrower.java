package net.justmili.leftforgotten.content.world.block.grower;

import net.justmili.leftforgotten.content.world.ConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public final class AlphaTreeGrower {
    public static final TreeGrower INSTANCE =
        new TreeGrower(
            "tree",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ConfiguredFeatures.FANCY_TREE),
            Optional.of(ConfiguredFeatures.TREE),
            Optional.empty(),
            Optional.empty()
        );

    private AlphaTreeGrower() {
    }
}