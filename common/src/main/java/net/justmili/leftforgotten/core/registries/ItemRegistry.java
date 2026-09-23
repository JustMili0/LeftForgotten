package net.justmili.leftforgotten.core.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.content.item.OldBoat;
import net.justmili.leftforgotten.core.references.LFItemIds;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class ItemRegistry {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(LeftForgotten.MODID, Registries.ITEM);
    public static final RegistrySupplier<Item>
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
    public static final RegistrySupplier<Item> CLAY_BALL, BRICK, BOAT;

    static {
        /// Block Items
        // In-Overworld
        BRITTLE_BEDROCK = block(BlockRegistry.BRITTLE_BEDROCK);

        // Nature / Ground
        GRASS_BLOCK = block(BlockRegistry.GRASS_BLOCK);
        DIRT = block(BlockRegistry.DIRT);
        FARMLAND = block(BlockRegistry.FARMLAND);
        GRAVEL = block(BlockRegistry.GRAVEL);
        SAND = block(BlockRegistry.SAND);
        CLAY = block(BlockRegistry.CLAY);

        // Nature / Vegetation
        RED_FLOWER = block(BlockRegistry.RED_FLOWER);
        YELLOW_FLOWER = block(BlockRegistry.YELLOW_FLOWER);
        RED_MUSHROOM = block(BlockRegistry.RED_MUSHROOM);
        BROWN_MUSHROOM = block(BlockRegistry.BROWN_MUSHROOM);
        CACTUS = block(BlockRegistry.CACTUS);
        SAPLING = block(BlockRegistry.SAPLING);
        LEAVES = block(BlockRegistry.LEAVES);

        // Building / Wood
        WOOD = block(BlockRegistry.WOOD);
        WOOD_6_SIDED = block(BlockRegistry.WOOD_6_SIDED);
        WOODEN_PLANKS = block(BlockRegistry.WOODEN_PLANKS);
        WOODEN_STAIRS = block(BlockRegistry.WOODEN_STAIRS);
        WOODEN_SLAB = block(BlockRegistry.WOODEN_SLAB);
        FENCE = block(BlockRegistry.FENCE);
        FENCE_GATE = block(BlockRegistry.FENCE_GATE);
        DOOR = doubleBlock(BlockRegistry.DOOR);
        TRAPDOOR = block(BlockRegistry.TRAPDOOR);
        PRESSURE_PLATE = block(BlockRegistry.PRESSURE_PLATE);
        BUTTON = block(BlockRegistry.BUTTON);

        // Nature / Underground
        COAL_ORE = block(BlockRegistry.COAL_ORE);
        IRON_ORE = block(BlockRegistry.IRON_ORE);
        GOLD_ORE = block(BlockRegistry.GOLD_ORE);
        REDSTONE_ORE = block(BlockRegistry.REDSTONE_ORE);
        DIAMOND_ORE = block(BlockRegistry.DIAMOND_ORE);
        STONE = block(BlockRegistry.STONE);

        // Building / Stone
        STONE_STAIRS = block(BlockRegistry.STONE_STAIRS);
        STONE_SLAB = block(BlockRegistry.STONE_SLAB);
        STONE_PRESSURE_PLATE = block(BlockRegistry.STONE_PRESSURE_PLATE);
        STONE_BUTTON = block(BlockRegistry.STONE_BUTTON);
        COBBLESTONE = block(BlockRegistry.COBBLESTONE);
        COBBLESTONE_STAIRS = block(BlockRegistry.COBBLESTONE_STAIRS);
        COBBLESTONE_SLAB = block(BlockRegistry.COBBLESTONE_SLAB);
        COBBLESTONE_WALL = block(BlockRegistry.COBBLESTONE_WALL);
        MOSSY_COBBLESTONE = block(BlockRegistry.MOSSY_COBBLESTONE);
        MOSSY_COBBLESTONE_STAIRS = block(BlockRegistry.MOSSY_COBBLESTONE_STAIRS);
        MOSSY_COBBLESTONE_SLAB = block(BlockRegistry.MOSSY_COBBLESTONE_SLAB);
        MOSSY_COBBLESTONE_WALL = block(BlockRegistry.MOSSY_COBBLESTONE_WALL);
        BRICKS = block(BlockRegistry.BRICKS);
        BRICK_STAIRS = block(BlockRegistry.BRICK_STAIRS);
        BRICK_SLAB = block(BlockRegistry.BRICK_SLAB);
        BRICK_WALL = block(BlockRegistry.BRICK_WALL);

        // Building / Deco
        OBSIDIAN = block(BlockRegistry.OBSIDIAN);
        IRON_BLOCK = block(BlockRegistry.IRON_BLOCK);
        GOLD_BLOCK = block(BlockRegistry.GOLD_BLOCK);
        DIAMOND_BLOCK = block(BlockRegistry.DIAMOND_BLOCK);
        BOOKSHELF = block(BlockRegistry.BOOKSHELF);
        GLASS = block(BlockRegistry.GLASS);
        GLASS_PANE = block(BlockRegistry.GLASS_PANE);
        TNT = block(BlockRegistry.TNT);
        IRON_DOOR = doubleBlock(BlockRegistry.IRON_DOOR);

        // Dev
        var devProps = new Item.Properties().rarity(Rarity.EPIC);
        FEATURE_VOID = block(BlockRegistry.FEATURE_VOID, devProps);
        RMDL_CRAFTING = block(BlockRegistry.RMDL_CRAFTING, devProps);
        RMDL_FURNACE = block(BlockRegistry.RMDL_FURNACE, devProps);
        RMDL_FURNACE_STONE = block(BlockRegistry.RMDL_FURNACE_STONE, devProps);
        RMDL_CHEST = block(BlockRegistry.RMDL_CHEST, devProps);

        /// Items
        // Misc
        CLAY_BALL = item(LFItemIds.CLAY_BALL, Item::new);
        BRICK = item(LFItemIds.BRICK, Item::new);
        BOAT = item(LFItemIds.BOAT, OldBoat::new);
    }

    private static <T extends Item> RegistrySupplier<T> item(ResourceKey<Item> id, Function<Item.Properties, T> itemBuilder) {
        //var id = ResourceKey.create(Registries.ITEM, LeftForgotten.asId(name)); // not needed right now but keep for later
        return REGISTRY.register(id.location().getPath(), () -> itemBuilder.apply(new Item.Properties()));
    }

    private static RegistrySupplier<Item> block(RegistrySupplier<Block> block, Item.Properties props) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), props));
    }

    private static RegistrySupplier<Item> block(RegistrySupplier<Block> block) {
        return block(block, new Item.Properties());
    }

    private static RegistrySupplier<Item> doubleBlock(RegistrySupplier<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
    }

    public static void register() {
        REGISTRY.register();
    }
}