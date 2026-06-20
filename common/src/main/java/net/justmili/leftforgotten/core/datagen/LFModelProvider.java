package net.justmili.leftforgotten.core.datagen;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.core.datagen.impl.ImprovedModelProvider;
import net.justmili.leftforgotten.core.util.DatagenAssetUtil;
import net.justmili.leftforgotten.registries.LFBlocks;
import net.justmili.leftforgotten.registries.LFItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;

import static net.justmili.leftforgotten.core.util.DatagenAssetUtil.RotationType;

public class LFModelProvider extends ImprovedModelProvider {
    public LFModelProvider(PackOutput output) {
        super(output, LeftForgotten.MODID);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockGen) {
        var gen = new DatagenAssetUtil(LeftForgotten.MODID, blockGen);

        // In-Overworld
        gen.createCubeAll(LFBlocks.BRITTLE_BEDROCK.get());

        // Nature / Ground
        gen.createCube(LFBlocks.GRASS_BLOCK.get(), RotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(LFBlocks.GRASS_BLOCK.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.GRASS_BLOCK.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(LFBlocks.DIRT.get()))
        );
        gen.createCubeAll(LFBlocks.DIRT.get());
        gen.createFarmland(LFBlocks.FARMLAND.get(), LFBlocks.DIRT.get());
        gen.createCubeAll(LFBlocks.GRAVEL.get());
        gen.createCubeAll(LFBlocks.SAND.get());
        gen.createCubeAll(LFBlocks.CLAY.get());

        // Nature / Vegetation
        gen.createPlant(LFBlocks.RED_FLOWER.get(), BlockModelGenerators.TintState.NOT_TINTED);
        gen.createPlant(LFBlocks.YELLOW_FLOWER.get(), BlockModelGenerators.TintState.NOT_TINTED);
        gen.createPlant(LFBlocks.RED_MUSHROOM.get(), BlockModelGenerators.TintState.NOT_TINTED);
        gen.createPlant(LFBlocks.BROWN_MUSHROOM.get(), BlockModelGenerators.TintState.NOT_TINTED);
        gen.createCactus(LFBlocks.CACTUS.get());
        gen.createPlant(LFBlocks.SAPLING.get(), BlockModelGenerators.TintState.NOT_TINTED);
        gen.createCubeAll(LFBlocks.LEAVES.get());

        // Building / Wood
        gen.createCube(LFBlocks.WOOD.get(), RotationType.LOG_XYZ,
            ModelTemplates.CUBE_COLUMN,
            new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.WOOD.get(), "_side"))
                .put(TextureSlot.END, TextureMapping.getBlockTexture(LFBlocks.WOOD.get(), "_top"))
        );
        gen.createCube(LFBlocks.WOOD_6_SIDED.get(), RotationType.LOG_XYZ,
            ModelTemplates.CUBE_ALL,
            new TextureMapping()
                .put(TextureSlot.ALL, TextureMapping.getBlockTexture(LFBlocks.WOOD.get(), "_side"))
        );
        gen.createWoodFamily(
            LFBlocks.WOODEN_PLANKS.get(), LFBlocks.WOODEN_STAIRS.get(), LFBlocks.WOODEN_SLAB.get(),
            LFBlocks.FENCE.get(), LFBlocks.FENCE_GATE.get(), LFBlocks.DOOR.get(), LFBlocks.TRAPDOOR.get());
        gen.createRedstoneFamily(LFBlocks.WOODEN_PLANKS.get(), LFBlocks.PRESSURE_PLATE.get(), LFBlocks.BUTTON.get());

        // Nature / Underground
        gen.createCubeAll(LFBlocks.COAL_ORE.get());
        gen.createCubeAll(LFBlocks.IRON_ORE.get());
        gen.createCubeAll(LFBlocks.GOLD_ORE.get());
        gen.createCubeAll(LFBlocks.REDSTONE_ORE.get());
        gen.createCubeAll(LFBlocks.DIAMOND_ORE.get());
        gen.createStoneFamily(LFBlocks.STONE.get(), LFBlocks.STONE_STAIRS.get(), LFBlocks.STONE_SLAB.get());

        // Building / Stone
        gen.createRedstoneFamily(LFBlocks.STONE.get(), LFBlocks.STONE_PRESSURE_PLATE.get(), LFBlocks.STONE_BUTTON.get());
        gen.createStoneFamily(LFBlocks.COBBLESTONE.get(), LFBlocks.COBBLESTONE_STAIRS.get(), LFBlocks.COBBLESTONE_SLAB.get(), LFBlocks.COBBLESTONE_WALL.get());
        gen.createStoneFamily(LFBlocks.MOSSY_COBBLESTONE.get(), LFBlocks.MOSSY_COBBLESTONE_STAIRS.get(), LFBlocks.MOSSY_COBBLESTONE_SLAB.get(), LFBlocks.MOSSY_COBBLESTONE_WALL.get());
        gen.createStoneFamily(LFBlocks.BRICKS.get(), LFBlocks.BRICK_STAIRS.get(), LFBlocks.BRICK_SLAB.get(), LFBlocks.BRICK_WALL.get());

        // Building / Deco
        gen.createCubeAll(LFBlocks.OBSIDIAN.get());
        gen.createGlassFamily(LFBlocks.GLASS.get(), LFBlocks.GLASS_PANE.get());
        gen.createCube(LFBlocks.BOOKSHELF.get(), RotationType.NONE,
            ModelTemplates.CUBE_COLUMN,
            new TextureMapping()
                .put(TextureSlot.END, TextureMapping.getBlockTexture(LFBlocks.WOODEN_PLANKS.get()))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.BOOKSHELF.get()))
        );
        gen.createCube(LFBlocks.TNT.get(), RotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(LFBlocks.TNT.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.TNT.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(LFBlocks.TNT.get(), "_bottom"))
        );
        gen.createCube(LFBlocks.IRON_BLOCK.get(), RotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(LFBlocks.IRON_BLOCK.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.IRON_BLOCK.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(LFBlocks.IRON_BLOCK.get(), "_bottom"))
        );
        gen.createCube(LFBlocks.GOLD_BLOCK.get(), RotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(LFBlocks.GOLD_BLOCK.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.GOLD_BLOCK.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(LFBlocks.GOLD_BLOCK.get(), "_bottom"))
        );
        gen.createCube(LFBlocks.DIAMOND_BLOCK.get(), RotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(LFBlocks.DIAMOND_BLOCK.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.DIAMOND_BLOCK.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(LFBlocks.DIAMOND_BLOCK.get(), "_bottom"))
        );

        // Building / Iron
        blockGen.createDoor(LFBlocks.IRON_DOOR.get());

        // Dev
        gen.createCubeAll(LFBlocks.FEATURE_VOID.get());
        gen.createCraftingTable(LFBlocks.REMODEL_CRAFTING_TABLE.get(), LFBlocks.WOODEN_PLANKS.get());
        gen.createFurnaceCUSTOM(LFBlocks.REMODEL_FURNACE.get(), LFBlocks.STONE.get());
        gen.createChest(LFBlocks.REMODEL_CHEST.get());
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemGen) {
        var gen = new DatagenAssetUtil(LeftForgotten.MODID, itemGen);

        gen.createFlatItem(LFItems.CLAY_BALL.get());
        gen.createFlatItem(LFItems.BRICK.get());
        gen.createFlatItem(LFItems.BOAT.get());
    }
}