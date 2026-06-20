package net.justmili.leftforgotten.core.datagen;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.core.util.DatagenDataUtil;
import net.justmili.leftforgotten.registries.LFItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class LFRecipeProvider extends RecipeProvider {
    public LFRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void buildRecipes(RecipeOutput writer) {
        var gen = new DatagenDataUtil(LeftForgotten.MODID, writer);

        // Wood & Planks
        gen.planks(LFItems.WOOD.get(), LFItems.WOODEN_PLANKS.get());
        gen.stairs(LFItems.WOODEN_PLANKS.get(), LFItems.WOODEN_STAIRS.get());
        gen.slab(LFItems.WOODEN_PLANKS.get(), LFItems.WOODEN_SLAB.get());
        gen.fence(LFItems.WOODEN_PLANKS.get(), LFItems.FENCE.get());
        gen.fenceGate(LFItems.WOODEN_PLANKS.get(), LFItems.FENCE_GATE.get());
        gen.door(LFItems.WOODEN_PLANKS.get(), LFItems.DOOR.get());
        gen.trapdoor(LFItems.WOODEN_PLANKS.get(), LFItems.TRAPDOOR.get());
        gen.pressurePlate(LFItems.WOODEN_PLANKS.get(), LFItems.PRESSURE_PLATE.get());
        gen.button(LFItems.WOODEN_PLANKS.get(), LFItems.BUTTON.get());

        // Stone
        gen.smelt(LFItems.COBBLESTONE.get(), LFItems.STONE.get(), 0.1f);
        gen.stairs(LFItems.STONE.get(), LFItems.STONE_STAIRS.get());
        gen.slab(LFItems.STONE.get(), LFItems.STONE_SLAB.get());
        gen.pressurePlate(LFItems.STONE.get(), LFItems.STONE_PRESSURE_PLATE.get());
        gen.button(LFItems.STONE.get(), LFItems.STONE_BUTTON.get());

        // Ores
        gen.smelt(LFItems.COAL_ORE.get(), Items.COAL, 0.1f);
        gen.smelt(LFItems.IRON_ORE.get(), Items.IRON_INGOT, 0.7f);
        gen.smelt(LFItems.GOLD_ORE.get(), Items.GOLD_INGOT, 1.0f);
        gen.smelt(LFItems.REDSTONE_ORE.get(), Items.REDSTONE, 0.7f);
        gen.smelt(LFItems.DIAMOND_ORE.get(), Items.DIAMOND, 1.0f);
        gen.blast(LFItems.COAL_ORE.get(), Items.COAL, 0.1f);
        gen.blast(LFItems.IRON_ORE.get(), Items.IRON_INGOT, 0.7f);
        gen.blast(LFItems.GOLD_ORE.get(), Items.GOLD_INGOT, 1.0f);
        gen.blast(LFItems.REDSTONE_ORE.get(), Items.REDSTONE, 0.7f);
        gen.blast(LFItems.DIAMOND_ORE.get(), Items.DIAMOND, 1.0f);

        // Cobblestone
        gen.stairs(LFItems.COBBLESTONE.get(), LFItems.COBBLESTONE_STAIRS.get());
        gen.slab(LFItems.COBBLESTONE.get(), LFItems.COBBLESTONE_SLAB.get());
        gen.wall(LFItems.COBBLESTONE.get(), LFItems.COBBLESTONE_WALL.get());
        gen.cut(LFItems.COBBLESTONE.get(), LFItems.COBBLESTONE_STAIRS.get(), 1);
        gen.cut(LFItems.COBBLESTONE.get(), LFItems.COBBLESTONE_SLAB.get(), 2);
        gen.cut(LFItems.COBBLESTONE.get(), LFItems.COBBLESTONE_WALL.get());

        // Mossy Cobblestone
        gen.shapeless(RecipeCategory.BUILDING_BLOCKS, LFItems.MOSSY_COBBLESTONE.get(), LFItems.COBBLESTONE.get(), Items.VINE);
        gen.shapeless(RecipeCategory.BUILDING_BLOCKS, LFItems.MOSSY_COBBLESTONE.get(), LFItems.COBBLESTONE.get(), Items.MOSS_BLOCK);
        gen.stairs(LFItems.MOSSY_COBBLESTONE.get(), LFItems.MOSSY_COBBLESTONE_STAIRS.get());
        gen.slab(LFItems.MOSSY_COBBLESTONE.get(), LFItems.MOSSY_COBBLESTONE_SLAB.get());
        gen.wall(LFItems.MOSSY_COBBLESTONE.get(), LFItems.MOSSY_COBBLESTONE_WALL.get());
        gen.cut(LFItems.MOSSY_COBBLESTONE.get(), LFItems.MOSSY_COBBLESTONE_STAIRS.get(), 1);
        gen.cut(LFItems.MOSSY_COBBLESTONE.get(), LFItems.MOSSY_COBBLESTONE_SLAB.get(), 2);
        gen.cut(LFItems.MOSSY_COBBLESTONE.get(), LFItems.MOSSY_COBBLESTONE_WALL.get());

        // Bricks
        gen.shaped2x2(RecipeCategory.BUILDING_BLOCKS, LFItems.BRICKS.get(), LFItems.BRICK.get(), 1);
        gen.stairs(LFItems.BRICKS.get(), LFItems.BRICK_STAIRS.get());
        gen.slab(LFItems.BRICKS.get(), LFItems.BRICK_SLAB.get());
        gen.wall(LFItems.BRICKS.get(), LFItems.BRICK_WALL.get());
        gen.cut(LFItems.BRICKS.get(), LFItems.BRICK_STAIRS.get(), 1);
        gen.cut(LFItems.BRICKS.get(), LFItems.BRICK_SLAB.get(), 2);
        gen.cut(LFItems.BRICKS.get(), LFItems.BRICK_WALL.get());

        // Clay & Brick item
        gen.shaped2x2(RecipeCategory.BUILDING_BLOCKS, LFItems.CLAY.get(), LFItems.CLAY_BALL.get(), 1);
        gen.smelt(LFItems.CLAY_BALL.get(), LFItems.BRICK.get(), 0.3f);

        // Glass
        gen.smelt(LFItems.SAND.get(), LFItems.GLASS.get(), 0.1f);
        gen.bars(LFItems.GLASS.get(), LFItems.GLASS_PANE.get());

        // TNT
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, LFItems.TNT.get())
            .define('#', Items.GUNPOWDER)
            .define('X', LFItems.SAND.get())
            .pattern("#X#")
            .pattern("X#X")
            .pattern("#X#")
            .unlockedBy(getHasName(LFItems.SAND.get()), has(LFItems.SAND.get()))
            .save(writer);

        // Wood 6-sided
        gen.shaped2x2(RecipeCategory.BUILDING_BLOCKS, LFItems.WOOD.get(), LFItems.WOOD_6_SIDED.get(), 3);

        // Furnace and gen Table
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.FURNACE, 1)
            .define('#', LFItems.COBBLESTONE.get())
            .pattern("###")
            .pattern("# #")
            .pattern("###")
            .unlockedBy(getHasName(LFItems.COBBLESTONE.get()), has(LFItems.COBBLESTONE.get()))
            .save(writer, LeftForgotten.asResource("furnace"));

        // Resource Blocks
        gen.shaped3x3(RecipeCategory.BUILDING_BLOCKS, LFItems.IRON_BLOCK.get(), Items.IRON_INGOT, 1);
        gen.shaped3x3(RecipeCategory.BUILDING_BLOCKS, LFItems.GOLD_BLOCK.get(), Items.GOLD_INGOT, 1);
        gen.shaped3x3(RecipeCategory.BUILDING_BLOCKS, LFItems.DIAMOND_BLOCK.get(), Items.DIAMOND, 1);
        gen.shapeless(RecipeCategory.MISC, Items.IRON_INGOT, 9, LFItems.IRON_BLOCK.get());
        gen.shapeless(RecipeCategory.MISC, Items.GOLD_INGOT, 9, LFItems.GOLD_BLOCK.get());
        gen.shapeless(RecipeCategory.MISC, Items.DIAMOND, 9, LFItems.DIAMOND_BLOCK.get());

        // Building / Iron
        gen.door(LFItems.IRON_ORE.get(), LFItems.IRON_DOOR.get());

        // Boat
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, LFItems.BOAT.get())
            .define('#', LFItems.WOODEN_PLANKS.get())
            .pattern("# #")
            .pattern("###")
            .unlockedBy(getHasName(LFItems.WOODEN_PLANKS.get()), has(LFItems.WOODEN_PLANKS.get()))
            .save(writer, LeftForgotten.asResource("boat"));
    }
}