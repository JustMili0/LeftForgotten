package net.justmili.leftforgotten.content.mechanics.gameplay;

import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.registries.extra.LFResources;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class ApplyProgrammerArt {
    static boolean shouldResetProgrammerArt = false;
    static String resourcePack = getPack();

    public static void onChangeDimension(ServerPlayer player, ResourceKey<Level> fromLevel, ResourceKey<Level> toLevel) {
        if (!Config.forceOldPack.get()) return;
        if (!resourcePack.contains("golden_days") && ClientUtil.arePackLoaded("golden_days", "golden_days_alpha", "golden_days_beta")) return;

        if (toLevel == LFResources.ALPHA_MINECRAFT) setupProgrammerArt();
        if (fromLevel == LFResources.ALPHA_MINECRAFT) clearProgrammerArt();
    }
    public static void onPlayerJoin(ServerPlayer player) {
        if (player.level().dimension().equals(LFResources.ALPHA_MINECRAFT)) shouldResetProgrammerArt = true;
    }

    static String getPack() {
        if (!Config.overrideOldPack.get().isEmpty()) return Config.overrideOldPack.get();
        return "programmer_art";
    }

    static void setupProgrammerArt() {
        if (ClientUtil.addPackAndTell(resourcePack)) {
            ClientUtil.reloadPacks();
            shouldResetProgrammerArt = true;
        } else {
            shouldResetProgrammerArt = false;
        }
    }
    static void clearProgrammerArt() {
        if (shouldResetProgrammerArt) ClientUtil.removePack(resourcePack);
    }
}