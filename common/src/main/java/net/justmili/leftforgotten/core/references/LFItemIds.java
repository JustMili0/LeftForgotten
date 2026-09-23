package net.justmili.leftforgotten.core.references;

import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class LFItemIds {
    public static final ResourceKey<Item> CLAY_BALL = create("clay_ball");
    public static final ResourceKey<Item> BRICK = create("brick");
    public static final ResourceKey<Item> BOAT = create("boat");

    private static ResourceKey<Item> create(String path) {
        return ResourceKey.create(Registries.ITEM, LeftForgotten.asId(path));
    }
}
