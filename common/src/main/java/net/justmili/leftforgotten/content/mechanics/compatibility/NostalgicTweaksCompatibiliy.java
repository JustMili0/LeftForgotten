package net.justmili.leftforgotten.content.mechanics.compatibility;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.util.Versions;
import net.minecraft.world.entity.player.Player;

public class NostalgicTweaksCompatibiliy {
    public static boolean justOut = false;

    public static void onPlayerTick(Player player) {
        // check for client side
        if (!Platform.isModLoaded("nostalgic_tweaks")) return;
        if (Versions.hadVersionOverlay(player.level().dimension())) {
            loadVars();
            justOut = true;
        } else {
            if (justOut) {
                justOut = false;
                restoreVars();
            }
            storeVars();
        }
    }

    @ExpectPlatform
    public static void loadVars() {

    }

    @ExpectPlatform
    public static void restoreVars() {

    }

    @ExpectPlatform
    public static void storeVars() {

    }
}
