package net.justmili.leftforgotten.libs.v1.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DatagenAssetUtil {
    private BlockModelGenerators blockGen;
    private ItemModelGenerators itemGen;
    private final String modId;

    public DatagenAssetUtil(String modId, BlockModelGenerators blockGen) {
        this.modId = modId;
        this.blockGen = blockGen;
    }
    public DatagenAssetUtil(String modId, ItemModelGenerators itemGen) {
        this.modId = modId;
        this.itemGen = itemGen;
    }


    public void createWoodFamily(Block planks, Block stairs, Block slab, Block fence, Block fenceGate, Block door, Block trapdoor) {
        TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
        TextureMapping mapping = texturedModel.getMapping();

        // Planks
        ResourceLocation fullBlock = texturedModel.create(planks, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(planks, fullBlock));

        // Stairs
        ResourceLocation stairsInner = ModelTemplates.STAIRS_INNER.create(stairs, mapping, blockGen.modelOutput);
        ResourceLocation stairsStraight = ModelTemplates.STAIRS_STRAIGHT.create(stairs, mapping, blockGen.modelOutput);
        ResourceLocation stairsOuter = ModelTemplates.STAIRS_OUTER.create(stairs, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs, stairsInner, stairsStraight, stairsOuter));
        blockGen.delegateItemModel(stairs, stairsStraight);

        // Slab
        ResourceLocation slabBottom = ModelTemplates.SLAB_BOTTOM.create(slab, mapping, blockGen.modelOutput);
        ResourceLocation slabTop = ModelTemplates.SLAB_TOP.create(slab, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSlab(slab, slabBottom, slabTop, fullBlock));
        blockGen.delegateItemModel(slab, slabBottom);

        // Fence
        ResourceLocation fencePost = ModelTemplates.FENCE_POST.create(fence, mapping, blockGen.modelOutput);
        ResourceLocation fenceSide = ModelTemplates.FENCE_SIDE.create(fence, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createFence(fence, fencePost, fenceSide));
        blockGen.delegateItemModel(fence, ModelTemplates.FENCE_INVENTORY.create(fence, mapping, blockGen.modelOutput));

        // Fence gate
        ResourceLocation fgOpen = ModelTemplates.FENCE_GATE_OPEN.create(fenceGate, mapping, blockGen.modelOutput);
        ResourceLocation fgClosed = ModelTemplates.FENCE_GATE_CLOSED.create(fenceGate, mapping, blockGen.modelOutput);
        ResourceLocation fgWallOpen = ModelTemplates.FENCE_GATE_WALL_OPEN.create(fenceGate, mapping, blockGen.modelOutput);
        ResourceLocation fgWallClosed = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(fenceGate, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createFenceGate(fenceGate, fgOpen, fgClosed, fgWallOpen, fgWallClosed, true));

        // Door & Trapdoor
        blockGen.createDoor(door);
        blockGen.createOrientableTrapdoor(trapdoor);
    }

    public void createStoneFamily(Block stoneType, Block stairs, Block slab) {
        TexturedModel texturedModel = TexturedModel.CUBE.get(stoneType);
        TextureMapping mapping = texturedModel.getMapping();

        // Stone block
        ResourceLocation fullBlock = texturedModel.create(stoneType, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(stoneType, fullBlock));

        //Stairs
        ResourceLocation stairsInner = ModelTemplates.STAIRS_INNER.create(stairs, mapping, blockGen.modelOutput);
        ResourceLocation stairsStraight = ModelTemplates.STAIRS_STRAIGHT.create(stairs, mapping, blockGen.modelOutput);
        ResourceLocation stairsOuter = ModelTemplates.STAIRS_OUTER.create(stairs, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs, stairsInner, stairsStraight, stairsOuter));
        blockGen.delegateItemModel(stairs, stairsStraight);

        // Slab
        ResourceLocation slabBottom = ModelTemplates.SLAB_BOTTOM.create(slab, mapping, blockGen.modelOutput);
        ResourceLocation slabTop = ModelTemplates.SLAB_TOP.create(slab, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSlab(slab, slabBottom, slabTop, fullBlock));
        blockGen.delegateItemModel(slab, slabBottom);
    }

    public void createStoneFamily(Block block, Block stairs, Block slab, Block wall) {
        // Call no-wall createStoneFamily
        createStoneFamily(block, stairs, slab);

        // Wall
        TextureMapping mapping = TexturedModel.CUBE.get(block).getMapping();
        ResourceLocation wallPost = ModelTemplates.WALL_POST.create(wall, mapping, blockGen.modelOutput);
        ResourceLocation wallLow = ModelTemplates.WALL_LOW_SIDE.create(wall, mapping, blockGen.modelOutput);
        ResourceLocation wallTall = ModelTemplates.WALL_TALL_SIDE.create(wall, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createWall(wall, wallPost, wallLow, wallTall));
        blockGen.delegateItemModel(wall, ModelTemplates.WALL_INVENTORY.create(wall, mapping, blockGen.modelOutput));
    }

    public void createRedstoneFamily(Block block, Block pressurePlate, Block button) {
        TextureMapping mapping = TexturedModel.CUBE.get(block).getMapping();

        if (pressurePlate != null) {
            ResourceLocation ppUp = ModelTemplates.PRESSURE_PLATE_UP.create(pressurePlate, mapping, blockGen.modelOutput);
            ResourceLocation ppDown = ModelTemplates.PRESSURE_PLATE_DOWN.create(pressurePlate, mapping, blockGen.modelOutput);
            blockGen.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(pressurePlate, ppUp, ppDown));
        }

        if (button != null) {
            ResourceLocation btn = ModelTemplates.BUTTON.create(button, mapping, blockGen.modelOutput);
            ResourceLocation btnPressed = ModelTemplates.BUTTON_PRESSED.create(button, mapping, blockGen.modelOutput);
            blockGen.blockStateOutput.accept(BlockModelGenerators.createButton(button, btn, btnPressed));
            blockGen.delegateItemModel(button, ModelTemplates.BUTTON_INVENTORY.create(button, mapping, blockGen.modelOutput));
        }
    }

    public void createGlassFamily(Block glass, Block pane) {
        blockGen.createGlassBlocks(glass, pane);
    }

    /**
     * Individual
     * Cubes
     */
    public void createCubeAll(Block block) {
        blockGen.createTrivialCube(block);
    }

    public void createCube(Block block, RotationType rotationType) {
        createCube(block, rotationType, ModelTemplates.CUBE_ALL, TextureMapping.cube(block));
    }

    public void createCube(Block block, RotationType rotationType,
                           ModelTemplate template, TextureMapping textureMapping) {
        ResourceLocation model = template.create(block, textureMapping, blockGen.modelOutput);
        switch (rotationType) {
            case NONE -> blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, model));
            case HORIZONTAL_Y -> blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                    Variant.variant().with(VariantProperties.MODEL, model))
                .with(BlockModelGenerators.createHorizontalFacingDispatch()));
            case ALL_DIRECTIONS -> blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                    Variant.variant().with(VariantProperties.MODEL, model))
                .with(BlockModelGenerators.createFacingDispatch()));
            case LOG_XYZ ->
                blockGen.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(block, model));
        }
    }

    public enum RotationType {
        NONE,
        HORIZONTAL_Y, // S/W/N/E y-axis
        ALL_DIRECTIONS, // D/U/N/S/W/E
        LOG_XYZ // Log XYZ axis
    }

    /**
     * Functional block templates
     */
    public void createCraftingTable(Block table, Block bottomTexture) {
        TextureMapping mapping = new TextureMapping()
            .put(TextureSlot.PARTICLE, ResourceUtil.mapTextureFront(table))
            .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(bottomTexture))
            .put(TextureSlot.UP, ResourceUtil.mapTextureTop(table))
            .put(TextureSlot.NORTH, ResourceUtil.mapTextureFront(table))
            .put(TextureSlot.EAST, ResourceUtil.mapTextureSide(table))
            .put(TextureSlot.SOUTH, ResourceUtil.mapTextureFront(table))
            .put(TextureSlot.WEST, ResourceUtil.mapTextureSide(table));

        ResourceLocation model = ModelTemplates.CUBE.create(table, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(table, model));
        blockGen.delegateItemModel(table, model);
    }

    public void createFurnace(Block block) {
        TextureMapping unlitMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFront(block))
            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block));
        TextureMapping litMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFrontOn(block))
            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block));

        ResourceLocation unlitModel = ModelTemplates.CUBE_ORIENTABLE.create(block, unlitMapping, blockGen.modelOutput);
        ResourceLocation litModel = ModelTemplates.CUBE_ORIENTABLE.create(
            ResourceUtil.mapTextureOn(block), litMapping, blockGen.modelOutput);

        blockGen.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(block)
                .with(BlockModelGenerators.createHorizontalFacingDispatch())
                .with(PropertyDispatch.property(BlockStateProperties.LIT)
                    .select(false, Variant.variant().with(VariantProperties.MODEL, unlitModel))
                    .select(true, Variant.variant().with(VariantProperties.MODEL, litModel)))
        );

        blockGen.delegateItemModel(block, unlitModel);
    }

    public void createChest(Block block) {
        TextureMapping singleMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFront(block));

        TextureMapping leftMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFrontRight(block))
            .put(TextureSlot.SOUTH, ResourceUtil.mapTextureBackRight(block));

        TextureMapping rightMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFrontLeft(block))
            .put(TextureSlot.SOUTH, ResourceUtil.mapTextureBackLeft(block));

        ModelTemplate customBackOrientable = new ModelTemplate(Optional.of(ResourceUtil.asBlockPath("orientable")),
            Optional.empty(), TextureSlot.TOP, TextureSlot.FRONT, TextureSlot.SIDE, TextureSlot.SOUTH);

        ResourceLocation singleModel = ModelTemplates.CUBE_ORIENTABLE.create(block, singleMapping, blockGen.modelOutput);
        ResourceLocation leftModel = customBackOrientable.create(ResourceUtil.mapTextureLeft(block),
            leftMapping, blockGen.modelOutput);
        ResourceLocation rightModel = customBackOrientable.create(ResourceUtil.mapTextureRight(block),
            rightMapping, blockGen.modelOutput);

        blockGen.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(block)
                .with(BlockModelGenerators.createHorizontalFacingDispatch())
                .with(PropertyDispatch.property(BlockStateProperties.WATERLOGGED)
                    .select(false, Variant.variant())
                    .select(true, Variant.variant())
                )
                .with(PropertyDispatch.property(BlockStateProperties.CHEST_TYPE)
                    .select(ChestType.SINGLE, Variant.variant().with(VariantProperties.MODEL, singleModel))
                    .select(ChestType.LEFT, Variant.variant().with(VariantProperties.MODEL, leftModel))
                    .select(ChestType.RIGHT, Variant.variant().with(VariantProperties.MODEL, rightModel))
                )
        );

        blockGen.delegateItemModel(block, singleModel);
    }

    /**
     * Individual
     * Magic or whatever
     * Portals
     */
    public void createNetherPortal(Block block) {
        blockGen.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.property(BlockStateProperties.HORIZONTAL_AXIS)
                    .select(Direction.Axis.X, Variant.variant()
                        .with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, "_ns")))
                    .select(Direction.Axis.Z, Variant.variant()
                        .with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, "_ew")))
                )
        );
    }

    /**
     * Individual
     * Nature
     */
    public void createFarmland(Block topTexture, Block wrapTexture) {
        TextureMapping dry = new TextureMapping()
            .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(wrapTexture))
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(topTexture));
        TextureMapping moist = new TextureMapping()
            .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(wrapTexture))
            .put(TextureSlot.TOP,ResourceUtil.mapTextureMoist(topTexture));
        ResourceLocation dryModel = ModelTemplates.FARMLAND.create(topTexture, dry, blockGen.modelOutput);
        ResourceLocation moistModel = ModelTemplates.FARMLAND.create(
            ResourceUtil.mapTextureMoist(topTexture), moist, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(topTexture)
            .with(BlockModelGenerators.createEmptyOrFullDispatch(
                BlockStateProperties.MOISTURE, 7, moistModel, dryModel)));
    }

    public void createCactus(Block block) {
        ResourceLocation sideTexture = ResourceUtil.mapTextureSide(block);
        ResourceLocation bottomTexture = ResourceUtil.mapTextureBottom(block);
        ResourceLocation topTexture = ResourceUtil.mapTextureTop(block);

        ResourceLocation modelLocation = ModelLocationUtils.getModelLocation(block);

        blockGen.modelOutput.accept(modelLocation, () -> {
            JsonObject root = new JsonObject();
            root.addProperty("parent", "block/block");

            JsonObject textures = new JsonObject();
            textures.addProperty("particle", sideTexture.toString());
            textures.addProperty("bottom", bottomTexture.toString());
            textures.addProperty("top", topTexture.toString());
            textures.addProperty("side", sideTexture.toString());
            root.add("textures", textures);

            JsonArray elements = new JsonArray();

            // Top and bottom faces
            JsonObject el1 = new JsonObject();
            el1.add("from", toJsonArray(0, 0, 0));
            el1.add("to", toJsonArray(16, 16, 16));
            JsonObject faces1 = new JsonObject();
            faces1.add("down", face("0 0 16 16", "#bottom", "down"));
            faces1.add("up", face("0 0 16 16", "#top", "up"));
            el1.add("faces", faces1);
            elements.add(el1);

            // North/south faces
            JsonObject el2 = new JsonObject();
            el2.add("from", toJsonArray(0, 0, 1));
            el2.add("to", toJsonArray(16, 16, 15));
            JsonObject faces2 = new JsonObject();
            faces2.add("north", face("0 0 16 16", "#side", null));
            faces2.add("south", face("0 0 16 16", "#side", null));
            el2.add("faces", faces2);
            elements.add(el2);

            // West/east faces
            JsonObject el3 = new JsonObject();
            el3.add("from", toJsonArray(1, 0, 0));
            el3.add("to", toJsonArray(15, 16, 16));
            JsonObject faces3 = new JsonObject();
            faces3.add("west", face("0 0 16 16", "#side", null));
            faces3.add("east", face("0 0 16 16", "#side", null));
            el3.add("faces", faces3);
            elements.add(el3);

            root.add("elements", elements);
            return root;
        });

        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, modelLocation));
    }

    public void createPlant(Block block, BlockModelGenerators.TintState tintState) {
        blockGen.createCrossBlockWithDefaultItem(block, tintState);
    }

    public void createTallPlant(Block block, BlockModelGenerators.TintState tintState) {
        blockGen.createDoublePlant(block, tintState);
    }

    public void createCrop(Block block,
                           Property<Integer> ageProperty, int... ageToVisualStageMapping) {
        blockGen.createCropBlock(block, ageProperty, ageToVisualStageMapping);
    }

    public void createY(Block block, ResourceLocation modelLocation) {
        blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                Variant.variant().with(VariantProperties.MODEL, modelLocation))
            .with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }

    public void createUPNWSE(Block block, ResourceLocation modelLocation) {
        blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                Variant.variant().with(VariantProperties.MODEL, modelLocation))
            .with(BlockModelGenerators.createFacingDispatch()));
    }

    public void createXYZ(Block block, ResourceLocation modelLocation) {
        blockGen.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(block, modelLocation));
    }

    public void createFlatItem(Item item) {
        itemGen.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }

    public void createFlatBlockItem(Block block) {
        itemGen.generateFlatItem(Item.BY_BLOCK.get(block), ModelTemplates.FLAT_ITEM);
    }

    /// Generation helpers
    private static JsonArray toJsonArray(int x, int y, int z) {
        JsonArray arr = new JsonArray();
        arr.add(x);
        arr.add(y);
        arr.add(z);
        return arr;
    }

    private JsonObject face(String uv, String texture, @Nullable String cullface) {
        JsonObject face = new JsonObject();
        String[] parts = uv.split(" ");
        JsonArray uvArr = new JsonArray();
        for (String p : parts) uvArr.add(Integer.parseInt(p));
        face.add("uv", uvArr);
        face.addProperty("texture", texture);
        if (cullface != null) face.addProperty("cullface", cullface);
        return face;
    }
}
