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
        return minecraft.getDebugOverlay().showDebugScreen();
    }

    public static int getWidth() {
        return getWindow().getGuiScaledWidth();
    }
    public static int getHeight() {
        return getWindow().getGuiScaledHeight();
    }

    public static boolean notSurvivalOrHideGui() {
        return isNotSurvival() || shouldHideGui();
    }
    public static boolean isNotSurvival() {
        if (minecraft.gameMode == null) return false;
        return !(minecraft.gameMode.canHurtPlayer() && minecraft.getCameraEntity() instanceof Player);
    }
    public static boolean shouldHideGui() {
        return minecraft.options.hideGui;
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

    public static boolean isPackLoaded(String pack) {
        return minecraft.getResourcePackRepository().isAvailable(pack);
    }
    public static boolean arePackLoaded(String... packs) {
        for (String pack : packs) {
            if (isPackLoaded(pack)) return true;
        }
        return false;
    }
    public static boolean addPackAndTell(String pack) {
        // Add resource pack and tell if it was loaded or not
        return minecraft.getResourcePackRepository().addPack(pack);
    }
    public static void removePack(String pack) {
        minecraft.getResourcePackRepository().removePack(pack);
        reloadPacks();
    }
    public static void reloadPacks() {
        minecraft.reloadResourcePacks();
    }
}
