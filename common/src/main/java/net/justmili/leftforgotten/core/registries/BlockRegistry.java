package net.justmili.leftforgotten.core.registries;

import com.google.common.collect.Streams;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.content.block.Cactus;
import net.justmili.leftforgotten.content.block.Farmland;
import net.justmili.leftforgotten.content.block.GrassBlock;
import net.justmili.leftforgotten.content.block.OldTillableBlock;
import net.justmili.leftforgotten.content.block.dev.FeatureVoid;
import net.justmili.leftforgotten.content.block.dev.RmdlChest;
import net.justmili.leftforgotten.content.block.dev.RmdlCrafting;
import net.justmili.leftforgotten.content.block.dev.RmdlFurnace;
import net.justmili.leftforgotten.content.world.block.grower.AlphaTreeGrower;
import net.justmili.leftforgotten.core.references.LFBlockItemIds;
import net.justmili.leftforgotten.libs.v1.references.BlockItemId;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(LeftForgotten.ID, Registries.BLOCK);
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
        BRITTLE_BEDROCK = block(LFBlockItemIds.BRITTLE_BEDROCK, Block::new, copy(Blocks.BEDROCK).strength(Block.INDESTRUCTIBLE, 6.5f));

        // Nature / Ground
        GRASS_BLOCK = block(LFBlockItemIds.GRASS_BLOCK, GrassBlock::new, copy(Blocks.GRASS_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN));
        DIRT = block(LFBlockItemIds.DIRT, OldTillableBlock::new, copy(Blocks.DIRT));
        FARMLAND = block(LFBlockItemIds.FARMLAND, Farmland::new, copy(Blocks.FARMLAND));
        GRAVEL = block(LFBlockItemIds.GRAVEL, p -> new ColoredFallingBlock(new ColorRGBA(-8356741), p), copy(Blocks.GRAVEL));
        SAND = block(LFBlockItemIds.SAND, p -> new ColoredFallingBlock(new ColorRGBA(14406560), p), copy(Blocks.SAND));
        CLAY = block(LFBlockItemIds.CLAY, Block::new, copy(Blocks.CLAY));

        // Nature / Vegetation
        RED_FLOWER = block(LFBlockItemIds.RED_FLOWER, p -> new FlowerBlock(MobEffects.NIGHT_VISION, 5, p), copy(Blocks.POPPY));
        YELLOW_FLOWER = block(LFBlockItemIds.YELLOW_FLOWER, p -> new FlowerBlock(MobEffects.SATURATION, 7, p), copy(Blocks.DANDELION));
        RED_MUSHROOM = block(LFBlockItemIds.RED_MUSHROOM, p -> new MushroomBlock(TreeFeatures.HUGE_RED_MUSHROOM, p), copy(Blocks.RED_MUSHROOM));
        BROWN_MUSHROOM = block(LFBlockItemIds.BROWN_MUSHROOM, p -> new MushroomBlock(TreeFeatures.HUGE_BROWN_MUSHROOM, p), copy(Blocks.BROWN_MUSHROOM));
        CACTUS = block(LFBlockItemIds.CACTUS, Cactus::new, copy(Blocks.CACTUS));
        SAPLING = block(LFBlockItemIds.SAPLING, p -> new SaplingBlock(AlphaTreeGrower.INSTANCE, p), copy(Blocks.OAK_SAPLING).mapColor(MapColor.COLOR_LIGHT_GREEN));
        LEAVES = block(LFBlockItemIds.LEAVES, LeavesBlock::new, copy(Blocks.OAK_LEAVES).mapColor(MapColor.COLOR_LIGHT_GREEN));

        // Building / Wood
        WOOD = block(LFBlockItemIds.WOOD, RotatedPillarBlock::new, copy(Blocks.OAK_LOG));
        WOOD_6_SIDED = block(LFBlockItemIds.WOOD_6_SIDED, RotatedPillarBlock::new, copy(Blocks.OAK_WOOD));
        WOODEN_PLANKS = block(LFBlockItemIds.WOODEN_PLANKS, Block::new, copy(Blocks.OAK_PLANKS));
        WOODEN_STAIRS = block(LFBlockItemIds.WOODEN_STAIRS, p -> new StairBlock(getState(WOODEN_PLANKS), p), copy(Blocks.OAK_STAIRS));
        WOODEN_SLAB = block(LFBlockItemIds.WOODEN_SLAB, SlabBlock::new, copy(Blocks.OAK_SLAB));
        FENCE = block(LFBlockItemIds.FENCE, FenceBlock::new, copy(Blocks.OAK_FENCE));
        FENCE_GATE = block(LFBlockItemIds.FENCE_GATE, p -> new FenceGateBlock(WoodType.OAK, p), copy(Blocks.OAK_FENCE_GATE));
        DOOR = block(LFBlockItemIds.DOOR, p -> new DoorBlock(BlockSetType.OAK, p),copy(Blocks.OAK_DOOR));
        TRAPDOOR = block(LFBlockItemIds.TRAPDOOR, p -> new TrapDoorBlock(BlockSetType.OAK, p), copy(Blocks.OAK_TRAPDOOR));
        PRESSURE_PLATE = block(LFBlockItemIds.PRESSURE_PLATE, p -> new PressurePlateBlock(BlockSetType.OAK, p), copy(Blocks.OAK_PRESSURE_PLATE));
        BUTTON = block(LFBlockItemIds.BUTTON, p -> new ButtonBlock(BlockSetType.OAK, 30, p), copy(Blocks.OAK_BUTTON));

        // Nature / Underground
        COAL_ORE = block(LFBlockItemIds.COAL_ORE, p -> new DropExperienceBlock(UniformInt.of(0, 2), p), copy(Blocks.COAL_ORE));
        IRON_ORE = block(LFBlockItemIds.IRON_ORE, Block::new, copy(Blocks.IRON_ORE));
        GOLD_ORE = block(LFBlockItemIds.GOLD_ORE, Block::new, copy(Blocks.GOLD_ORE));
        REDSTONE_ORE = block(LFBlockItemIds.REDSTONE_ORE, RedStoneOreBlock::new, copy(Blocks.REDSTONE_ORE));
        DIAMOND_ORE = block(LFBlockItemIds.DIAMOND_ORE, p -> new DropExperienceBlock(UniformInt.of(3, 7), p), copy(Blocks.DIAMOND_ORE));
        STONE = block(LFBlockItemIds.STONE, Block::new, copy(Blocks.STONE));

        // Building / Stone
        STONE_STAIRS = block(LFBlockItemIds.STONE_STAIRS, p -> new StairBlock(getState(STONE), p), copy(Blocks.STONE_STAIRS));
        STONE_SLAB = block(LFBlockItemIds.STONE_SLAB, SlabBlock::new, copy(Blocks.STONE_SLAB));
        STONE_PRESSURE_PLATE = block(LFBlockItemIds.STONE_PRESSURE_PLATE, p -> new PressurePlateBlock(BlockSetType.STONE, p), copy(Blocks.STONE_PRESSURE_PLATE));
        STONE_BUTTON = block(LFBlockItemIds.STONE_BUTTON, p -> new ButtonBlock(BlockSetType.STONE, 20, p), copy(Blocks.STONE_BUTTON));
        COBBLESTONE = block(LFBlockItemIds.COBBLESTONE, Block::new, copy(Blocks.COBBLESTONE));
        COBBLESTONE_STAIRS = block(LFBlockItemIds.COBBLESTONE_STAIRS, p -> new StairBlock(getState(COBBLESTONE), p), copy(Blocks.COBBLESTONE_STAIRS));
        COBBLESTONE_SLAB = block(LFBlockItemIds.COBBLESTONE_SLAB, SlabBlock::new, copy(Blocks.COBBLESTONE_SLAB));
        COBBLESTONE_WALL = block(LFBlockItemIds.COBBLESTONE_WALL, WallBlock::new, copy(Blocks.COBBLESTONE_WALL));
        MOSSY_COBBLESTONE = block(LFBlockItemIds.MOSSY_COBBLESTONE, Block::new, copy(Blocks.MOSSY_COBBLESTONE));
        MOSSY_COBBLESTONE_STAIRS = block(LFBlockItemIds.MOSSY_COBBLESTONE_STAIRS, p -> new StairBlock(getState(MOSSY_COBBLESTONE), p), copy(Blocks.MOSSY_COBBLESTONE_STAIRS));
        MOSSY_COBBLESTONE_SLAB = block(LFBlockItemIds.MOSSY_COBBLESTONE_SLAB, SlabBlock::new, copy(Blocks.MOSSY_COBBLESTONE_SLAB));
        MOSSY_COBBLESTONE_WALL = block(LFBlockItemIds.MOSSY_COBBLESTONE_WALL, WallBlock::new, copy(Blocks.MOSSY_COBBLESTONE_WALL));
        BRICKS = block(LFBlockItemIds.BRICKS, Block::new, copy(Blocks.BRICKS));
        BRICK_STAIRS = block(LFBlockItemIds.BRICK_STAIRS, p -> new StairBlock(getState(BRICKS), p), copy(Blocks.BRICK_STAIRS));
        BRICK_SLAB = block(LFBlockItemIds.BRICK_SLAB, SlabBlock::new, copy(Blocks.BRICK_SLAB));
        BRICK_WALL = block(LFBlockItemIds.BRICK_WALL, WallBlock::new, copy(Blocks.BRICK_WALL));

        // Building / Deco
        OBSIDIAN = block(LFBlockItemIds.OBSIDIAN, Block::new, copy(Blocks.OBSIDIAN));
        IRON_BLOCK = block(LFBlockItemIds.IRON_BLOCK, Block::new, copy(Blocks.IRON_BLOCK));
        GOLD_BLOCK = block(LFBlockItemIds.GOLD_BLOCK, Block::new, copy(Blocks.GOLD_BLOCK));
        DIAMOND_BLOCK = block(LFBlockItemIds.DIAMOND_BLOCK, Block::new, copy(Blocks.DIAMOND_BLOCK));
        BOOKSHELF = block(LFBlockItemIds.BOOKSHELF, Block::new, copy(Blocks.BOOKSHELF));
        GLASS = block(LFBlockItemIds.GLASS, TransparentBlock::new, copy(Blocks.GLASS));
        GLASS_PANE = block(LFBlockItemIds.GLASS_PANE, IronBarsBlock::new, copy(Blocks.GLASS_PANE));
        TNT = block(LFBlockItemIds.TNT, TntBlock::new, copy(Blocks.TNT));
        IRON_DOOR = block(LFBlockItemIds.IRON_DOOR, p -> new DoorBlock(BlockSetType.IRON, p), copy(Blocks.IRON_DOOR));

        // Dev
        FEATURE_VOID = block(LFBlockItemIds.FEATURE_VOID, FeatureVoid::new, copy(Blocks.BARRIER));
        RMDL_FURNACE_STONE = block(LFBlockItemIds.RMDL_FURNACE_STONE, RmdlFurnace::new, copy(Blocks.BARRIER).mapColor(MapColor.NONE));
        RMDL_FURNACE = block(LFBlockItemIds.RMDL_FURNACE, RmdlFurnace::new, copy(Blocks.BARRIER).mapColor(MapColor.NONE));
        RMDL_CRAFTING = block(LFBlockItemIds.RMDL_CRAFTING, RmdlCrafting::new, copy(Blocks.BARRIER).mapColor(MapColor.NONE));
        RMDL_CHEST = block(LFBlockItemIds.RMDL_CHEST, RmdlChest::new, copy(Blocks.BARRIER).mapColor(MapColor.NONE));
    }

    private static RegistrySupplier<Block> block(BlockItemId id, Function<BlockBehaviour.Properties, Block> block, BlockBehaviour.Properties properties) {
        return REGISTRY.register(id.block().location().getPath(), () -> block.apply(properties));
    }

    private static BlockState getState(RegistrySupplier<Block> block) {
        return block.get().defaultBlockState();
    }

    private static BlockBehaviour.Properties copy(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block);
    }

    public static void init() {
        REGISTRY.register();
    }

    public static Block[] getBlocksFromRegistry() {
        return Streams.stream(REGISTRY).filter(Objects::nonNull).map(Supplier::get).toArray(Block[]::new);
    }
}