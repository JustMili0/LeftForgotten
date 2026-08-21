package net.justmili.leftforgotten.libs.v1.utils.client;

import com.mojang.blaze3d.platform.Window;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

@Environment(EnvType.CLIENT)
public class ClientUtil {
    private static Minecraft client = Minecraft.getInstance();

    public static Minecraft client() {
        return client;
    }

    public static Gui gui() {
        return client.gui;
    }

    public static Font font() {
        return client.font;
    }

    public static Window window() {
        return client.getWindow();
    }

    public static int width() {
        return window().getGuiScaledWidth();
    }

    public static int height() {
        return window().getGuiScaledHeight();
    }

    public static boolean isDebugScreenOn() {
        return shouldHideGui();
    }

    public static boolean notSurvivalOrHideGui() {
        return isNotSurvival() || shouldHideGui();
    }

    public static boolean isNotSurvival() {
        if (client.gameMode == null) return false;
        return !(client.gameMode.canHurtPlayer() && client.getCameraEntity() instanceof Player);
    }

    public static boolean shouldHideGui() {
        return client.options.hideGui;
    }

    public static Player player() {
        return client.player;
    }

    public static boolean isCreative() {
        return player() != null && player().isCreative();
    }

    public static boolean isSpectator() {
        return player() != null && player().isSpectator();
    }

    public static boolean isSurvival() {
        return !isNotSurvival();
    }

    public static Level level() {
        return client.level;
    }

    public static ResourceKey<Level> dimension() {
        if (player() == null) return Level.OVERWORLD;
        return player().level().dimension();
    }

    public static boolean inDimension(ResourceKey<Level> dimension) {
        return dimension() == dimension;
    }

    public static void playSound(SoundEvent sound, float volume, float pitch) {
        if (player() == null) return;
        player().playSound(sound, volume, pitch);
    }

    public static boolean isPackLoaded(String pack) {
        return client.getResourcePackRepository().isAvailable(pack);
    }

    public static boolean arePackLoaded(String... packs) {
        for (String pack : packs) {
            if (isPackLoaded(pack)) return true;
        }
        return false;
    }

    public static boolean addPackAndTell(String pack) {
        // Add resource pack and tell if it was loaded or not
        return client.getResourcePackRepository().addPack(pack);
    }

    public static void removePack(String pack) {
        client.getResourcePackRepository().removePack(pack);
        reloadPacks();
    }

    public static void reloadPacks() {
        client.reloadResourcePacks();
    }
}