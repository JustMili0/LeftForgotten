package net.justmili.leftforgotten.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.content.item.Boat;
import net.justmili.leftforgotten.content.item.Brick;
import net.justmili.leftforgotten.content.item.ClayBall;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class LFItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(LeftForgotten.MODID, Registries.ITEM);

    public static final RegistrySupplier<Item> BRITTLE_BEDROCK,
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
        FEATURE_VOID, REMODEL_FURNACE, REMODEL_CRAFTING_TABLE, REMODEL_CHEST;

    public static final RegistrySupplier<Item> CLAY_BALL, BRICK, BOAT;

    static {
        /// Block Items
        // In-Overworld
        BRITTLE_BEDROCK = registerBlock(LFBlocks.BRITTLE_BEDROCK);

        // Nature / Ground
        GRASS_BLOCK = registerBlock(LFBlocks.GRASS_BLOCK);
        DIRT = registerBlock(LFBlocks.DIRT);
        FARMLAND = registerBlock(LFBlocks.FARMLAND);
        GRAVEL = registerBlock(LFBlocks.GRAVEL);
        SAND = registerBlock(LFBlocks.SAND);
        CLAY = registerBlock(LFBlocks.CLAY);

        // Nature / Vegetation
        RED_FLOWER = registerBlock(LFBlocks.RED_FLOWER);
        YELLOW_FLOWER = registerBlock(LFBlocks.YELLOW_FLOWER);
        RED_MUSHROOM = registerBlock(LFBlocks.RED_MUSHROOM);
        BROWN_MUSHROOM = registerBlock(LFBlocks.BROWN_MUSHROOM);
        CACTUS = registerBlock(LFBlocks.CACTUS);
        SAPLING = registerBlock(LFBlocks.SAPLING);
        LEAVES = registerBlock(LFBlocks.LEAVES);

        // Building / Wood
        WOOD = registerBlock(LFBlocks.WOOD);
        WOOD_6_SIDED = registerBlock(LFBlocks.WOOD_6_SIDED);
        WOODEN_PLANKS = registerBlock(LFBlocks.WOODEN_PLANKS);
        WOODEN_STAIRS = registerBlock(LFBlocks.WOODEN_STAIRS);
        WOODEN_SLAB = registerBlock(LFBlocks.WOODEN_SLAB);
        FENCE = registerBlock(LFBlocks.FENCE);
        FENCE_GATE = registerBlock(LFBlocks.FENCE_GATE);
        DOOR = registerDoubleBlock(LFBlocks.DOOR);
        TRAPDOOR = registerBlock(LFBlocks.TRAPDOOR);
        PRESSURE_PLATE = registerBlock(LFBlocks.PRESSURE_PLATE);
        BUTTON = registerBlock(LFBlocks.BUTTON);

        // Nature / Underground
        COAL_ORE = registerBlock(LFBlocks.COAL_ORE);
        IRON_ORE = registerBlock(LFBlocks.IRON_ORE);
        GOLD_ORE = registerBlock(LFBlocks.GOLD_ORE);
        REDSTONE_ORE = registerBlock(LFBlocks.REDSTONE_ORE);
        DIAMOND_ORE = registerBlock(LFBlocks.DIAMOND_ORE);
        STONE = registerBlock(LFBlocks.STONE);

        // Building / Stone
        STONE_STAIRS = registerBlock(LFBlocks.STONE_STAIRS);
        STONE_SLAB = registerBlock(LFBlocks.STONE_SLAB);
        STONE_PRESSURE_PLATE = registerBlock(LFBlocks.STONE_PRESSURE_PLATE);
        STONE_BUTTON = registerBlock(LFBlocks.STONE_BUTTON);
        COBBLESTONE = registerBlock(LFBlocks.COBBLESTONE);
        COBBLESTONE_STAIRS = registerBlock(LFBlocks.COBBLESTONE_STAIRS);
        COBBLESTONE_SLAB = registerBlock(LFBlocks.COBBLESTONE_SLAB);
        COBBLESTONE_WALL = registerBlock(LFBlocks.COBBLESTONE_WALL);
        MOSSY_COBBLESTONE = registerBlock(LFBlocks.MOSSY_COBBLESTONE);
        MOSSY_COBBLESTONE_STAIRS = registerBlock(LFBlocks.MOSSY_COBBLESTONE_STAIRS);
        MOSSY_COBBLESTONE_SLAB = registerBlock(LFBlocks.MOSSY_COBBLESTONE_SLAB);
        MOSSY_COBBLESTONE_WALL = registerBlock(LFBlocks.MOSSY_COBBLESTONE_WALL);
        BRICKS = registerBlock(LFBlocks.BRICKS);
        BRICK_STAIRS = registerBlock(LFBlocks.BRICK_STAIRS);
        BRICK_SLAB = registerBlock(LFBlocks.BRICK_SLAB);
        BRICK_WALL = registerBlock(LFBlocks.BRICK_WALL);

        // Building / Deco
        OBSIDIAN = registerBlock(LFBlocks.OBSIDIAN);
        IRON_BLOCK = registerBlock(LFBlocks.IRON_BLOCK);
        GOLD_BLOCK = registerBlock(LFBlocks.GOLD_BLOCK);
        DIAMOND_BLOCK = registerBlock(LFBlocks.DIAMOND_BLOCK);
        BOOKSHELF = registerBlock(LFBlocks.BOOKSHELF);
        GLASS = registerBlock(LFBlocks.GLASS);
        GLASS_PANE = registerBlock(LFBlocks.GLASS_PANE);
        TNT = registerBlock(LFBlocks.TNT);

        // Building / Iron
        IRON_DOOR = registerDoubleBlock(LFBlocks.IRON_DOOR);

        // Dev
        FEATURE_VOID = registerBlock(LFBlocks.FEATURE_VOID);
        REMODEL_CRAFTING_TABLE = registerBlock(LFBlocks.REMODEL_CRAFTING_TABLE);
        REMODEL_FURNACE = registerBlock(LFBlocks.REMODEL_FURNACE);
        REMODEL_CHEST = registerBlock(LFBlocks.REMODEL_CHEST);

        /// Items
        // Misc
        CLAY_BALL = registerItem("clay_ball", ClayBall::new);
        BRICK = registerItem("brick", Brick::new);
        BOAT = registerItem("boat", properties -> new Boat());
    }

    private static <T extends Item> RegistrySupplier<T> registerItem(String name, Function<Item.Properties, T> itemBuilder) {
        //var key = ResourceKey.create(Registries.ITEM, LeftForgotten.asResource(name)); // not needed right now but keep for later
        return REGISTRY.register(name, () -> itemBuilder.apply(new Item.Properties()));
    }
    private static RegistrySupplier<Item> registerBlock(RegistrySupplier<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
    private static RegistrySupplier<Item> registerDoubleBlock(RegistrySupplier<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
    }

    public static void register() {
        REGISTRY.register();
    }
}