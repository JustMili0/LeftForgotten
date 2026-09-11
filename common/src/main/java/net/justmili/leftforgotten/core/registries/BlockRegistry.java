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

    public static final RegistrySupplier<Block> BRITTLE_BEDROCK,
        GRASS_BLOCK, DIRT, FARMLAND, GRAVEL, SAND, CLAY,
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
        BRITTLE_BEDROCK = registerBlock("brittle_bedrock", BrittleBedrock::new);

        // Nature / Ground
        GRASS_BLOCK = registerBlock("grass_block", GrassBlock::new);
        DIRT = registerBlock("dirt", Dirt::new);
        FARMLAND = registerBlock("farmland", Farmland::new);
        GRAVEL = registerBlock("gravel", Gravel::new);
        SAND = registerBlock("sand", Sand::new);
        CLAY = registerBlock("clay", Clay::new);

        // Nature / Vegetation
        RED_FLOWER = registerBlock("red_flower", RedFlower::new);
        YELLOW_FLOWER = registerBlock("yellow_flower", YellowFlower::new);
        RED_MUSHROOM = registerBlock("red_mushroom", RedMushroom::new);
        BROWN_MUSHROOM = registerBlock("brown_mushroom", BrownMushroom::new);
        CACTUS = registerBlock("cactus", Cactus::new);
        SAPLING = registerBlock("sapling", Sapling::new);
        LEAVES = registerBlock("leaves", Leaves::new);

        // Building / Wood
        WOOD = registerBlock("wood", Wood::new);
        WOOD_6_SIDED = registerBlock("wood_6_sided", Wood6Sided::new);
        WOODEN_PLANKS = registerBlock("wooden_planks", WoodenPlanks::new);
        WOODEN_STAIRS = registerBlock("wooden_stairs", WoodenStairs::new);
        WOODEN_SLAB = registerBlock("wooden_slab", WoodenSlab::new);
        FENCE = registerBlock("fence", WoodenFence::new);
        FENCE_GATE = registerBlock("fence_gate", WoodenFenceGate::new);
        DOOR = registerBlock("door", WoodenDoor::new);
        TRAPDOOR = registerBlock("trapdoor", WoodenTrapdoor::new);
        PRESSURE_PLATE = registerBlock("pressure_plate", WoodenPressurePlate::new);
        BUTTON = registerBlock("button", WoodenButton::new);

        // Nature / Underground
        COAL_ORE = registerBlock("coal_ore", CoalOre::new);
        IRON_ORE = registerBlock("iron_ore", IronOre::new);
        GOLD_ORE = registerBlock("gold_ore", GoldOre::new);
        REDSTONE_ORE = registerBlock("redstone_ore", RedstoneOre::new);
        DIAMOND_ORE = registerBlock("diamond_ore", DiamondOre::new);
        STONE = registerBlock("stone", Stone::new);

        // Building / Stone
        STONE_STAIRS = registerBlock("stone_stairs", StoneStairs::new);
        STONE_SLAB = registerBlock("stone_slab", StoneSlab::new);
        STONE_PRESSURE_PLATE = registerBlock("stone_pressure_plate", StonePressurePlate::new);
        STONE_BUTTON = registerBlock("stone_button", StoneButton::new);
        COBBLESTONE = registerBlock("cobblestone", Cobblestone::new);
        COBBLESTONE_STAIRS = registerBlock("cobblestone_stairs", CobblestoneStairs::new);
        COBBLESTONE_SLAB = registerBlock("cobblestone_slab", CobblestoneSlab::new);
        COBBLESTONE_WALL = registerBlock("cobblestone_wall", CobblestoneWall::new);
        MOSSY_COBBLESTONE = registerBlock("mossy_cobblestone", MossyCobblestone::new);
        MOSSY_COBBLESTONE_STAIRS = registerBlock("mossy_cobblestone_stairs", MossyCobblestoneStairs::new);
        MOSSY_COBBLESTONE_SLAB = registerBlock("mossy_cobblestone_slab", MossyCobblestoneSlab::new);
        MOSSY_COBBLESTONE_WALL = registerBlock("mossy_cobblestone_wall", MossyCobblestoneWall::new);
        BRICKS = registerBlock("bricks", Bricks::new);
        BRICK_STAIRS = registerBlock("brick_stairs", BrickStairs::new);
        BRICK_SLAB = registerBlock("brick_slab", BrickSlab::new);
        BRICK_WALL = registerBlock("brick_wall", BrickWall::new);

        // Building / Deco
        OBSIDIAN = registerBlock("obsidian", Obsidian::new);
        IRON_BLOCK = registerBlock("iron_block", IronBlock::new);
        GOLD_BLOCK = registerBlock("gold_block", GoldBlock::new);
        DIAMOND_BLOCK = registerBlock("diamond_block", DiamondBlock::new);
        BOOKSHELF = registerBlock("bookshelf", Bookshelf::new);
        GLASS = registerBlock("glass", Glass::new);
        GLASS_PANE = registerBlock("glass_pane", GlassPane::new);
        TNT = registerBlock("tnt", Tnt::new);

        IRON_DOOR = registerBlock("iron_door", IronDoor::new);
        FEATURE_VOID = registerBlock("feature_void", FeatureVoid::new);
        REMODEL_FURNACE_STONE = registerBlock("furnace_pre_beta", RemodelFurnace::new);
        REMODEL_FURNACE = registerBlock("furnace_post_beta", RemodelFurnace::new); // TODO: Make own class & datagen
        REMODEL_CRAFTING_TABLE = registerBlock("remodel_crafting_table", RemodelCraftingTable::new);
        REMODEL_CHEST = registerBlock("remodel_chest", RemodelChest::new);
    }

    private static RegistrySupplier<Block> registerBlock(String key, Supplier<Block> block) {
        return REGISTRY.register(key, block);
    }

    public static void register() {
        REGISTRY.register();
    }

    public static Block[] getBlocksFromRegistry() {
        return Streams.stream(BlockRegistry.REGISTRY).filter(Objects::nonNull).map(Supplier::get).toArray(Block[]::new);
    }
}