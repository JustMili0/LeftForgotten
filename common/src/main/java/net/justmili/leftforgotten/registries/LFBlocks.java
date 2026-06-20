package net.justmili.leftforgotten.registries;

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

public class LFBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(LeftForgotten.MODID, Registries.BLOCK);

    // In-Overworld
    public static final RegistrySupplier<Block> BRITTLE_BEDROCK = REGISTRY.register("brittle_bedrock", BrittleBedrock::new);

    // Nature / Ground
    public static final RegistrySupplier<Block> GRASS_BLOCK = REGISTRY.register("grass_block", GrassBlock::new);
    public static final RegistrySupplier<Block> DIRT = REGISTRY.register("dirt", Dirt::new);
    public static final RegistrySupplier<Block> FARMLAND = REGISTRY.register("farmland", Farmland::new);
    public static final RegistrySupplier<Block> GRAVEL = REGISTRY.register("gravel", Gravel::new);
    public static final RegistrySupplier<Block> SAND = REGISTRY.register("sand", Sand::new);
    public static final RegistrySupplier<Block> CLAY = REGISTRY.register("clay", Clay::new);

    // Nature / Vegetation
    public static final RegistrySupplier<Block> RED_FLOWER = REGISTRY.register("red_flower", RedFlower::new);
    public static final RegistrySupplier<Block> YELLOW_FLOWER = REGISTRY.register("yellow_flower", YellowFlower::new);
    public static final RegistrySupplier<Block> RED_MUSHROOM = REGISTRY.register("red_mushroom", RedMushroom::new);
    public static final RegistrySupplier<Block> BROWN_MUSHROOM = REGISTRY.register("brown_mushroom", BrownMushroom::new);
    public static final RegistrySupplier<Block> CACTUS = REGISTRY.register("cactus", Cactus::new);
    public static final RegistrySupplier<Block> SAPLING = REGISTRY.register("sapling", Sapling::new);
    public static final RegistrySupplier<Block> LEAVES = REGISTRY.register("leaves", Leaves::new);

    // Building / Wood
    public static final RegistrySupplier<Block> WOOD = REGISTRY.register("wood", Wood::new);
    public static final RegistrySupplier<Block> WOOD_6_SIDED = REGISTRY.register("wood_6_sided", Wood6Sided::new);
    public static final RegistrySupplier<Block> WOODEN_PLANKS = REGISTRY.register("wooden_planks", WoodenPlanks::new);
    public static final RegistrySupplier<Block> WOODEN_STAIRS = REGISTRY.register("wooden_stairs", WoodenStairs::new);
    public static final RegistrySupplier<Block> WOODEN_SLAB = REGISTRY.register("wooden_slab", WoodenSlab::new);
    public static final RegistrySupplier<Block> FENCE = REGISTRY.register("fence", WoodenFence::new);
    public static final RegistrySupplier<Block> FENCE_GATE = REGISTRY.register("fence_gate", WoodenFenceGate::new);
    public static final RegistrySupplier<Block> DOOR = REGISTRY.register("door", WoodenDoor::new);
    public static final RegistrySupplier<Block> TRAPDOOR = REGISTRY.register("trapdoor", WoodenTrapdoor::new);
    public static final RegistrySupplier<Block> PRESSURE_PLATE = REGISTRY.register("pressure_plate", WoodenPressurePlate::new);
    public static final RegistrySupplier<Block> BUTTON = REGISTRY.register("button", WoodenButton::new);

    // Nature / Underground
    public static final RegistrySupplier<Block> COAL_ORE = REGISTRY.register("coal_ore", CoalOre::new);
    public static final RegistrySupplier<Block> IRON_ORE = REGISTRY.register("iron_ore", IronOre::new);
    public static final RegistrySupplier<Block> GOLD_ORE = REGISTRY.register("gold_ore", GoldOre::new);
    public static final RegistrySupplier<Block> REDSTONE_ORE = REGISTRY.register("redstone_ore", RedstoneOre::new);
    public static final RegistrySupplier<Block> DIAMOND_ORE = REGISTRY.register("diamond_ore", DiamondOre::new);
    public static final RegistrySupplier<Block> STONE = REGISTRY.register("stone", Stone::new);

    // Building / Stone
    public static final RegistrySupplier<Block> STONE_STAIRS = REGISTRY.register("stone_stairs", StoneStairs::new);
    public static final RegistrySupplier<Block> STONE_SLAB = REGISTRY.register("stone_slab", StoneSlab::new);
    public static final RegistrySupplier<Block> STONE_PRESSURE_PLATE = REGISTRY.register("stone_pressure_plate", StonePressurePlate::new);
    public static final RegistrySupplier<Block> STONE_BUTTON = REGISTRY.register("stone_button", StoneButton::new);
    public static final RegistrySupplier<Block> COBBLESTONE = REGISTRY.register("cobblestone", Cobblestone::new);
    public static final RegistrySupplier<Block> COBBLESTONE_STAIRS = REGISTRY.register("cobblestone_stairs", CobblestoneStairs::new);
    public static final RegistrySupplier<Block> COBBLESTONE_SLAB = REGISTRY.register("cobblestone_slab", CobblestoneSlab::new);
    public static final RegistrySupplier<Block> COBBLESTONE_WALL = REGISTRY.register("cobblestone_wall", CobblestoneWall::new);
    public static final RegistrySupplier<Block> MOSSY_COBBLESTONE = REGISTRY.register("mossy_cobblestone", MossyCobblestone::new);
    public static final RegistrySupplier<Block> MOSSY_COBBLESTONE_STAIRS = REGISTRY.register("mossy_cobblestone_stairs", MossyCobblestoneStairs::new);
    public static final RegistrySupplier<Block> MOSSY_COBBLESTONE_SLAB = REGISTRY.register("mossy_cobblestone_slab", MossyCobblestoneSlab::new);
    public static final RegistrySupplier<Block> MOSSY_COBBLESTONE_WALL = REGISTRY.register("mossy_cobblestone_wall", MossyCobblestoneWall::new);
    public static final RegistrySupplier<Block> BRICKS = REGISTRY.register("bricks", Bricks::new);
    public static final RegistrySupplier<Block> BRICK_STAIRS = REGISTRY.register("brick_stairs", BrickStairs::new);
    public static final RegistrySupplier<Block> BRICK_SLAB = REGISTRY.register("brick_slab", BrickSlab::new);
    public static final RegistrySupplier<Block> BRICK_WALL = REGISTRY.register("brick_wall", BrickWall::new);

    // Building / Deco
    public static final RegistrySupplier<Block> OBSIDIAN = REGISTRY.register("obsidian", Obsidian::new);
    public static final RegistrySupplier<Block> IRON_BLOCK = REGISTRY.register("iron_block", IronBlock::new);
    public static final RegistrySupplier<Block> GOLD_BLOCK = REGISTRY.register("gold_block", GoldBlock::new);
    public static final RegistrySupplier<Block> DIAMOND_BLOCK = REGISTRY.register("diamond_block", DiamondBlock::new);
    public static final RegistrySupplier<Block> BOOKSHELF = REGISTRY.register("bookshelf", Bookshelf::new);
    public static final RegistrySupplier<Block> GLASS = REGISTRY.register("glass", Glass::new);
    public static final RegistrySupplier<Block> GLASS_PANE = REGISTRY.register("glass_pane", GlassPane::new);
    public static final RegistrySupplier<Block> TNT = REGISTRY.register("tnt", Tnt::new);

    // Building / Iron
    public static final RegistrySupplier<Block> IRON_DOOR = REGISTRY.register("iron_door", IronDoor::new);

    // Dev
    public static final RegistrySupplier<Block> FEATURE_VOID = REGISTRY.register("feature_void", FeatureVoid::new);
    public static final RegistrySupplier<Block> REMODEL_FURNACE = REGISTRY.register("remodel_furnace", RemodelFurnace::new);
    public static final RegistrySupplier<Block> REMODEL_CRAFTING_TABLE = REGISTRY.register("remodel_crafting_table", RemodelCraftingTable::new);
    public static final RegistrySupplier<Block> REMODEL_CHEST = REGISTRY.register("remodel_chest", RemodelChest::new);

    public static void register() {
        REGISTRY.register();
    }
}