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

public class ItemRegistry {
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
        BRITTLE_BEDROCK = registerBlock(BlockRegistry.BRITTLE_BEDROCK);

        // Nature / Ground
        GRASS_BLOCK = registerBlock(BlockRegistry.GRASS_BLOCK);
        DIRT = registerBlock(BlockRegistry.DIRT);
        FARMLAND = registerBlock(BlockRegistry.FARMLAND);
        GRAVEL = registerBlock(BlockRegistry.GRAVEL);
        SAND = registerBlock(BlockRegistry.SAND);
        CLAY = registerBlock(BlockRegistry.CLAY);

        // Nature / Vegetation
        RED_FLOWER = registerBlock(BlockRegistry.RED_FLOWER);
        YELLOW_FLOWER = registerBlock(BlockRegistry.YELLOW_FLOWER);
        RED_MUSHROOM = registerBlock(BlockRegistry.RED_MUSHROOM);
        BROWN_MUSHROOM = registerBlock(BlockRegistry.BROWN_MUSHROOM);
        CACTUS = registerBlock(BlockRegistry.CACTUS);
        SAPLING = registerBlock(BlockRegistry.SAPLING);
        LEAVES = registerBlock(BlockRegistry.LEAVES);

        // Building / Wood
        WOOD = registerBlock(BlockRegistry.WOOD);
        WOOD_6_SIDED = registerBlock(BlockRegistry.WOOD_6_SIDED);
        WOODEN_PLANKS = registerBlock(BlockRegistry.WOODEN_PLANKS);
        WOODEN_STAIRS = registerBlock(BlockRegistry.WOODEN_STAIRS);
        WOODEN_SLAB = registerBlock(BlockRegistry.WOODEN_SLAB);
        FENCE = registerBlock(BlockRegistry.FENCE);
        FENCE_GATE = registerBlock(BlockRegistry.FENCE_GATE);
        DOOR = registerDoubleBlock(BlockRegistry.DOOR);
        TRAPDOOR = registerBlock(BlockRegistry.TRAPDOOR);
        PRESSURE_PLATE = registerBlock(BlockRegistry.PRESSURE_PLATE);
        BUTTON = registerBlock(BlockRegistry.BUTTON);

        // Nature / Underground
        COAL_ORE = registerBlock(BlockRegistry.COAL_ORE);
        IRON_ORE = registerBlock(BlockRegistry.IRON_ORE);
        GOLD_ORE = registerBlock(BlockRegistry.GOLD_ORE);
        REDSTONE_ORE = registerBlock(BlockRegistry.REDSTONE_ORE);
        DIAMOND_ORE = registerBlock(BlockRegistry.DIAMOND_ORE);
        STONE = registerBlock(BlockRegistry.STONE);

        // Building / Stone
        STONE_STAIRS = registerBlock(BlockRegistry.STONE_STAIRS);
        STONE_SLAB = registerBlock(BlockRegistry.STONE_SLAB);
        STONE_PRESSURE_PLATE = registerBlock(BlockRegistry.STONE_PRESSURE_PLATE);
        STONE_BUTTON = registerBlock(BlockRegistry.STONE_BUTTON);
        COBBLESTONE = registerBlock(BlockRegistry.COBBLESTONE);
        COBBLESTONE_STAIRS = registerBlock(BlockRegistry.COBBLESTONE_STAIRS);
        COBBLESTONE_SLAB = registerBlock(BlockRegistry.COBBLESTONE_SLAB);
        COBBLESTONE_WALL = registerBlock(BlockRegistry.COBBLESTONE_WALL);
        MOSSY_COBBLESTONE = registerBlock(BlockRegistry.MOSSY_COBBLESTONE);
        MOSSY_COBBLESTONE_STAIRS = registerBlock(BlockRegistry.MOSSY_COBBLESTONE_STAIRS);
        MOSSY_COBBLESTONE_SLAB = registerBlock(BlockRegistry.MOSSY_COBBLESTONE_SLAB);
        MOSSY_COBBLESTONE_WALL = registerBlock(BlockRegistry.MOSSY_COBBLESTONE_WALL);
        BRICKS = registerBlock(BlockRegistry.BRICKS);
        BRICK_STAIRS = registerBlock(BlockRegistry.BRICK_STAIRS);
        BRICK_SLAB = registerBlock(BlockRegistry.BRICK_SLAB);
        BRICK_WALL = registerBlock(BlockRegistry.BRICK_WALL);

        // Building / Deco
        OBSIDIAN = registerBlock(BlockRegistry.OBSIDIAN);
        IRON_BLOCK = registerBlock(BlockRegistry.IRON_BLOCK);
        GOLD_BLOCK = registerBlock(BlockRegistry.GOLD_BLOCK);
        DIAMOND_BLOCK = registerBlock(BlockRegistry.DIAMOND_BLOCK);
        BOOKSHELF = registerBlock(BlockRegistry.BOOKSHELF);
        GLASS = registerBlock(BlockRegistry.GLASS);
        GLASS_PANE = registerBlock(BlockRegistry.GLASS_PANE);
        TNT = registerBlock(BlockRegistry.TNT);

        // Building / Iron
        IRON_DOOR = registerDoubleBlock(BlockRegistry.IRON_DOOR);

        // Dev
        FEATURE_VOID = registerBlock(BlockRegistry.FEATURE_VOID);
        REMODEL_CRAFTING_TABLE = registerBlock(BlockRegistry.REMODEL_CRAFTING_TABLE);
        REMODEL_FURNACE = registerBlock(BlockRegistry.REMODEL_FURNACE_STONE);
        REMODEL_CHEST = registerBlock(BlockRegistry.REMODEL_CHEST);

        /// Items
        // Misc
        CLAY_BALL = registerItem("clay_ball", ClayBall::new);
        BRICK = registerItem("brick", Brick::new);
        BOAT = registerItem("boat", properties -> new Boat());
    }

    private static <T extends Item> RegistrySupplier<T> registerItem(String name, Function<Item.Properties, T> itemBuilder) {
        //var key = ResourceKey.create(Registries.ITEM, LeftForgotten.asId(name)); // not needed right now but keep for later
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