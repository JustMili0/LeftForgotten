package net.justmili.leftforgotten.content.mechanics.logic;

import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class FoodLvlReset {
    public static void onChangeDimension(ServerPlayer player, ResourceKey<Level> fromDimension, ResourceKey<Level> toDimension) {
        if (fromDimension == LFResources.Levels.ALPHA_MINECRAFT && toDimension == Level.OVERWORLD) {
            player.getFoodData().setFoodLevel(20);
            player.getFoodData().setSaturation(10.0f);
        }
    }
}
