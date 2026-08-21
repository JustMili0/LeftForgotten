package net.justmili.leftforgotten.datagen.providers;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.datagen.extra.DatagenAssetUtilExtra;
import net.justmili.leftforgotten.libs.v1.utils.common.datagen.DatagenAssetUtil;
import net.justmili.leftforgotten.libs.v1.utils.common.datagen.impl.ImprovedModelProvider;
import net.justmili.leftforgotten.registries.BlockRegistry;
import net.justmili.leftforgotten.registries.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;

import static net.justmili.leftforgotten.libs.v1.utils.common.datagen.DatagenAssetUtil.RotationType;

public class LFModelProvider extends ImprovedModelProvider {
    public LFModelProvider(PackOutput output) {
        super(output, LeftForgotten.MODID);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockGen) {
        var gen = new DatagenAssetUtil(LeftForgotten.MODID, blockGen);
        var noRot = RotationType.NONE;

        // In-Overworld
        gen.createCube(BlockRegistry.BRITTLE_BEDROCK.get(), noRot);

        // Nature / Ground
        gen.createBlock(BlockRegistry.GRASS_BLOCK.get(), RotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(BlockRegistry.GRASS_BLOCK.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.GRASS_BLOCK.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(BlockRegistry.DIRT.get()))
        );
        gen.createCube(BlockRegistry.DIRT.get(), noRot);
        gen.createFarmland(BlockRegistry.FARMLAND.get(), BlockRegistry.DIRT.get());
        gen.createCube(BlockRegistry.GRAVEL.get(), noRot);
        gen.createCube(BlockRegistry.SAND.get(), noRot);
        gen.createCube(BlockRegistry.CLAY.get(), noRot);

        // Nature / Vegetation
        gen.createPlant(BlockRegistry.RED_FLOWER.get(), BlockModelGenerators.TintState.NOT_TINTED);
        gen.createPlant(BlockRegistry.YELLOW_FLOWER.get(), BlockModelGenerators.TintState.NOT_TINTED);
        gen.createPlant(BlockRegistry.RED_MUSHROOM.get(), BlockModelGenerators.TintState.NOT_TINTED);
        gen.createPlant(BlockRegistry.BROWN_MUSHROOM.get(), BlockModelGenerators.TintState.NOT_TINTED);
        gen.createCactus(BlockRegistry.CACTUS.get());
        gen.createPlant(BlockRegistry.SAPLING.get(), BlockModelGenerators.TintState.NOT_TINTED);
        gen.createCube(BlockRegistry.LEAVES.get(), noRot);

        // Building / Wood
        gen.createBlock(BlockRegistry.WOOD.get(), RotationType.LOG_XYZ,
            ModelTemplates.CUBE_COLUMN,
            new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.WOOD.get(), "_side"))
                .put(TextureSlot.END, TextureMapping.getBlockTexture(BlockRegistry.WOOD.get(), "_top"))
        );
        gen.createBlock(BlockRegistry.WOOD_6_SIDED.get(), RotationType.LOG_XYZ,
            ModelTemplates.CUBE_ALL,
            new TextureMapping()
                .put(TextureSlot.ALL, TextureMapping.getBlockTexture(BlockRegistry.WOOD.get(), "_side"))
        );
        gen.createWoodFamily(
            BlockRegistry.WOODEN_PLANKS.get(), BlockRegistry.WOODEN_STAIRS.get(), BlockRegistry.WOODEN_SLAB.get(),
            BlockRegistry.FENCE.get(), BlockRegistry.FENCE_GATE.get(), BlockRegistry.DOOR.get(), BlockRegistry.TRAPDOOR.get());
        gen.createRedstoneFamily(BlockRegistry.WOODEN_PLANKS.get(), BlockRegistry.PRESSURE_PLATE.get(), BlockRegistry.BUTTON.get());

        // Nature / Underground
        gen.createCube(BlockRegistry.COAL_ORE.get(), noRot);
        gen.createCube(BlockRegistry.IRON_ORE.get(), noRot);
        gen.createCube(BlockRegistry.GOLD_ORE.get(), noRot);
        gen.createCube(BlockRegistry.REDSTONE_ORE.get(), noRot);
        gen.createCube(BlockRegistry.DIAMOND_ORE.get(), noRot);
        gen.createStoneFamily(BlockRegistry.STONE.get(), BlockRegistry.STONE_STAIRS.get(), BlockRegistry.STONE_SLAB.get());

        // Building / Stone
        gen.createRedstoneFamily(BlockRegistry.STONE.get(), BlockRegistry.STONE_PRESSURE_PLATE.get(), BlockRegistry.STONE_BUTTON.get());
        gen.createStoneFamily(BlockRegistry.COBBLESTONE.get(), BlockRegistry.COBBLESTONE_STAIRS.get(), BlockRegistry.COBBLESTONE_SLAB.get(), BlockRegistry.COBBLESTONE_WALL.get());
        gen.createStoneFamily(BlockRegistry.MOSSY_COBBLESTONE.get(), BlockRegistry.MOSSY_COBBLESTONE_STAIRS.get(), BlockRegistry.MOSSY_COBBLESTONE_SLAB.get(), BlockRegistry.MOSSY_COBBLESTONE_WALL.get());
        gen.createStoneFamily(BlockRegistry.BRICKS.get(), BlockRegistry.BRICK_STAIRS.get(), BlockRegistry.BRICK_SLAB.get(), BlockRegistry.BRICK_WALL.get());

        // Building / Deco
        gen.createCube(BlockRegistry.OBSIDIAN.get(), noRot);
        gen.createGlassFamily(BlockRegistry.GLASS.get(), BlockRegistry.GLASS_PANE.get());
        gen.createBlock(BlockRegistry.BOOKSHELF.get(), RotationType.NONE,
            ModelTemplates.CUBE_COLUMN,
            new TextureMapping()
                .put(TextureSlot.END, TextureMapping.getBlockTexture(BlockRegistry.WOODEN_PLANKS.get()))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.BOOKSHELF.get()))
        );
        gen.createBlock(BlockRegistry.TNT.get(), RotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(BlockRegistry.TNT.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.TNT.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(BlockRegistry.TNT.get(), "_bottom"))
        );
        gen.createBlock(BlockRegistry.IRON_BLOCK.get(), RotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(BlockRegistry.IRON_BLOCK.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.IRON_BLOCK.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(BlockRegistry.IRON_BLOCK.get(), "_bottom"))
        );
        gen.createBlock(BlockRegistry.GOLD_BLOCK.get(), RotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(BlockRegistry.GOLD_BLOCK.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.GOLD_BLOCK.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(BlockRegistry.GOLD_BLOCK.get(), "_bottom"))
        );
        gen.createBlock(BlockRegistry.DIAMOND_BLOCK.get(), RotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(BlockRegistry.DIAMOND_BLOCK.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(BlockRegistry.DIAMOND_BLOCK.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(BlockRegistry.DIAMOND_BLOCK.get(), "_bottom"))
        );

        // Building / Iron
        blockGen.createDoor(BlockRegistry.IRON_DOOR.get());

        // Dev
        gen.createCube(BlockRegistry.FEATURE_VOID.get(), noRot);
        gen.createCraftingTable(BlockRegistry.REMODEL_CRAFTING_TABLE.get(), BlockRegistry.WOODEN_PLANKS.get());
        DatagenAssetUtilExtra.createFurnaceCUSTOM(blockGen, BlockRegistry.REMODEL_FURNACE.get(), BlockRegistry.STONE.get());
        gen.createChest(BlockRegistry.REMODEL_CHEST.get());
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemGen) {
        var gen = new DatagenAssetUtil(LeftForgotten.MODID, itemGen);

        gen.createFlatItem(ItemRegistry.CLAY_BALL.get());
        gen.createFlatItem(ItemRegistry.BRICK.get());
        gen.createFlatItem(ItemRegistry.BOAT.get());
    }
}