package net.justmili.leftforgotten.datagen.providers;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.registries.ItemRegistry;
import net.justmili.leftforgotten.libs.v1.utils.common.datagen.DatagenDataUtil;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class LFRecipeProvider extends RecipeProvider {
    public LFRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> writer) {
        var gen = new DatagenDataUtil(LeftForgotten.MODID, writer);

        // Wood & Planks
        gen.planksFromLogs(ItemRegistry.WOOD.get(), ItemRegistry.WOODEN_PLANKS.get());
        gen.planksFromWood(ItemRegistry.WOOD_6_SIDED.get(), ItemRegistry.WOODEN_PLANKS.get());
        gen.stairs(ItemRegistry.WOODEN_PLANKS.get(), ItemRegistry.WOODEN_STAIRS.get());
        gen.slab(ItemRegistry.WOODEN_PLANKS.get(), ItemRegistry.WOODEN_SLAB.get());
        gen.fence(ItemRegistry.WOODEN_PLANKS.get(), ItemRegistry.FENCE.get());
        gen.fenceGate(ItemRegistry.WOODEN_PLANKS.get(), ItemRegistry.FENCE_GATE.get());
        gen.door(ItemRegistry.WOODEN_PLANKS.get(), ItemRegistry.DOOR.get());
        gen.trapdoor(ItemRegistry.WOODEN_PLANKS.get(), ItemRegistry.TRAPDOOR.get());
        gen.pressurePlate(ItemRegistry.WOODEN_PLANKS.get(), ItemRegistry.PRESSURE_PLATE.get());
        gen.button(ItemRegistry.WOODEN_PLANKS.get(), ItemRegistry.BUTTON.get());

        // Stone
        gen.smelt(ItemRegistry.COBBLESTONE.get(), ItemRegistry.STONE.get(), 0.1f);
        gen.stairs(ItemRegistry.STONE.get(), ItemRegistry.STONE_STAIRS.get());
        gen.slab(ItemRegistry.STONE.get(), ItemRegistry.STONE_SLAB.get());
        gen.pressurePlate(ItemRegistry.STONE.get(), ItemRegistry.STONE_PRESSURE_PLATE.get());
        gen.button(ItemRegistry.STONE.get(), ItemRegistry.STONE_BUTTON.get());

        // Ores
        gen.smelt(ItemRegistry.COAL_ORE.get(), Items.COAL, 0.1f);
        gen.smelt(ItemRegistry.IRON_ORE.get(), Items.IRON_INGOT, 0.7f);
        gen.smelt(ItemRegistry.GOLD_ORE.get(), Items.GOLD_INGOT, 1.0f);
        gen.smelt(ItemRegistry.REDSTONE_ORE.get(), Items.REDSTONE, 0.7f);
        gen.smelt(ItemRegistry.DIAMOND_ORE.get(), Items.DIAMOND, 1.0f);
        gen.blast(ItemRegistry.COAL_ORE.get(), Items.COAL, 0.1f);
        gen.blast(ItemRegistry.IRON_ORE.get(), Items.IRON_INGOT, 0.7f);
        gen.blast(ItemRegistry.GOLD_ORE.get(), Items.GOLD_INGOT, 1.0f);
        gen.blast(ItemRegistry.REDSTONE_ORE.get(), Items.REDSTONE, 0.7f);
        gen.blast(ItemRegistry.DIAMOND_ORE.get(), Items.DIAMOND, 1.0f);

        // Cobblestone
        gen.stairs(ItemRegistry.COBBLESTONE.get(), ItemRegistry.COBBLESTONE_STAIRS.get());
        gen.slab(ItemRegistry.COBBLESTONE.get(), ItemRegistry.COBBLESTONE_SLAB.get());
        gen.wall(ItemRegistry.COBBLESTONE.get(), ItemRegistry.COBBLESTONE_WALL.get());
        gen.cut(ItemRegistry.COBBLESTONE.get(), ItemRegistry.COBBLESTONE_STAIRS.get(), 1);
        gen.cut(ItemRegistry.COBBLESTONE.get(), ItemRegistry.COBBLESTONE_SLAB.get(), 2);
        gen.cut(ItemRegistry.COBBLESTONE.get(), ItemRegistry.COBBLESTONE_WALL.get());

        // Mossy Cobblestone
        gen.shapeless(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.MOSSY_COBBLESTONE.get(), ItemRegistry.COBBLESTONE.get(), Items.VINE);
        gen.shapeless(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.MOSSY_COBBLESTONE.get(), ItemRegistry.COBBLESTONE.get(), Items.MOSS_BLOCK);
        gen.stairs(ItemRegistry.MOSSY_COBBLESTONE.get(), ItemRegistry.MOSSY_COBBLESTONE_STAIRS.get());
        gen.slab(ItemRegistry.MOSSY_COBBLESTONE.get(), ItemRegistry.MOSSY_COBBLESTONE_SLAB.get());
        gen.wall(ItemRegistry.MOSSY_COBBLESTONE.get(), ItemRegistry.MOSSY_COBBLESTONE_WALL.get());
        gen.cut(ItemRegistry.MOSSY_COBBLESTONE.get(), ItemRegistry.MOSSY_COBBLESTONE_STAIRS.get(), 1);
        gen.cut(ItemRegistry.MOSSY_COBBLESTONE.get(), ItemRegistry.MOSSY_COBBLESTONE_SLAB.get(), 2);
        gen.cut(ItemRegistry.MOSSY_COBBLESTONE.get(), ItemRegistry.MOSSY_COBBLESTONE_WALL.get());

        // Bricks
        gen.shaped2x2(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.BRICK.get(), ItemRegistry.BRICKS.get(), 1);
        gen.stairs(ItemRegistry.BRICKS.get(), ItemRegistry.BRICK_STAIRS.get());
        gen.slab(ItemRegistry.BRICKS.get(), ItemRegistry.BRICK_SLAB.get());
        gen.wall(ItemRegistry.BRICKS.get(), ItemRegistry.BRICK_WALL.get());
        gen.cut(ItemRegistry.BRICKS.get(), ItemRegistry.BRICK_STAIRS.get(), 1);
        gen.cut(ItemRegistry.BRICKS.get(), ItemRegistry.BRICK_SLAB.get(), 2);
        gen.cut(ItemRegistry.BRICKS.get(), ItemRegistry.BRICK_WALL.get());

        // Clay & Brick item
        gen.shaped2x2(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.CLAY_BALL.get(), ItemRegistry.CLAY.get(), 1);
        gen.smelt(ItemRegistry.CLAY_BALL.get(), ItemRegistry.BRICK.get(), 0.3f);

        // Glass
        gen.smelt(ItemRegistry.SAND.get(), ItemRegistry.GLASS.get(), 0.1f);
        gen.bars(ItemRegistry.GLASS.get(), ItemRegistry.GLASS_PANE.get());

        // TNT
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ItemRegistry.TNT.get())
            .define('#', Items.GUNPOWDER)
            .define('X', ItemRegistry.SAND.get())
            .pattern("#X#")
            .pattern("X#X")
            .pattern("#X#")
            .unlockedBy(getHasName(ItemRegistry.SAND.get()), has(ItemRegistry.SAND.get()))
            .save(writer);

        // Wood 6-sided
        gen.shaped2x2(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.WOOD.get(), ItemRegistry.WOOD_6_SIDED.get(), 3);

        // Furnace and gen Table
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.FURNACE, 1)
            .define('#', ItemRegistry.COBBLESTONE.get())
            .pattern("###")
            .pattern("# #")
            .pattern("###")
            .unlockedBy(getHasName(ItemRegistry.COBBLESTONE.get()), has(ItemRegistry.COBBLESTONE.get()))
            .save(writer, LeftForgotten.asResource("furnace"));

        // Resource Blocks
        gen.shaped3x3(RecipeCategory.BUILDING_BLOCKS, Items.IRON_INGOT, ItemRegistry.IRON_BLOCK.get(), 1);
        gen.shaped3x3(RecipeCategory.BUILDING_BLOCKS, Items.GOLD_INGOT, ItemRegistry.GOLD_BLOCK.get(), 1);
        gen.shaped3x3(RecipeCategory.BUILDING_BLOCKS, Items.DIAMOND, ItemRegistry.DIAMOND_BLOCK.get(), 1);
        gen.shapeless(RecipeCategory.MISC, Items.IRON_INGOT, 9, ItemRegistry.IRON_BLOCK.get());
        gen.shapeless(RecipeCategory.MISC, Items.GOLD_INGOT, 9, ItemRegistry.GOLD_BLOCK.get());
        gen.shapeless(RecipeCategory.MISC, Items.DIAMOND, 9, ItemRegistry.DIAMOND_BLOCK.get());

        // Building / Iron
        gen.door(ItemRegistry.IRON_ORE.get(), ItemRegistry.IRON_DOOR.get());

        // Boat
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, ItemRegistry.BOAT.get())
            .define('#', ItemRegistry.WOODEN_PLANKS.get())
            .pattern("# #")
            .pattern("###")
            .unlockedBy(getHasName(ItemRegistry.WOODEN_PLANKS.get()), has(ItemRegistry.WOODEN_PLANKS.get()))
            .save(writer, LeftForgotten.asResource("boat"));
    }
}