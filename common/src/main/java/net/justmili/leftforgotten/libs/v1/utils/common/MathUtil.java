package net.justmili.leftforgotten.libs.v1.utils.common;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class MathUtil {
    public static Random random = new Random();

    public static boolean chance(float chance) {
        // Ex.: 20% = 0.2f
        if (chance > 1.0f) chance = 1.0f; // Limit to 100%
        return !(Math.random() <= chance);
    }

    public static boolean isInView(ServerPlayer player, Entity target, int fov) {
        var eyePos = player.getEyePosition();
        var targetCenter = target.getBoundingBox().getCenter();
        var toTarget = targetCenter.subtract(eyePos).normalize();
        double angle = Math.toDegrees(Math.acos(toTarget.dot(player.getLookAngle().normalize())));
        return angle < (fov / 2.0);
    }

    public static boolean isInView(ServerPlayer player, BlockPos target, int fov) {
        var targetCenter = Vec3.atCenterOf(target);
        var eyePos = player.getEyePosition();
        var toTarget = targetCenter.subtract(eyePos).normalize();
        double angle = Math.toDegrees(Math.acos(toTarget.dot(player.getLookAngle().normalize())));
        return angle < (fov / 2.0);
    }

    /**
     * Any integer, long, double or float can be input as-is,
     * and it'll just be turned into a double
     *
     * @param ticks
     * @return
     */
    public static String ticksToTime(long ticks) {
        int s = ticksToSeconds(ticks) % 60;
        int min = roundIntDown(ticksToMinutes(ticks)) % 60;
        int h = roundIntDown(ticksToHours(ticks)) % 24;
        int d = roundIntDown(ticksToDays(ticks));

        if (d > 0) return String.format("%sd %sh %smin %ss", d, h, min, s);
        if (h > 0) return String.format("%sh %smin %ss", h, min, s);
        if (min > 0) return String.format("%smin %ss", min, s);
        return String.format("%ss", s);
    }

    public static int ticksToSeconds(long ticks) {
        long seconds = ticks / 20;
        return (int) roundHalfUp(seconds, 0);
    }

    public static double ticksToMinutes(long ticks) {
        long minutes = ticks / 20 / 60;
        return roundHalfUp(minutes, 2);
    }

    public static double ticksToHours(long ticks) {
        long hours = ticks / 20 / 60 / 60;
        return roundHalfUp(hours, 2);
    }

    public static double ticksToDays(long ticks) {
        long hours = ticks / 20 / 60 / 60 / 24;
        return roundHalfUp(hours, 2);
    }

    public static float roundHalfUp(double value, int pastDecimal) {
        return BigDecimal.valueOf(value).setScale(pastDecimal, RoundingMode.HALF_UP).floatValue();
    }

    public static float roundHalfDown(double value, int pastDecimal) {
        return BigDecimal.valueOf(value).setScale(pastDecimal, RoundingMode.HALF_DOWN).floatValue();
    }

    public static float roundHalfEven(double value, int pastDecimal) {
        return BigDecimal.valueOf(value).setScale(pastDecimal, RoundingMode.HALF_EVEN).floatValue();
    }

    public static float roundUp(double value, int pastDecimal) {
        return BigDecimal.valueOf(value).setScale(pastDecimal, RoundingMode.UP).floatValue();
    }

    public static float roundDown(double value, int pastDecimal) {
        return BigDecimal.valueOf(value).setScale(pastDecimal, RoundingMode.DOWN).floatValue();
    }

    public static int roundIntUp(double value) {
        return Math.toIntExact((long) Math.ceil(value));
    }

    public static int roundIntDown(double value) {
        return Math.toIntExact((long) Math.floor(value));
    }
}