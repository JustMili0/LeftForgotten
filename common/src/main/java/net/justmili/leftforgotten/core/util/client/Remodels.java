package net.justmili.leftforgotten.core.util.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.justmili.leftforgotten.core.registries.ItemRegistry;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

@Environment(EnvType.CLIENT)
public class Remodels {

    public static Block of(Block block) {
        if (block == Blocks.CHEST && chests()) return BlockRegistry.RMDL_CHEST.get();
        if (block == Blocks.CRAFTING_TABLE && workstations()) return BlockRegistry.RMDL_CRAFTING.get();
        if (block == Blocks.FURNACE && workstations()) return stoneFurnace()? BlockRegistry.RMDL_FURNACE_STONE.get() : BlockRegistry.RMDL_FURNACE.get();
        return block;
    }

    public static Item of(Item item) {
        if (item == Items.CHEST && chests()) return ItemRegistry.RMDL_CHEST.get();
        if (item == Items.CRAFTING_TABLE && workstations()) return ItemRegistry.RMDL_CRAFTING.get();
        if (item == Items.FURNACE && workstations()) return stoneFurnace()? ItemRegistry.RMDL_FURNACE_STONE.get() : ItemRegistry.RMDL_FURNACE.get();
        return item;
    }

    private static boolean chests() {
        return Config.chestRemodel.get() && Versions.hadBlockyChests(ClientUtil.level());
    }

    private static boolean workstations() {
        return Config.workstationRemodel.get() && Versions.isOldVersion(ClientUtil.level());
    }

    private static boolean stoneFurnace() {
        return Versions.upToAlpha(ClientUtil.level());
    }
}