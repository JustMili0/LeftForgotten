package net.justmili.leftforgotten.core.references;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.libs.v1.references.BlockItemId;

public class LFBlockItemIds {
    public static final BlockItemId BRITTLE_BEDROCK = create("brittle_bedrock");
    public static final BlockItemId GRASS_BLOCK = create("grass_block");
    public static final BlockItemId DIRT = create("dirt");
    public static final BlockItemId FARMLAND = create("farmland");
    public static final BlockItemId GRAVEL = create("gravel");
    public static final BlockItemId SAND = create("sand");
    public static final BlockItemId CLAY = create("clay");
    public static final BlockItemId RED_FLOWER = create("red_flower");
    public static final BlockItemId YELLOW_FLOWER = create("yellow_flower");
    public static final BlockItemId RED_MUSHROOM = create("red_mushroom");
    public static final BlockItemId BROWN_MUSHROOM = create("brown_mushroom");
    public static final BlockItemId CACTUS = create("cactus");
    public static final BlockItemId SAPLING = create("sapling");
    public static final BlockItemId LEAVES = create("leaves");
    public static final BlockItemId WOOD = create("wood");
    public static final BlockItemId WOOD_6_SIDED = create("wood_6_sided");
    public static final BlockItemId WOODEN_PLANKS = create("wooden_planks");
    public static final BlockItemId WOODEN_STAIRS = create("wooden_stairs");
    public static final BlockItemId WOODEN_SLAB = create("wooden_slab");
    public static final BlockItemId FENCE = create("fence");
    public static final BlockItemId FENCE_GATE = create("fence_gate");
    public static final BlockItemId DOOR = create("door");
    public static final BlockItemId TRAPDOOR = create("trapdoor");
    public static final BlockItemId PRESSURE_PLATE = create("pressure_plate");
    public static final BlockItemId BUTTON = create("button");
    public static final BlockItemId COAL_ORE = create("coal_ore");
    public static final BlockItemId IRON_ORE = create("iron_ore");
    public static final BlockItemId GOLD_ORE = create("gold_ore");
    public static final BlockItemId REDSTONE_ORE = create("redstone_ore");
    public static final BlockItemId DIAMOND_ORE = create("diamond_ore");
    public static final BlockItemId STONE = create("stone");
    public static final BlockItemId STONE_STAIRS = create("stone_stairs");
    public static final BlockItemId STONE_SLAB = create("stone_slab");
    public static final BlockItemId STONE_PRESSURE_PLATE = create("stone_pressure_plate");
    public static final BlockItemId STONE_BUTTON = create("stone_button");
    public static final BlockItemId COBBLESTONE = create("cobblestone");
    public static final BlockItemId COBBLESTONE_STAIRS = create("cobblestone_stairs");
    public static final BlockItemId COBBLESTONE_SLAB = create("cobblestone_slab");
    public static final BlockItemId COBBLESTONE_WALL = create("cobblestone_wall");
    public static final BlockItemId MOSSY_COBBLESTONE = create("mossy_cobblestone");
    public static final BlockItemId MOSSY_COBBLESTONE_STAIRS = create("mossy_cobblestone_stairs");
    public static final BlockItemId MOSSY_COBBLESTONE_SLAB = create("mossy_cobblestone_slab");
    public static final BlockItemId MOSSY_COBBLESTONE_WALL = create("mossy_cobblestone_wall");
    public static final BlockItemId BRICKS = create("bricks");
    public static final BlockItemId BRICK_STAIRS = create("brick_stairs");
    public static final BlockItemId BRICK_SLAB = create("brick_slab");
    public static final BlockItemId BRICK_WALL = create("brick_wall");
    public static final BlockItemId OBSIDIAN = create("obsidian");
    public static final BlockItemId IRON_BLOCK = create("iron_block");
    public static final BlockItemId GOLD_BLOCK = create("gold_block");
    public static final BlockItemId DIAMOND_BLOCK = create("diamond_block");
    public static final BlockItemId BOOKSHELF = create("bookshelf");
    public static final BlockItemId GLASS = create("glass");
    public static final BlockItemId GLASS_PANE = create("glass_pane");
    public static final BlockItemId TNT = create("tnt");
    public static final BlockItemId IRON_DOOR = create("iron_door");
    public static final BlockItemId FEATURE_VOID = create("feature_void");
    public static final BlockItemId RMDL_FURNACE_STONE = create("rmdl_furnace_pre_beta");
    public static final BlockItemId RMDL_FURNACE = create("rmdl_furnace_post_beta");
    public static final BlockItemId RMDL_CRAFTING = create("rmdl_crafting_table");
    public static final BlockItemId RMDL_CHEST = create("rmdl_chest");

    private static BlockItemId create(String path) {
        var id = LeftForgotten.asId(path);
        return BlockItemId.create(id, id);
    }
}