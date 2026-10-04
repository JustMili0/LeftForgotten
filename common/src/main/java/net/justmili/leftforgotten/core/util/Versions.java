package net.justmili.leftforgotten.core.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;

import static net.justmili.leftforgotten.core.registries.LevelRegistry.*;

public class Versions {
    private static final List<ResourceKey<Level>> VERSIONS = List.of(PRECLASSIC, CLASSIC, INDEV, INFDEV, ALPHA, BETA);
    private static final List<ResourceKey<Level>> UP_TO_ALPHA = List.of(PRECLASSIC, CLASSIC, INDEV, INFDEV, ALPHA);
    private static final List<ResourceKey<Level>> HAD_VERSION_OVERLAY = List.of(CLASSIC, INDEV, INFDEV, ALPHA, BETA);

    public static Level get(LivingEntity entity, ResourceKey<Level> dimension) {
        var server = entity.level().getServer();
        if (server == null) return entity.level();
        return server.getLevel(dimension);
    }

    public static boolean isOverworld(Level level) {
        if (level == null) return false;
        return level.dimension() == Level.OVERWORLD;
    }

    public static boolean isOldVersion(Level level) {
        if (level == null) return false;
        return VERSIONS.contains(level.dimension());
    }

    public static boolean isHighestLayer(Level level) {
        if (level == null) return false;
        return level.dimension() == ALPHA; // TODO: Change to BETA once added in 1.3
    }

    public static boolean upToAny(Level level) {
        if (level == null) return false;
        return VERSIONS.contains(level.dimension());
    }

    public static boolean upToBeta(Level level) {
        return upToAny(level);
    }

    public static boolean upToAlpha(Level level) {
        if (level == null) return false;
        return UP_TO_ALPHA.contains(level.dimension());
    }

    public static boolean hadVersionOverlay(Level level) {
        return HAD_VERSION_OVERLAY.contains(level.dimension());
    }

    public static boolean hadOldHUD(Level level) {
        return upToAlpha(level);
    }

    public static boolean hadNoHunger(Level level) {
        return upToAlpha(level);
    }

    public static boolean hadNoSprint(Level level) {
        return upToAlpha(level);
    }

    public static boolean hadNoAttackCooldown(Level level) {
        return upToBeta(level);
    }

    public static boolean hadNoExp(Level level) {
        return upToAlpha(level);
    }

    public static boolean hadNoBeds(Level level) {
        return upToAlpha(level);
    }

    public static boolean hadBedMonsters(Level level) {
        if (level == null) return false;
        return level.dimension() == BETA;
    }

    public static boolean hadBlockyLighting(Level level) {
        return upToAlpha(level);
    }

    public static boolean hadCaveFog(Level level) {
        if (level == null) return false;
        return level.dimension() == BETA;
    }

    public static boolean hadBlockyChests(Level level) {
        return upToAlpha(level);
    }

    public static boolean hadNoSkins(Level level) {
        return upToAlpha(level);
    }

    public static boolean hadBillboardItems(Level level) {
        return upToBeta(level);
    }
}