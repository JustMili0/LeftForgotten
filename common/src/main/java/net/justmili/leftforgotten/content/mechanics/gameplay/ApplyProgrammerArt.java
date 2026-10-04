package net.justmili.leftforgotten.content.mechanics.gameplay;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

@Environment(EnvType.CLIENT)
public class ApplyProgrammerArt {
    private static boolean shouldResetProgrammerArt = false;
    private static final String resourcePack = "programmer_art";

    public static void onChangeDimension(ServerPlayer player, ResourceKey<Level> fromLevel, ResourceKey<Level> toLevel) {
        if (!Config.applyProgrammerArt.get()) return;
        if (!ClientUtil.arePackLoaded("golden_days", "golden_days_alpha", "golden_days_beta")) return;

        if (Versions.isOldVersion(Versions.get(player, toLevel))) setupProgrammerArt();
        if (Versions.isOldVersion(Versions.get(player, fromLevel))) clearProgrammerArt();
    }

    public static void onPlayerJoin(ServerPlayer player) {
        if (Versions.isOldVersion(player.level())) shouldResetProgrammerArt = true;
    }

    private static void setupProgrammerArt() {
        if (ClientUtil.addPackAndTell(resourcePack)) {
            ClientUtil.reloadPacks();
            shouldResetProgrammerArt = true;
        } else {
            shouldResetProgrammerArt = false;
        }
    }

    private static void clearProgrammerArt() {
        if (shouldResetProgrammerArt) ClientUtil.removePack(resourcePack);
    }
}