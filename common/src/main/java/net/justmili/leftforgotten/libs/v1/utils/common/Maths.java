package net.justmili.leftforgotten.libs.v1.utils.common;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

public class Maths {
    private static final int FLOAT_STEPS = 1 << 24;
    public static final int MAX_INT = Integer.MAX_VALUE;
    public static final int MIN_INT = Integer.MIN_VALUE;
    public static final long MAX_LONG = Long.MAX_VALUE;
    public static final long MIN_LONG = Long.MIN_VALUE;
    public static final float MAX_FLOAT = Float.MAX_VALUE;
    public static final float MIN_FLOAT = -Float.MAX_VALUE;
    public static final float FNaN = Float.NaN;
    public static final double MAX_DOUBLE = Double.MAX_VALUE;
    public static final double MIN_DOUBLE = -Double.MAX_VALUE;
    public static final double DNaN = Double.NaN;
    public static final double POS_INFINITY = Double.POSITIVE_INFINITY;
    public static final double NEG_INFINITY = Double.NEGATIVE_INFINITY;
    public static final double PI = Math.PI;
    public static final double TWO_PI = Mth.TWO_PI;
    public static final double HALF_PI = Mth.HALF_PI;
    public static final double QUARTER_PI = Math.PI / 4.0;
    public static final double E = Math.E;
    public static final double SQRT_2 = Mth.SQRT_OF_TWO;
    public static final double SQRT_3 = sqrt(3);
    public static final double GOLDEN_RATIO = 1.618033988749895;
    public static final double DEG_TO_RAD = Mth.DEG_TO_RAD;
    public static final double RAD_TO_DEG = Mth.RAD_TO_DEG;
    public static final double EPSILON = Mth.EPSILON;
    public static final double MICROS_PER_NANO = 1.0E-3;
    public static final double MILLIS_PER_NANO = 1.0E-6;
    public static final double SECONDS_PER_NANO = 1.0E-9;
    public static final double MILLIS_PER_MICRO = 1.0E-3;
    public static final double SECONDS_PER_MICRO = 1.0E-6;
    public static final double SECONDS_PER_MILLI = 1.0E-3;
    public static final int TICKS_PER_SECOND = 20;
    public static final int TICKS_PER_MINUTE = TICKS_PER_SECOND * 60;
    public static final int TICKS_PER_HOUR = TICKS_PER_MINUTE * 60;
    public static final int TICKS_PER_DAY = TICKS_PER_HOUR * 24;

    public static RandomSource random = RandomSource.create();

    public static void setSeed(long seed) {
        random.setSeed(seed);
    }

    public static int randomInt() {
        return random.nextInt();
    }

    public static int randomInt(int min, int max) {
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        return Mth.randomBetweenInclusive(random, min, max);
    }

    public static long randomLong() {
        return random.nextLong();
    }

    public static float randomFloat() {
        return randomFloat(0f, 1f);
    }

    public static float randomFloat(float min, float max) {
        if (min > max) {
            float temp = min;
            min = max;
            max = temp;
        }
        int step = random.nextInt(FLOAT_STEPS + 1);
        if (step == FLOAT_STEPS) return max;
        return min + (max - min) * ((float) step / FLOAT_STEPS);
    }

    public static double randomDouble(double min, double max) {
        if (min > max) {
            double temp = min;
            min = max;
            max = temp;
        }
        return Mth.nextDouble(random, min, max);
    }

    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    public static int randomSign() {
        return random.nextBoolean()? 1 : -1;
    }

    public static double randomGaussian(double mean, double standardDeviation) {
        return mean + random.nextGaussian() * standardDeviation;
    }

    public static boolean equal(float x, float y) {
        return Math.abs(y - x) < 1.0E-5F;
    }

    public static boolean equal(double x, double y) {
        return Math.abs(y - x) < (double) 1.0E-5F;
    }

    public static boolean chance(float chance) {
        return random.nextFloat() < Math.min(chance, 1f);
    }

    public static <T> T randomElement(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    @SafeVarargs
    public static <T> T randomElement(T... array) {
        return array[random.nextInt(array.length)];
    }

    public static boolean isInView(ServerPlayer player, Entity target, int fov) {
        var toTarget = target.getBoundingBox().getCenter().subtract(player.getEyePosition()).normalize();
        double dot = clamp(toTarget.dot(player.getLookAngle().normalize()), -1.0, 1.0);
        return Math.toDegrees(Math.acos(dot)) < (fov / 2.0);
    }

    public static boolean isInView(ServerPlayer player, BlockPos target, int fov) {
        var toTarget = target.getCenter().subtract(player.getEyePosition()).normalize();
        double dot = clamp(toTarget.dot(player.getLookAngle().normalize()), -1.0, 1.0);
        return Math.toDegrees(Math.acos(dot)) < (fov / 2.0);
    }

    public static String ticksToTime(long ticks) {
        long totalSecs = ticks / TICKS_PER_SECOND;
        long secs = totalSecs % 60;
        long mins = (totalSecs / 60) % 60;
        long hours = (totalSecs / 3600) % 24;
        long days = totalSecs / 86400;

        if (days > 0) return String.format("%sd %sh %smin %ss", days, hours, mins, secs);
        if (hours > 0) return String.format("%sh %smin %ss", hours, mins, secs);
        if (mins > 0) return String.format("%smin %ss", mins, secs);
        return String.format("%ss", secs);
    }

    public static int ticksToSeconds(long ticks) {
        return (int) (ticks / TICKS_PER_SECOND);
    }

    public static double ticksToMinutes(long ticks) {
        return roundHalfUp(ticks / (double) TICKS_PER_MINUTE, 2);
    }

    public static double ticksToHours(long ticks) {
        return roundHalfUp(ticks / (double) TICKS_PER_HOUR, 2);
    }

    public static double ticksToDays(long ticks) {
        return roundHalfUp(ticks / (double) TICKS_PER_DAY, 2);
    }

    public static long ticksToMillis(long ticks) {
        return ticks * 50L;
    }

    public static long secondsToTicks(double seconds) {
        return Math.round(seconds * TICKS_PER_SECOND);
    }

    public static long minutesToTicks(double minutes) {
        return Math.round(minutes * TICKS_PER_MINUTE);
    }

    public static long hoursToTicks(double hours) {
        return Math.round(hours * TICKS_PER_HOUR);
    }

    public static long millisToTicks(long millis) {
        return millis / 50L;
    }

    public static int abs(int value) {
        return Mth.abs(value);
    }

    public static long abs(long value) {
        return Math.abs(value);
    }

    public static float abs(float value) {
        return Mth.abs(value);
    }

    public static double abs(double value) {
        return Math.abs(value);
    }

    public static double absMax(double x, double y) {
        return Mth.absMax(x, y);
    }

    public static int min(int x, int y) {
        return Math.min(x, y);
    }

    public static long min(long x, long y) {
        return Math.min(x, y);
    }

    public static float min(float x, float y) {
        return Math.min(x, y);
    }

    public static double min(double x, double y) {
        return Math.min(x, y);
    }

    public static int min(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }

    public static double min(double x, double y, double z) {
        return Math.min(x, Math.min(y, z));
    }

    public static int max(int x, int y) {
        return Math.max(x, y);
    }

    public static long max(long x, long y) {
        return Math.max(x, y);
    }

    public static float max(float x, float y) {
        return Math.max(x, y);
    }

    public static double max(double x, double y) {
        return Math.max(x, y);
    }

    public static int max(int x, int y, int z) {
        return Math.max(x, Math.max(y, z));
    }

    public static double max(double x, double y, double z) {
        return Math.max(x, Math.max(y, z));
    }

    public static int clamp(int value, int min, int max) {
        return Mth.clamp(value, min, max);
    }

    public static long clamp(long value, long min, long max) {
        return Math.min(Math.max(value, min), max);
    }

    public static float clamp(float value, float min, float max) {
        return Mth.clamp(value, min, max);
    }

    public static double clamp(double value, double min, double max) {
        return Mth.clamp(value, min, max);
    }

    public static float clamp(float value) {
        return clamp(value, 0f, 1f);
    }

    public static double clamp(double value) {
        return clamp(value, 0.0, 1.0);
    }

    public static int clampToInt(long value, int min, int max) {
        return (int) Math.min(max, Math.max(value, min));
    }

    public static int floor(double value) {
        return Mth.floor(value);
    }

    public static int floor(float value) {
        return Mth.floor(value);
    }

    public static long floorLong(double value) {
        return Mth.lfloor(value);
    }

    public static int ceil(double value) {
        return Mth.ceil(value);
    }

    public static int ceil(float value) {
        return Mth.ceil(value);
    }

    public static int round(double value) {
        return (int) Math.round(value);
    }

    public static double rint(double value) {
        return Math.rint(value);
    }

    public static double frac(double value) {
        return Mth.frac(value);
    }

    public static float frac(float value) {
        return Mth.frac(value);
    }

    public static double snap(double value, double step) {
        return Math.round(value / step) * step;
    }

    public static int quantize(double value, int factor) {
        return Mth.quantize(value, factor);
    }

    public static int roundToward(int value, int factor) {
        return Mth.roundToward(value, factor);
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

    public static float roundAwayFromZero(double value, int pastDecimal) {
        return BigDecimal.valueOf(value).setScale(pastDecimal, RoundingMode.UP).floatValue();
    }

    public static float roundTowardZero(double value, int pastDecimal) {
        return BigDecimal.valueOf(value).setScale(pastDecimal, RoundingMode.DOWN).floatValue();
    }

    public static int floorDiv(int dividend, int divisor) {
        return Mth.floorDiv(dividend, divisor);
    }

    public static long floorDiv(long dividend, long divisor) {
        return Math.floorDiv(dividend, divisor);
    }

    public static int ceilDiv(int dividend, int divisor) {
        int quotient = dividend / divisor;
        if ((dividend ^ divisor) >= 0 && quotient * divisor != dividend) return quotient + 1;
        return quotient;
    }

    public static long ceilDiv(long dividend, long divisor) {
        long quotient = dividend / divisor;
        if ((dividend ^ divisor) >= 0 && quotient * divisor != dividend) return quotient + 1;
        return quotient;
    }

    public static int ceilMod(int dividend, int divisor) {
        int remainder = dividend % divisor;
        if ((dividend ^ divisor) >= 0 && remainder != 0) return remainder - divisor;
        return remainder;
    }

    public static long ceilMod(long dividend, long divisor) {
        long remainder = dividend % divisor;
        if ((dividend ^ divisor) >= 0 && remainder != 0) return remainder - divisor;
        return remainder;
    }

    public static int mod(int dividend, int divisor) {
        return Mth.positiveModulo(dividend, divisor);
    }

    public static long mod(long dividend, long divisor) {
        return Math.floorMod(dividend, divisor);
    }

    public static float mod(float dividend, float divisor) {
        return Mth.positiveModulo(dividend, divisor);
    }

    public static double mod(double dividend, double divisor) {
        return Mth.positiveModulo(dividend, divisor);
    }

    public static boolean isEven(int value) {
        return (value & 1) == 0;
    }

    public static boolean isOdd(int value) {
        return !isEven(value);
    }

    public static boolean isEven(long value) {
        return (value & 1L) == 0;
    }

    public static boolean isOdd(long value) {
        return !isEven(value);
    }

    public static boolean isDivisibleBy(int value, int divisor) {
        return Mth.isMultipleOf(value, divisor);
    }

    public static float sign(float value) {
        return value < 0? -1f : 1f;
    }

    public static double sign(double value) {
        return value < 0? -1.0 : 1.0;
    }

    public static int addExact(int x, int y) {
        return Math.addExact(x, y);
    }

    public static long addExact(long x, long y) {
        return Math.addExact(x, y);
    }

    public static int subtractExact(int x, int y) {
        return Math.subtractExact(x, y);
    }

    public static long subtractExact(long x, long y) {
        return Math.subtractExact(x, y);
    }

    public static int multiplyExact(int x, int y) {
        return Math.multiplyExact(x, y);
    }

    public static long multiplyExact(long x, long y) {
        return Math.multiplyExact(x, y);
    }

    public static int absExact(int value) {
        return Math.absExact(value);
    }

    public static long absExact(long value) {
        return Math.absExact(value);
    }

    public static int toIntExact(long value) {
        return Math.toIntExact(value);
    }

    public static double pow(double value, double powerOf) {
        return Math.pow(value, powerOf);
    }

    public static float sqr(float value) {
        return Mth.square(value);
    }

    public static double sqr(double value) {
        return Mth.square(value);
    }

    public static int sqr(int value) {
        return Mth.square(value);
    }

    public static long sqr(long value) {
        return Mth.square(value);
    }

    public static double cube(double value) {
        return value * value * value;
    }

    public static double sqrt(double value) { // Square root
        return Math.sqrt(value);
    }

    public static double cbrt(double value) { // Cube root
        return Math.cbrt(value);
    }

    public static double nrt(double value, int degree) {
        if (degree == 0) throw new IllegalArgumentException("degree must not be 0");
        if (value < 0 && degree % 2 == 0) return Double.NaN;
        return Math.copySign(Math.pow(Math.abs(value), 1.0 / degree), value);
    }

    public static float invSqrt(float value) {
        return Mth.invSqrt(value);
    }

    public static double invSqrt(double value) {
        return Mth.invSqrt(value);
    }

    public static float fastInvCubeRoot(float value) {
        return Mth.fastInvCubeRoot(value);
    }

    public static double exp(double value) {
        return Math.exp(value);
    }

    public static double expm1(double value) {
        return Math.expm1(value);
    }

    public static double log(double value) {
        return Math.log(value);
    }

    public static double log1p(double value) {
        return Math.log1p(value);
    }

    public static double log10(double value) {
        return Math.log10(value);
    }

    public static double log2(double value) {
        return Math.log(value) / Math.log(2.0);
    }

    public static int floorLog2(int value) {
        return Mth.log2(value);
    }

    public static int ceilLog2(int value) {
        return Mth.ceillog2(value);
    }

    public static double logBase(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    public static double hypot(double x, double y) {
        return Math.hypot(x, y);
    }

    public static double fma(double factor, double multiplier, double addend) {
        return Math.fma(factor, multiplier, addend);
    }

    public static double copySign(double magnitude, double sign) {
        return Math.copySign(magnitude, sign);
    }

    public static double ulp(double value) {
        return Math.ulp(value);
    }

    public static double nextUp(double value) {
        return Math.nextUp(value);
    }

    public static double nextDown(double value) {
        return Math.nextDown(value);
    }

    public static double sin(double radians) {
        return Mth.sin((float) radians);
    }

    public static double cos(double radians) {
        return Mth.cos((float) radians);
    }

    public static double tan(double radians) {
        return Math.tan(radians);
    }

    public static double asin(double value) {
        return Math.asin(clamp(value, -1.0, 1.0));
    }

    public static double acos(double value) {
        return Math.acos(clamp(value, -1.0, 1.0));
    }

    public static double atan(double value) {
        return Math.atan(value);
    }

    public static double atan2(double y, double x) {
        return Math.atan2(y, x);
    }

    public static double sinh(double value) {
        return Math.sinh(value);
    }

    public static double cosh(double value) {
        return Math.cosh(value);
    }

    public static double tanh(double value) {
        return Math.tanh(value);
    }

    public static double sinDeg(double degrees) {
        return Mth.sin((float) Math.toRadians(degrees));
    }

    public static double cosDeg(double degrees) {
        return Mth.cos((float) Math.toRadians(degrees));
    }

    public static double tanDeg(double degrees) {
        return Math.tan(Math.toRadians(degrees));
    }

    public static double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    public static double toDegrees(double radians) {
        return Math.toDegrees(radians);
    }

    public static int wrapDegrees(int degrees) {
        return Mth.wrapDegrees(degrees);
    }

    public static float wrapDegrees(float degrees) {
        return Mth.wrapDegrees(degrees);
    }

    public static double wrapDegrees(double degrees) {
        return Mth.wrapDegrees(degrees);
    }

    public static double wrapRadians(double radians) {
        return wrap(radians, -PI, PI);
    }

    public static double wrapDegreeDifference(double start, double end) {
        return Mth.wrapDegrees(end - start);
    }

    public static double lerpDegreeAngle(double start, double end, double delta) {
        return start + Mth.wrapDegrees(end - start) * delta;
    }

    public static float approachDegrees(float angle, float limit, float stepSize) {
        return Mth.approachDegrees(angle, limit, stepSize);
    }

    public static float triangleWave(float input, float period) {
        return Mth.triangleWave(input, period);
    }

    public static float lerp(float start, float end, float delta) {
        return Mth.lerp(delta, start, end);
    }

    public static double lerp(double start, double end, double delta) {
        return Mth.lerp(delta, start, end);
    }

    public static int lerpInt(int start, int end, float delta) {
        return Mth.lerpInt(delta, start, end);
    }

    public static float clampedLerp(float start, float end, float delta) {
        return Mth.clampedLerp(start, end, delta);
    }

    public static double clampedLerp(double start, double end, double delta) {
        return Mth.clampedLerp(start, end, delta);
    }

    public static double bilerp(double firstDelta, double secondDelta, double firstStart, double firstEnd, double secondStart, double secondEnd) {
        return Mth.lerp2(firstDelta, secondDelta, firstStart, firstEnd, secondStart, secondEnd);
    }

    public static double trilerp(double firstDelta, double secondDelta, double thirdDelta,
                                 double firstStart, double firstEnd, double secondStart, double secondEnd,
                                 double thirdStart, double thirdEnd, double fourthStart, double fourthEnd) {
        return Mth.lerp3(firstDelta, secondDelta, thirdDelta, firstStart, firstEnd, secondStart, secondEnd, thirdStart, thirdEnd, fourthStart, fourthEnd);
    }

    public static float catmullRom(float delta, float firstPoint, float secondPoint, float thirdPoint, float fourthPoint) {
        return Mth.catmullrom(delta, firstPoint, secondPoint, thirdPoint, fourthPoint);
    }

    public static double inverseLerp(double start, double end, double value) {
        return start == end? 0.0 : Mth.inverseLerp(value, start, end);
    }

    public static double remap(double value, double inputMin, double inputMax, double outputMin, double outputMax) {
        return Mth.map(value, inputMin, inputMax, outputMin, outputMax);
    }

    public static double clampedRemap(double value, double inputMin, double inputMax, double outputMin, double outputMax) {
        return Mth.clampedMap(value, inputMin, inputMax, outputMin, outputMax);
    }

    public static double percent(double value, double total) {
        return total == 0? 0 : (value / total) * 100.0;
    }

    public static double smoothstep(double lowerEdge, double upperEdge, double value) {
        double progress = clamp(inverseLerp(lowerEdge, upperEdge, value));
        return progress * progress * (3.0 - 2.0 * progress);
    }

    public static double smootherstep(double lowerEdge, double upperEdge, double value) {
        return Mth.smoothstep(clamp(inverseLerp(lowerEdge, upperEdge, value)));
    }

    public static double easeInQuad(double progress) {
        return progress * progress;
    }

    public static double easeOutQuad(double progress) {
        return 1.0 - sqr(1.0 - progress);
    }

    public static double easeInOutQuad(double progress) {
        return progress < 0.5? 2.0 * progress * progress : 1.0 - sqr(-2.0 * progress + 2.0) / 2.0;
    }

    public static double easeInCubic(double progress) {
        return cube(progress);
    }

    public static double easeOutCubic(double progress) {
        return 1.0 - cube(1.0 - progress);
    }

    public static double easeInOutCubic(double progress) {
        return progress < 0.5? 4.0 * cube(progress) : 1.0 - cube(-2.0 * progress + 2.0) / 2.0;
    }

    public static float moveTowards(float current, float target, float delta) {
        return Mth.approach(current, target, delta);
    }

    public static double moveTowards(double current, double target, double delta) {
        if (Math.abs(target - current) <= delta) return target;
        return current + sign(target - current) * delta;
    }

    public static float moveClampTowards(float current, float target, float delta, float min, float max) {
        return clamp(moveTowards(current, target, delta), min, max);
    }

    public static double moveClampTowards(double current, double target, double delta, double min, double max) {
        return clamp(moveTowards(current, target, delta), min, max);
    }

    public static int wrap(int value, int start, int end) {
        return Math.floorMod(value - start, end - start) + start;
    }

    public static long wrap(long value, long start, long end) {
        return Math.floorMod(value - start, end - start) + start;
    }

    public static double wrap(double value, double start, double end) {
        double range = end - start;
        double offset = value - start;
        return offset - Math.floor(offset / range) * range + start;
    }

    public static boolean isInRange(double value, double start, double end) {
        return value >= start && value <= end;
    }

    public static boolean isWithinLine(double point, double start, double size) {
        return point >= start && point < start + size;
    }

    public static boolean isWithinBox(double pointX, double pointY, double startX, double startY, double width, double height) {
        return isWithinLine(pointX, startX, width) && isWithinLine(pointY, startY, height);
    }

    public static boolean tolerance(double x, double y, double tolerance) {
        return Math.abs(x - y) < tolerance;
    }

    public static boolean tolerance(double x, double y, double z, double tolerance) {
        return max(x, y, z) - min(x, y, z) < tolerance;
    }

    public static boolean approx(double x, double y) {
        return equal(x, y);
    }

    public static boolean isFinite(double value) {
        return Double.isFinite(value);
    }

    public static double lengthSqrd(double x, double y) {
        return Mth.lengthSquared(x, y);
    }

    public static double lengthSqrd(double x, double y, double z) {
        return Mth.lengthSquared(x, y, z);
    }

    public static double length(double x, double y) {
        return Mth.length(x, y);
    }

    public static double length(double x, double y, double z) {
        return Mth.length(x, y, z);
    }

    public static double distanceSqrd(double startX, double startY, double endX, double endY) {
        return Mth.lengthSquared(endX - startX, endY - startY);
    }

    public static double distance(double startX, double startY, double endX, double endY) {
        return Mth.length(endX - startX, endY - startY);
    }

    public static double distanceSqrd(double startX, double startY, double startZ, double endX, double endY, double endZ) {
        return Mth.lengthSquared(endX - startX, endY - startY, endZ - startZ);
    }

    public static double distance(double startX, double startY, double startZ, double endX, double endY, double endZ) {
        return Mth.length(endX - startX, endY - startY, endZ - startZ);
    }

    public static double manhattan(double startX, double startY, double endX, double endY) {
        return Math.abs(endX - startX) + Math.abs(endY - startY);
    }

    public static double manhattan(double startX, double startY, double startZ, double endX, double endY, double endZ) {
        return Math.abs(endX - startX) + Math.abs(endY - startY) + Math.abs(endZ - startZ);
    }

    public static double dot(double firstX, double firstY, double secondX, double secondY) {
        return firstX * secondX + firstY * secondY;
    }

    public static double dot(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ) {
        return firstX * secondX + firstY * secondY + firstZ * secondZ;
    }

    public static double circleArea(double radius) {
        return PI * sqr(radius);
    }

    public static double circleCircumference(double radius) {
        return TWO_PI * radius;
    }

    public static double sphereVolume(double radius) {
        return (4.0 / 3.0) * PI * cube(radius);
    }

    public static float center(int startPos, int size, int maxSize) {
        return startPos + Math.abs((size / 2f) - (maxSize / 2f));
    }

    public static float center(int size, int maxSize) {
        return center(0, size, maxSize);
    }

    public static long gcd(long x, long y) {
        x = Math.abs(x);
        y = Math.abs(y);
        while (y != 0) {
            long remainder = y;
            y = x % y;
            x = remainder;
        }
        return x;
    }

    public static long lcm(long x, long y) {
        if (x == 0 || y == 0) return 0;
        return Math.abs(x / gcd(x, y) * y);
    }

    public static boolean isPrime(long value) {
        if (value < 2) return false;
        if (value < 4) return true;
        if (value % 2 == 0 || value % 3 == 0) return false;
        for (long divisor = 5; divisor * divisor <= value; divisor += 6) {
            if (value % divisor == 0 || value % (divisor + 2) == 0) return false;
        }
        return true;
    }

    public static long factorial(int value) {
        if (value < 0 || value > 20) throw new IllegalArgumentException("value must be 0..20");
        long result = 1;
        for (int factor = 2; factor <= value; factor++) result *= factor;
        return result;
    }

    public static long fibonacci(int position) {
        if (position < 0 || position > 92) throw new IllegalArgumentException("position must be 0..92");
        long previous = 0, current = 1;
        for (int index = 0; index < position; index++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return previous;
    }

    public static long binomial(int total, int chosen) {
        if (chosen < 0 || chosen > total) return 0;
        chosen = Math.min(chosen, total - chosen);
        long result = 1;
        for (int index = 1; index <= chosen; index++) {
            result = result * (total - chosen + index) / index;
        }
        return result;
    }

    public static boolean isPowerOfTwo(int value) {
        return Mth.isPowerOfTwo(value);
    }

    public static int nextPowerOfTwo(int value) {
        return Mth.smallestEncompassingPowerOfTwo(value);
    }

    public static int digitCount(long value) {
        return value == 0? 1 : (int) Math.log10(Math.abs((double) value)) + 1;
    }

    public static int parseInt(String value, int defaultValue) {
        return Mth.getInt(value, defaultValue);
    }

    public static int hash(int value) {
        return Mth.murmurHash3Mixer(value);
    }

    public static int binarySearch(int min, int max, IntPredicate isTargetBeforeOrAt) {
        return Mth.binarySearch(min, max, isTargetBeforeOrAt);
    }

    public static int largest(int... numbers) {
        int largest = MIN_INT;
        for (int number : numbers) if (number > largest) largest = number;
        return largest;
    }

    public static double largest(double... numbers) {
        double largest = NEG_INFINITY;
        for (double number : numbers) if (number > largest) largest = number;
        return largest;
    }

    public static int smallest(int... numbers) {
        int smallest = MAX_INT;
        for (int number : numbers) if (number < smallest) smallest = number;
        return smallest;
    }

    public static double smallest(double... numbers) {
        double smallest = POS_INFINITY;
        for (double number : numbers) if (number < smallest) smallest = number;
        return smallest;
    }

    public static double sum(double... numbers) {
        double total = 0;
        for (double number : numbers) total += number;
        return total;
    }

    public static double product(double... numbers) {
        double total = 1;
        for (double number : numbers) total *= number;
        return total;
    }

    public static double avg(double... numbers) {
        return numbers.length == 0? 0 : sum(numbers) / numbers.length;
    }

    public static double med(double... numbers) {
        if (numbers.length == 0) return 0;
        double[] sorted = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(sorted);
        int middle = sorted.length / 2;
        return sorted.length % 2 == 0? (sorted[middle - 1] + sorted[middle]) / 2.0 : sorted[middle];
    }

    public static double var(double... numbers) {
        if (numbers.length == 0) return 0;
        double average = avg(numbers);
        double total = 0;
        for (double number : numbers) total += sqr(number - average);
        return total / numbers.length;
    }

    public static double standardDeviation(double... numbers) {
        return Math.sqrt(var(numbers));
    }
}