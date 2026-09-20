package net.justmili.leftforgotten.core.registries;

import com.google.common.collect.Streams;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.content.block.*;
import net.justmili.leftforgotten.content.block.dev.FeatureVoid;
import net.justmili.leftforgotten.content.block.dev.RemodelChest;
import net.justmili.leftforgotten.content.block.dev.RemodelCraftingTable;
import net.justmili.leftforgotten.content.block.dev.RemodelFurnace;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(LeftForgotten.MODID, Registries.BLOCK);
    public static final RegistrySupplier<Block>
        BRITTLE_BEDROCK, GRASS_BLOCK, DIRT, FARMLAND, GRAVEL, SAND, CLAY,
        RED_FLOWER, YELLOW_FLOWER, RED_MUSHROOM, BROWN_MUSHROOM, CACTUS, SAPLING, LEAVES,
        WOOD, WOOD_6_SIDED, WOODEN_PLANKS, WOODEN_STAIRS, WOODEN_SLAB, FENCE, FENCE_GATE, DOOR, TRAPDOOR, PRESSURE_PLATE, BUTTON,
        COAL_ORE, IRON_ORE, GOLD_ORE, REDSTONE_ORE, DIAMOND_ORE,
        STONE, STONE_STAIRS, STONE_SLAB, STONE_PRESSURE_PLATE, STONE_BUTTON,
        COBBLESTONE, COBBLESTONE_STAIRS, COBBLESTONE_SLAB, COBBLESTONE_WALL,
        MOSSY_COBBLESTONE, MOSSY_COBBLESTONE_STAIRS, MOSSY_COBBLESTONE_SLAB, MOSSY_COBBLESTONE_WALL,
        BRICKS, BRICK_STAIRS, BRICK_SLAB, BRICK_WALL,
        OBSIDIAN, IRON_BLOCK, GOLD_BLOCK, DIAMOND_BLOCK,
        BOOKSHELF, GLASS, GLASS_PANE, TNT, IRON_DOOR,
        FEATURE_VOID, REMODEL_FURNACE_STONE, REMODEL_FURNACE, REMODEL_CRAFTING_TABLE, REMODEL_CHEST;

    static {
        // In-Overworld
        BRITTLE_BEDROCK = block("brittle_bedrock", BrittleBedrock::new);

        // Nature / Ground
        GRASS_BLOCK = block("grass_block", GrassBlock::new);
        DIRT = block("dirt", Dirt::new);
        FARMLAND = block("farmland", Farmland::new);
        GRAVEL = block("gravel", Gravel::new);
        SAND = block("sand", Sand::new);
        CLAY = block("clay", Clay::new);

        // Nature / Vegetation
        RED_FLOWER = block("red_flower", RedFlower::new);
        YELLOW_FLOWER = block("yellow_flower", YellowFlower::new);
        RED_MUSHROOM = block("red_mushroom", RedMushroom::new);
        BROWN_MUSHROOM = block("brown_mushroom", BrownMushroom::new);
        CACTUS = block("cactus", Cactus::new);
        SAPLING = block("sapling", Sapling::new);
        LEAVES = block("leaves", Leaves::new);

        // Building / Wood
        WOOD = block("wood", Wood::new);
        WOOD_6_SIDED = block("wood_6_sided", Wood6Sided::new);
        WOODEN_PLANKS = block("wooden_planks", WoodenPlanks::new);
        WOODEN_STAIRS = block("wooden_stairs", WoodenStairs::new);
        WOODEN_SLAB = block("wooden_slab", WoodenSlab::new);
        FENCE = block("fence", WoodenFence::new);
        FENCE_GATE = block("fence_gate", WoodenFenceGate::new);
        DOOR = block("door", WoodenDoor::new);
        TRAPDOOR = block("trapdoor", WoodenTrapdoor::new);
        PRESSURE_PLATE = block("pressure_plate", WoodenPressurePlate::new);
        BUTTON = block("button", WoodenButton::new);

        // Nature / Underground
        COAL_ORE = block("coal_ore", CoalOre::new);
        IRON_ORE = block("iron_ore", IronOre::new);
        GOLD_ORE = block("gold_ore", GoldOre::new);
        REDSTONE_ORE = block("redstone_ore", RedstoneOre::new);
        DIAMOND_ORE = block("diamond_ore", DiamondOre::new);
        STONE = block("stone", Stone::new);

        // Building / Stone
        STONE_STAIRS = block("stone_stairs", StoneStairs::new);
        STONE_SLAB = block("stone_slab", StoneSlab::new);
        STONE_PRESSURE_PLATE = block("stone_pressure_plate", StonePressurePlate::new);
        STONE_BUTTON = block("stone_button", StoneButton::new);
        COBBLESTONE = block("cobblestone", Cobblestone::new);
        COBBLESTONE_STAIRS = block("cobblestone_stairs", CobblestoneStairs::new);
        COBBLESTONE_SLAB = block("cobblestone_slab", CobblestoneSlab::new);
        COBBLESTONE_WALL = block("cobblestone_wall", CobblestoneWall::new);
        MOSSY_COBBLESTONE = block("mossy_cobblestone", MossyCobblestone::new);
        MOSSY_COBBLESTONE_STAIRS = block("mossy_cobblestone_stairs", MossyCobblestoneStairs::new);
        MOSSY_COBBLESTONE_SLAB = block("mossy_cobblestone_slab", MossyCobblestoneSlab::new);
        MOSSY_COBBLESTONE_WALL = block("mossy_cobblestone_wall", MossyCobblestoneWall::new);
        BRICKS = block("bricks", Bricks::new);
        BRICK_STAIRS = block("brick_stairs", BrickStairs::new);
        BRICK_SLAB = block("brick_slab", BrickSlab::new);
        BRICK_WALL = block("brick_wall", BrickWall::new);

        // Building / Deco
        OBSIDIAN = block("obsidian", Obsidian::new);
        IRON_BLOCK = block("iron_block", IronBlock::new);
        GOLD_BLOCK = block("gold_block", GoldBlock::new);
        DIAMOND_BLOCK = block("diamond_block", DiamondBlock::new);
        BOOKSHELF = block("bookshelf", Bookshelf::new);
        GLASS = block("glass", Glass::new);
        GLASS_PANE = block("glass_pane", GlassPane::new);
        TNT = block("tnt", Tnt::new);
        IRON_DOOR = block("iron_door", IronDoor::new);

        FEATURE_VOID = block("feature_void", FeatureVoid::new);
        REMODEL_FURNACE_STONE = block("furnace_pre_beta", RemodelFurnace::new);
        REMODEL_FURNACE = block("furnace_post_beta", RemodelFurnace::new);
        REMODEL_CRAFTING_TABLE = block("remodel_crafting_table", RemodelCraftingTable::new);
        REMODEL_CHEST = block("remodel_chest", RemodelChest::new);
    }

    private static RegistrySupplier<Block> block(String key, Supplier<Block> block) {
        return REGISTRY.register(key, block);
    }

    public static void register() {
        REGISTRY.register();
    }

    public static Block[] getBlocksFromRegistry() {
        return Streams.stream(REGISTRY).filter(Objects::nonNull).map(Supplier::get).toArray(Block[]::new);
    }
}