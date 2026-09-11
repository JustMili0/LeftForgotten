package net.justmili.leftforgotten.core.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.List;

import static net.justmili.leftforgotten.core.registries.LevelRegistry.*;

public class Versions {
    public static boolean isOldVersion(ResourceKey<Level> level) {
        return List.of(PRECLASSIC, CLASSIC, INDEV, INFDEV, ALPHA, BETA).contains(level);
    }

    public static boolean isHighestLayer(ResourceKey<Level> level) {
        return level.equals(ALPHA); // TODO: Change to BETA once added in 1.3
    }

    public static boolean upToBeta(ResourceKey<Level> level) {
        return List.of(PRECLASSIC, CLASSIC, INDEV, INFDEV, ALPHA, BETA).contains(level);
    }

    public static boolean upToAlpha(ResourceKey<Level> level) {
        return List.of(PRECLASSIC, CLASSIC, INDEV, INFDEV, ALPHA).contains(level);
    }

    public static boolean hadVersionOverlay(ResourceKey<Level> level) {
        return upToBeta(level);
    }

    public static boolean hadOldHUD(ResourceKey<Level> level) {
        return upToAlpha(level);
    }

    public static boolean hadNoHunger(ResourceKey<Level> level) {
        return upToAlpha(level);
    }

    public static boolean hadNoSprint(ResourceKey<Level> level) {
        return upToAlpha(level);
    }

    public static boolean hadNoAttackCooldown(ResourceKey<Level> level) {
        return upToBeta(level);
    }

    public static boolean hadNoExp(ResourceKey<Level> level) {
        return upToAlpha(level);
    }

    public static boolean hadNoBeds(ResourceKey<Level> level) {
        return upToAlpha(level);
    }

    public static boolean hadBlockyLighting(ResourceKey<Level> level) {
        return upToAlpha(level);
    }

    public static boolean hadStoneOnFurnaceTexture(ResourceKey<Level> level) {
        return upToAlpha(level);
    }

    public static boolean hadNoSkins(ResourceKey<Level> level) {
        return upToAlpha(level);
    }

    public static boolean hadBillboardItems(ResourceKey<Level> level) {
        return upToBeta(level);
    }
}