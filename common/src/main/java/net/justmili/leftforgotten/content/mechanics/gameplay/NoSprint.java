package net.justmili.leftforgotten.content.mechanics.gameplay;

import net.justmili.leftforgotten.registries.extra.LFResources;
import net.minecraft.world.entity.player.Player;

public class NoSprint {
    public static void onPlayerTick(Player player) {
        if (player.level().dimension() == LFResources.ALPHA_MINECRAFT) {
            player.getFoodData().setFoodLevel(4);
            player.getFoodData().setSaturation(0.0F);
        }
    }
}