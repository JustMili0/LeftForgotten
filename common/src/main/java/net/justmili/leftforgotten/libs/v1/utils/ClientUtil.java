package net.justmili.leftforgotten.libs.v1.utils;

import com.mojang.blaze3d.platform.Window;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

@Environment(EnvType.CLIENT)
public class ClientUtil {
    public static Minecraft minecraft = Minecraft.getInstance();

    public static Window getWindow() {
        return minecraft.getWindow();
    }

    public static boolean isDebugScreenOn() {
        return minecraft.options.hideGui;
    }

    public static int getWidth() {
        return getWindow().getGuiScaledWidth();
    }
    public static int getHeight() {
        return getWindow().getGuiScaledHeight();
    }

    public static Player getPlayer() {
        return minecraft.player;
    }
    public static Level getLevel() {
        return minecraft.level;
    }

    public static ResourceKey<Level> getDimension() {
        return getLevel().dimension();
    }

    public static boolean inDimension(ResourceKey<Level> dimension) {
        return getDimension() == dimension;
    }

    public static void playSound(SoundEvent sound, float volume, float pitch) {
        if (getPlayer() == null) return;
        getPlayer().playSound(sound, volume, pitch);
    }
}
