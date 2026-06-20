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

    /// Block Items
    // In-Overworld
    public static final RegistrySupplier<Item> BRITTLE_BEDROCK = block(LFBlocks.BRITTLE_BEDROCK);

    // Nature / Ground
    public static final RegistrySupplier<Item> GRASS_BLOCK = block(LFBlocks.GRASS_BLOCK);
    public static final RegistrySupplier<Item> DIRT = block(LFBlocks.DIRT);
    public static final RegistrySupplier<Item> FARMLAND = block(LFBlocks.FARMLAND);
    public static final RegistrySupplier<Item> GRAVEL = block(LFBlocks.GRAVEL);
    public static final RegistrySupplier<Item> SAND = block(LFBlocks.SAND);
    public static final RegistrySupplier<Item> CLAY = block(LFBlocks.CLAY);

    // Nature / Vegetation
    public static final RegistrySupplier<Item> RED_FLOWER = block(LFBlocks.RED_FLOWER);
    public static final RegistrySupplier<Item> YELLOW_FLOWER = block(LFBlocks.YELLOW_FLOWER);
    public static final RegistrySupplier<Item> RED_MUSHROOM = block(LFBlocks.RED_MUSHROOM);
    public static final RegistrySupplier<Item> BROWN_MUSHROOM = block(LFBlocks.BROWN_MUSHROOM);
    public static final RegistrySupplier<Item> CACTUS = block(LFBlocks.CACTUS);
    public static final RegistrySupplier<Item> SAPLING = block(LFBlocks.SAPLING);
    public static final RegistrySupplier<Item> LEAVES = block(LFBlocks.LEAVES);

    // Building / Wood
    public static final RegistrySupplier<Item> WOOD = block(LFBlocks.WOOD);
    public static final RegistrySupplier<Item> WOOD_6_SIDED = block(LFBlocks.WOOD_6_SIDED);
    public static final RegistrySupplier<Item> WOODEN_PLANKS = block(LFBlocks.WOODEN_PLANKS);
    public static final RegistrySupplier<Item> WOODEN_STAIRS = block(LFBlocks.WOODEN_STAIRS);
    public static final RegistrySupplier<Item> WOODEN_SLAB = block(LFBlocks.WOODEN_SLAB);
    public static final RegistrySupplier<Item> FENCE = block(LFBlocks.FENCE);
    public static final RegistrySupplier<Item> FENCE_GATE = block(LFBlocks.FENCE_GATE);
    public static final RegistrySupplier<Item> DOOR = doubleBlock(LFBlocks.DOOR);
    public static final RegistrySupplier<Item> TRAPDOOR = block(LFBlocks.TRAPDOOR);
    public static final RegistrySupplier<Item> PRESSURE_PLATE = block(LFBlocks.PRESSURE_PLATE);
    public static final RegistrySupplier<Item> BUTTON = block(LFBlocks.BUTTON);

    // Nature / Underground
    public static final RegistrySupplier<Item> COAL_ORE = block(LFBlocks.COAL_ORE);
    public static final RegistrySupplier<Item> IRON_ORE = block(LFBlocks.IRON_ORE);
    public static final RegistrySupplier<Item> GOLD_ORE = block(LFBlocks.GOLD_ORE);
    public static final RegistrySupplier<Item> REDSTONE_ORE = block(LFBlocks.REDSTONE_ORE);
    public static final RegistrySupplier<Item> DIAMOND_ORE = block(LFBlocks.DIAMOND_ORE);
    public static final RegistrySupplier<Item> STONE = block(LFBlocks.STONE);

    // Building / Stone
    public static final RegistrySupplier<Item> STONE_STAIRS = block(LFBlocks.STONE_STAIRS);
    public static final RegistrySupplier<Item> STONE_SLAB = block(LFBlocks.STONE_SLAB);
    public static final RegistrySupplier<Item> STONE_PRESSURE_PLATE = block(LFBlocks.STONE_PRESSURE_PLATE);
    public static final RegistrySupplier<Item> STONE_BUTTON = block(LFBlocks.STONE_BUTTON);
    public static final RegistrySupplier<Item> COBBLESTONE = block(LFBlocks.COBBLESTONE);
    public static final RegistrySupplier<Item> COBBLESTONE_STAIRS = block(LFBlocks.COBBLESTONE_STAIRS);
    public static final RegistrySupplier<Item> COBBLESTONE_SLAB = block(LFBlocks.COBBLESTONE_SLAB);
    public static final RegistrySupplier<Item> COBBLESTONE_WALL = block(LFBlocks.COBBLESTONE_WALL);
    public static final RegistrySupplier<Item> MOSSY_COBBLESTONE = block(LFBlocks.MOSSY_COBBLESTONE);
    public static final RegistrySupplier<Item> MOSSY_COBBLESTONE_STAIRS = block(LFBlocks.MOSSY_COBBLESTONE_STAIRS);
    public static final RegistrySupplier<Item> MOSSY_COBBLESTONE_SLAB = block(LFBlocks.MOSSY_COBBLESTONE_SLAB);
    public static final RegistrySupplier<Item> MOSSY_COBBLESTONE_WALL = block(LFBlocks.MOSSY_COBBLESTONE_WALL);
    public static final RegistrySupplier<Item> BRICKS = block(LFBlocks.BRICKS);
    public static final RegistrySupplier<Item> BRICK_STAIRS = block(LFBlocks.BRICK_STAIRS);
    public static final RegistrySupplier<Item> BRICK_SLAB = block(LFBlocks.BRICK_SLAB);
    public static final RegistrySupplier<Item> BRICK_WALL = block(LFBlocks.BRICK_WALL);

    // Building / Deco
    public static final RegistrySupplier<Item> OBSIDIAN = block(LFBlocks.OBSIDIAN);
    public static final RegistrySupplier<Item> IRON_BLOCK = block(LFBlocks.IRON_BLOCK);
    public static final RegistrySupplier<Item> GOLD_BLOCK = block(LFBlocks.GOLD_BLOCK);
    public static final RegistrySupplier<Item> DIAMOND_BLOCK = block(LFBlocks.DIAMOND_BLOCK);
    public static final RegistrySupplier<Item> BOOKSHELF = block(LFBlocks.BOOKSHELF);
    public static final RegistrySupplier<Item> GLASS = block(LFBlocks.GLASS);
    public static final RegistrySupplier<Item> GLASS_PANE = block(LFBlocks.GLASS_PANE);
    public static final RegistrySupplier<Item> TNT = block(LFBlocks.TNT);

    // Building / Iron
    public static final RegistrySupplier<Item> IRON_DOOR = doubleBlock(LFBlocks.IRON_DOOR);

    // Dev
    public static final RegistrySupplier<Item> FEATURE_VOID = block(LFBlocks.FEATURE_VOID);
    public static final RegistrySupplier<Item> REMODEL_CRAFTING_TABLE = block(LFBlocks.REMODEL_CRAFTING_TABLE);
    public static final RegistrySupplier<Item> REMODEL_FURNACE = block(LFBlocks.REMODEL_FURNACE);
    public static final RegistrySupplier<Item> REMODEL_CHEST = block(LFBlocks.REMODEL_CHEST);

    /// Items
    // Misc
    public static final RegistrySupplier<Item> CLAY_BALL = register("clay_ball", ClayBall::new);
    public static final RegistrySupplier<Item> BRICK = register("brick", Brick::new);
    public static final RegistrySupplier<Item> BOAT = register("boat", properties -> new Boat());

    private static <T extends Item> RegistrySupplier<T> register(String name, Function<Item.Properties, T> itemBuilder) {
        //var key = ResourceKey.create(Registries.ITEM, LeftForgotten.asResource(name)); // not needed right now
        return REGISTRY.register(name, () -> itemBuilder.apply(new Item.Properties()));
    }
    private static RegistrySupplier<Item> block(RegistrySupplier<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
    private static RegistrySupplier<Item> doubleBlock(RegistrySupplier<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
    }
    public static void register() {
        REGISTRY.register();
    }
}