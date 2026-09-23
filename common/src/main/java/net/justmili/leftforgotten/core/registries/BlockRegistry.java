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
import net.justmili.leftforgotten.core.references.LFBlockItemIds;
import net.justmili.leftforgotten.libs.v1.references.BlockItemId;
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
        FEATURE_VOID, RMDL_FURNACE, RMDL_FURNACE_STONE, RMDL_CRAFTING, RMDL_CHEST;

    static {
        // In-Overworld
        BRITTLE_BEDROCK = block(LFBlockItemIds.BRITTLE_BEDROCK, BrittleBedrock::new);

        // Nature / Ground
        GRASS_BLOCK = block(LFBlockItemIds.GRASS_BLOCK, GrassBlock::new);
        DIRT = block(LFBlockItemIds.DIRT, Dirt::new);
        FARMLAND = block(LFBlockItemIds.FARMLAND, Farmland::new);
        GRAVEL = block(LFBlockItemIds.GRAVEL, Gravel::new);
        SAND = block(LFBlockItemIds.SAND, Sand::new);
        CLAY = block(LFBlockItemIds.CLAY, Clay::new);

        // Nature / Vegetation
        RED_FLOWER = block(LFBlockItemIds.RED_FLOWER, RedFlower::new);
        YELLOW_FLOWER = block(LFBlockItemIds.YELLOW_FLOWER, YellowFlower::new);
        RED_MUSHROOM = block(LFBlockItemIds.RED_MUSHROOM, RedMushroom::new);
        BROWN_MUSHROOM = block(LFBlockItemIds.BROWN_MUSHROOM, BrownMushroom::new);
        CACTUS = block(LFBlockItemIds.CACTUS, Cactus::new);
        SAPLING = block(LFBlockItemIds.SAPLING, Sapling::new);
        LEAVES = block(LFBlockItemIds.LEAVES, Leaves::new);

        // Building / Wood
        WOOD = block(LFBlockItemIds.WOOD, Wood::new);
        WOOD_6_SIDED = block(LFBlockItemIds.WOOD_6_SIDED, Wood6Sided::new);
        WOODEN_PLANKS = block(LFBlockItemIds.WOODEN_PLANKS, WoodenPlanks::new);
        WOODEN_STAIRS = block(LFBlockItemIds.WOODEN_STAIRS, WoodenStairs::new);
        WOODEN_SLAB = block(LFBlockItemIds.WOODEN_SLAB, WoodenSlab::new);
        FENCE = block(LFBlockItemIds.FENCE, WoodenFence::new);
        FENCE_GATE = block(LFBlockItemIds.FENCE_GATE, WoodenFenceGate::new);
        DOOR = block(LFBlockItemIds.DOOR, WoodenDoor::new);
        TRAPDOOR = block(LFBlockItemIds.TRAPDOOR, WoodenTrapdoor::new);
        PRESSURE_PLATE = block(LFBlockItemIds.PRESSURE_PLATE, WoodenPressurePlate::new);
        BUTTON = block(LFBlockItemIds.BUTTON, WoodenButton::new);

        // Nature / Underground
        COAL_ORE = block(LFBlockItemIds.COAL_ORE, CoalOre::new);
        IRON_ORE = block(LFBlockItemIds.IRON_ORE, IronOre::new);
        GOLD_ORE = block(LFBlockItemIds.GOLD_ORE, GoldOre::new);
        REDSTONE_ORE = block(LFBlockItemIds.REDSTONE_ORE, RedstoneOre::new);
        DIAMOND_ORE = block(LFBlockItemIds.DIAMOND_ORE, DiamondOre::new);
        STONE = block(LFBlockItemIds.STONE, Stone::new);

        // Building / Stone
        STONE_STAIRS = block(LFBlockItemIds.STONE_STAIRS, StoneStairs::new);
        STONE_SLAB = block(LFBlockItemIds.STONE_SLAB, StoneSlab::new);
        STONE_PRESSURE_PLATE = block(LFBlockItemIds.STONE_PRESSURE_PLATE, StonePressurePlate::new);
        STONE_BUTTON = block(LFBlockItemIds.STONE_BUTTON, StoneButton::new);
        COBBLESTONE = block(LFBlockItemIds.COBBLESTONE, Cobblestone::new);
        COBBLESTONE_STAIRS = block(LFBlockItemIds.COBBLESTONE_STAIRS, CobblestoneStairs::new);
        COBBLESTONE_SLAB = block(LFBlockItemIds.COBBLESTONE_SLAB, CobblestoneSlab::new);
        COBBLESTONE_WALL = block(LFBlockItemIds.COBBLESTONE_WALL, CobblestoneWall::new);
        MOSSY_COBBLESTONE = block(LFBlockItemIds.MOSSY_COBBLESTONE, MossyCobblestone::new);
        MOSSY_COBBLESTONE_STAIRS = block(LFBlockItemIds.MOSSY_COBBLESTONE_STAIRS, MossyCobblestoneStairs::new);
        MOSSY_COBBLESTONE_SLAB = block(LFBlockItemIds.MOSSY_COBBLESTONE_SLAB, MossyCobblestoneSlab::new);
        MOSSY_COBBLESTONE_WALL = block(LFBlockItemIds.MOSSY_COBBLESTONE_WALL, MossyCobblestoneWall::new);
        BRICKS = block(LFBlockItemIds.BRICKS, Bricks::new);
        BRICK_STAIRS = block(LFBlockItemIds.BRICK_STAIRS, BrickStairs::new);
        BRICK_SLAB = block(LFBlockItemIds.BRICK_SLAB, BrickSlab::new);
        BRICK_WALL = block(LFBlockItemIds.BRICK_WALL, BrickWall::new);

        // Building / Deco
        OBSIDIAN = block(LFBlockItemIds.OBSIDIAN, Obsidian::new);
        IRON_BLOCK = block(LFBlockItemIds.IRON_BLOCK, IronBlock::new);
        GOLD_BLOCK = block(LFBlockItemIds.GOLD_BLOCK, GoldBlock::new);
        DIAMOND_BLOCK = block(LFBlockItemIds.DIAMOND_BLOCK, DiamondBlock::new);
        BOOKSHELF = block(LFBlockItemIds.BOOKSHELF, Bookshelf::new);
        GLASS = block(LFBlockItemIds.GLASS, Glass::new);
        GLASS_PANE = block(LFBlockItemIds.GLASS_PANE, GlassPane::new);
        TNT = block(LFBlockItemIds.TNT, Tnt::new);
        IRON_DOOR = block(LFBlockItemIds.IRON_DOOR, IronDoor::new);

        // Dev
        FEATURE_VOID = block(LFBlockItemIds.FEATURE_VOID, FeatureVoid::new);
        RMDL_FURNACE_STONE = block(LFBlockItemIds.RMDL_FURNACE_STONE, RemodelFurnace::new);
        RMDL_FURNACE = block(LFBlockItemIds.RMDL_FURNACE, RemodelFurnace::new);
        RMDL_CRAFTING = block(LFBlockItemIds.RMDL_CRAFTING, RemodelCraftingTable::new);
        RMDL_CHEST = block(LFBlockItemIds.RMDL_CHEST, RemodelChest::new);
    }

    private static RegistrySupplier<Block> block(BlockItemId id, Supplier<Block> block) {
        return REGISTRY.register(id.block().location().getPath(), block);
    }

    public static void register() {
        REGISTRY.register();
    }

    public static Block[] getBlocksFromRegistry() {
        return Streams.stream(REGISTRY).filter(Objects::nonNull).map(Supplier::get).toArray(Block[]::new);
    }
}