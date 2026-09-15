package net.justmili.leftforgotten.core.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;

import static net.justmili.leftforgotten.core.registries.LevelRegistry.*;

public class Versions {
    static final List<ResourceKey<Level>> VERSIONS = List.of(PRECLASSIC, CLASSIC, INDEV, INFDEV, ALPHA, BETA);

    public static Level get(LivingEntity entity, ResourceKey<Level> dimension) {
        var server = entity.level().getServer();
        if (server == null) return entity.level();
        return server.getLevel(dimension);
    }

    public static boolean isOverworld(Level level) {
        if (level == null) return false;
        return level.dimension().equals(Level.OVERWORLD);
    }

    public static boolean isOldVersion(Level level) {
        if (level == null) return false;
        return VERSIONS.contains(level.dimension());
    }

    public static boolean isHighestLayer(Level level) {
        if (level == null) return false;
        return level.dimension().equals(ALPHA); // TODO: Change to BETA once added in 1.3
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
        return List.of(PRECLASSIC, CLASSIC, INDEV, INFDEV, ALPHA).contains(level.dimension());
    }

    public static boolean hadVersionOverlay(Level level) {
        return upToBeta(level);
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

    public static boolean hadNoBeds(Level level) {
        return upToAlpha(level);
    }

    public static boolean hadBedMonsters(Level level) {
        if (level == null) return false;
        return level.dimension().equals(BETA);
    }

    public static boolean hadBlockyLighting(Level level) {
        return upToAlpha(level);
    }

    public static boolean hadCaveFog(Level level) {
        if (level == null) return false;
        return level.dimension().equals(BETA);
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