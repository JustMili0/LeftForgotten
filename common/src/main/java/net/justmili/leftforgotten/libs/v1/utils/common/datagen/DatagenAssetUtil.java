package net.justmili.leftforgotten.libs.v1.utils.common.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.Nullable;

public class DatagenAssetUtil {
    public static final VariantProperty<VariantProperties.Rotation> Y_ROT = VariantProperties.Y_ROT, X_ROT = VariantProperties.X_ROT;
    public static final VariantProperties.Rotation Y_ROT_90 = VariantProperties.Rotation.R90, Y_ROT_180 = VariantProperties.Rotation.R180 ,Y_ROT_270 = VariantProperties.Rotation.R270;
    public static final VariantProperties.Rotation X_ROT_90 = VariantProperties.Rotation.R90, X_ROT_180 = VariantProperties.Rotation.R180 ,X_ROT_270 = VariantProperties.Rotation.R270;

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
        var texturedModel = TexturedModel.CUBE.get(planks);
        var mapping = texturedModel.getMapping();

        // Planks
        var fullBlock = texturedModel.create(planks, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(planks, fullBlock));

        // Stairs
        var stairsInner = ModelTemplates.STAIRS_INNER.create(stairs, mapping, blockGen.modelOutput);
        var stairsStraight = ModelTemplates.STAIRS_STRAIGHT.create(stairs, mapping, blockGen.modelOutput);
        var stairsOuter = ModelTemplates.STAIRS_OUTER.create(stairs, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs, stairsInner, stairsStraight, stairsOuter));
        blockGen.delegateItemModel(stairs, stairsStraight);

        // Slab
        var slabBottom = ModelTemplates.SLAB_BOTTOM.create(slab, mapping, blockGen.modelOutput);
        var slabTop = ModelTemplates.SLAB_TOP.create(slab, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSlab(slab, slabBottom, slabTop, fullBlock));
        blockGen.delegateItemModel(slab, slabBottom);

        // Fence
        var fencePost = ModelTemplates.FENCE_POST.create(fence, mapping, blockGen.modelOutput);
        var fenceSide = ModelTemplates.FENCE_SIDE.create(fence, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createFence(fence, fencePost, fenceSide));
        blockGen.delegateItemModel(fence, ModelTemplates.FENCE_INVENTORY.create(fence, mapping, blockGen.modelOutput));

        // Fence gate
        var fgOpen = ModelTemplates.FENCE_GATE_OPEN.create(fenceGate, mapping, blockGen.modelOutput);
        var fgClosed = ModelTemplates.FENCE_GATE_CLOSED.create(fenceGate, mapping, blockGen.modelOutput);
        var fgWallOpen = ModelTemplates.FENCE_GATE_WALL_OPEN.create(fenceGate, mapping, blockGen.modelOutput);
        var fgWallClosed = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(fenceGate, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createFenceGate(fenceGate, fgOpen, fgClosed, fgWallOpen, fgWallClosed, true));

        // Door & Trapdoor
        blockGen.createDoor(door);
        blockGen.createOrientableTrapdoor(trapdoor);
    }

    public void createStoneFamily(Block stoneType, Block stairs, Block slab) {
        var texturedModel = TexturedModel.CUBE.get(stoneType);
        var mapping = texturedModel.getMapping();

        // Stone block
        var fullBlock = texturedModel.create(stoneType, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(stoneType, fullBlock));

        //Stairs
        var stairsInner = ModelTemplates.STAIRS_INNER.create(stairs, mapping, blockGen.modelOutput);
        var stairsStraight = ModelTemplates.STAIRS_STRAIGHT.create(stairs, mapping, blockGen.modelOutput);
        var stairsOuter = ModelTemplates.STAIRS_OUTER.create(stairs, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs, stairsInner, stairsStraight, stairsOuter));
        blockGen.delegateItemModel(stairs, stairsStraight);

        // Slab
        var slabBottom = ModelTemplates.SLAB_BOTTOM.create(slab, mapping, blockGen.modelOutput);
        var slabTop = ModelTemplates.SLAB_TOP.create(slab, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSlab(slab, slabBottom, slabTop, fullBlock));
        blockGen.delegateItemModel(slab, slabBottom);
    }

    public void createStoneFamily(Block block, Block stairs, Block slab, Block wall) {
        // Call no-wall createStoneFamily
        createStoneFamily(block, stairs, slab);

        // Wall
        var mapping = TexturedModel.CUBE.get(block).getMapping();
        var wallPost = ModelTemplates.WALL_POST.create(wall, mapping, blockGen.modelOutput);
        var wallLow = ModelTemplates.WALL_LOW_SIDE.create(wall, mapping, blockGen.modelOutput);
        var wallTall = ModelTemplates.WALL_TALL_SIDE.create(wall, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createWall(wall, wallPost, wallLow, wallTall));
        blockGen.delegateItemModel(wall, ModelTemplates.WALL_INVENTORY.create(wall, mapping, blockGen.modelOutput));
    }

    public void createRedstoneFamily(Block block, Block pressurePlate, Block button) {
        var mapping = TexturedModel.CUBE.get(block).getMapping();

        if (pressurePlate != null) {
            var ppUp = ModelTemplates.PRESSURE_PLATE_UP.create(pressurePlate, mapping, blockGen.modelOutput);
            var ppDown = ModelTemplates.PRESSURE_PLATE_DOWN.create(pressurePlate, mapping, blockGen.modelOutput);
            blockGen.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(pressurePlate, ppUp, ppDown));
        }

        if (button != null) {
            var btn = ModelTemplates.BUTTON.create(button, mapping, blockGen.modelOutput);
            var btnPressed = ModelTemplates.BUTTON_PRESSED.create(button, mapping, blockGen.modelOutput);
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
    public void createCube(Block block, RotationType rotationType) {
        createBlock(block, rotationType, ModelTemplates.CUBE_ALL, TextureMapping.cube(block));
    }

    public void createCarpet(Block carpetBlock, Block fullBlock) {
        var model = ModelTemplates.CARPET.create(carpetBlock, TextureMapping.wool(fullBlock), blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(carpetBlock, model));
    }
    public void createCubeAndCarpet(Block fullBlock, Block carpetBlock, RotationType rotationType) {
        createCube(fullBlock, rotationType);
        createCarpet(carpetBlock, fullBlock);
    }

    public void createBlock(Block block, RotationType rotationType, ModelTemplate template, TextureMapping textureMapping) {
        var model = template.create(block, textureMapping, blockGen.modelOutput);
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

    public final void createNonTemplateModelBlock(Block block) {
        createNonTemplateModelBlock(block, block);
    }
    public final void createNonTemplateModelBlock(Block block, Block modelBlock) {
        blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(modelBlock))));
    }

    public void createNonTemplateConnectable(Block block) {
        blockGen.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(block).with(PropertyDispatch.properties(BlockStateProperties.EAST, BlockStateProperties.NORTH, BlockStateProperties.SOUTH, BlockStateProperties.WEST)
                .select(false, false, false, false, getPlainVariantModelLoc(block, "_ns"))
                .select(true, false, false, false, getPlainVariantModelLoc(block, "_n").with(Y_ROT, Y_ROT_90))
                .select(false, true, false, false, getPlainVariantModelLoc(block, "_n"))
                .select(false, false, true, false, getPlainVariantModelLoc(block, "_n").with(Y_ROT, Y_ROT_180))
                .select(false, false, false, true, getPlainVariantModelLoc(block, "_n").with(Y_ROT, Y_ROT_270))
                .select(true, true, false, false, getPlainVariantModelLoc(block, "_ne"))
                .select(true, false, true, false, getPlainVariantModelLoc(block, "_ne").with(Y_ROT, Y_ROT_90))
                .select(false, false, true, true, getPlainVariantModelLoc(block, "_ne").with(Y_ROT, Y_ROT_180))
                .select(false, true, false, true, getPlainVariantModelLoc(block, "_ne").with(Y_ROT, Y_ROT_270))
                .select(false, true, true, false, getPlainVariantModelLoc(block, "_ns"))
                .select(true, false, false, true, getPlainVariantModelLoc(block, "_ns").with(Y_ROT, Y_ROT_90))
                .select(true, true, true, false, getPlainVariantModelLoc(block, "_nse"))
                .select(true, false, true, true, getPlainVariantModelLoc(block, "_nse").with(Y_ROT, Y_ROT_90))
                .select(false, true, true, true, getPlainVariantModelLoc(block, "_nse").with(Y_ROT, Y_ROT_180))
                .select(true, true, false, true, getPlainVariantModelLoc(block, "_nse").with(Y_ROT, Y_ROT_270))
                .select(true, true, true, true, getPlainVariantModelLoc(block, "_nsew"))
            ));
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
        var mapping = new TextureMapping()
            .put(TextureSlot.PARTICLE, ResourceUtil.mapTextureFront(table))
            .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(bottomTexture))
            .put(TextureSlot.UP, ResourceUtil.mapTextureTop(table))
            .put(TextureSlot.NORTH, ResourceUtil.mapTextureFront(table))
            .put(TextureSlot.EAST, ResourceUtil.mapTextureSide(table))
            .put(TextureSlot.SOUTH, ResourceUtil.mapTextureFront(table))
            .put(TextureSlot.WEST, ResourceUtil.mapTextureSide(table));

        var model = ModelTemplates.CUBE.create(table, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(table, model));
        blockGen.delegateItemModel(table, model);
    }

    public void createFurnace(Block block) {
        var unlitMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFront(block))
            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block));
        var litMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFrontOn(block))
            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block));

        var unlitModel = ModelTemplates.CUBE_ORIENTABLE.create(block, unlitMapping, blockGen.modelOutput);
        var litModel = ModelTemplates.CUBE_ORIENTABLE.create(
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
        // TODO: Do proper chest
    }

    public void createTripwire(Block block) {
        blockGen.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(block).with(PropertyDispatch.properties(BlockStateProperties.ATTACHED, BlockStateProperties.EAST, BlockStateProperties.NORTH, BlockStateProperties.SOUTH, BlockStateProperties.WEST)
                .select(false, false, false, false, false, getPlainVariantModelLoc(block, "_ns"))
                .select(false, true, false, false, false, getPlainVariantModelLoc(block, "_n").with(Y_ROT, Y_ROT_90))
                .select(false, false, true, false, false, getPlainVariantModelLoc(block, "_n"))
                .select(false, false, false, true, false, getPlainVariantModelLoc(block, "_n").with(Y_ROT, Y_ROT_180))
                .select(false, false, false, false, true, getPlainVariantModelLoc(block, "_n").with(Y_ROT, Y_ROT_270))
                .select(false, true, true, false, false, getPlainVariantModelLoc(block, "_ne"))
                .select(false, true, false, true, false, getPlainVariantModelLoc(block, "_ne").with(Y_ROT, Y_ROT_90))
                .select(false, false, false, true, true, getPlainVariantModelLoc(block, "_ne").with(Y_ROT, Y_ROT_180))
                .select(false, false, true, false, true, getPlainVariantModelLoc(block, "_ne").with(Y_ROT, Y_ROT_270))
                .select(false, false, true, true, false, getPlainVariantModelLoc(block, "_ns"))
                .select(false, true, false, false, true, getPlainVariantModelLoc(block, "_ns").with(Y_ROT, Y_ROT_90))
                .select(false, true, true, true, false, getPlainVariantModelLoc(block, "_nse"))
                .select(false, true, false, true, true, getPlainVariantModelLoc(block, "_nse").with(Y_ROT, Y_ROT_90))
                .select(false, false, true, true, true, getPlainVariantModelLoc(block, "_nse").with(Y_ROT, Y_ROT_180))
                .select(false, true, true, false, true, getPlainVariantModelLoc(block, "_nse").with(Y_ROT, Y_ROT_270))
                .select(false, true, true, true, true, getPlainVariantModelLoc(block, "_nsew"))
                .select(true, false, false, false, false, getPlainVariantModelLoc(block, "_attached_ns"))
                .select(true, false, true, false, false, getPlainVariantModelLoc(block, "_attached_n"))
                .select(true, false, false, true, false, getPlainVariantModelLoc(block, "_attached_n").with(Y_ROT, Y_ROT_180))
                .select(true, true, false, false, false, getPlainVariantModelLoc(block, "_attached_n").with(Y_ROT, Y_ROT_90))
                .select(true, false, false, false, true, getPlainVariantModelLoc(block, "_attached_n").with(Y_ROT, Y_ROT_270))
                .select(true, true, true, false, false, getPlainVariantModelLoc(block, "_attached_ne"))
                .select(true, true, false, true, false, getPlainVariantModelLoc(block, "_attached_ne").with(Y_ROT, Y_ROT_90))
                .select(true, false, false, true, true, getPlainVariantModelLoc(block, "_attached_ne").with(Y_ROT, Y_ROT_180))
                .select(true, false, true, false, true, getPlainVariantModelLoc(block, "_attached_ne").with(Y_ROT, Y_ROT_270))
                .select(true, false, true, true, false, getPlainVariantModelLoc(block, "_attached_ns"))
                .select(true, true, false, false, true, getPlainVariantModelLoc(block, "_attached_ns").with(Y_ROT, Y_ROT_90))
                .select(true, true, true, true, false, getPlainVariantModelLoc(block, "_attached_nse"))
                .select(true, true, false, true, true, getPlainVariantModelLoc(block, "_attached_nse").with(Y_ROT, Y_ROT_90))
                .select(true, false, true, true, true, getPlainVariantModelLoc(block, "_attached_nse").with(Y_ROT, Y_ROT_180))
                .select(true, true, true, false, true, getPlainVariantModelLoc(block, "_attached_nse").with(Y_ROT, Y_ROT_270))
                .select(true, true, true, true, true, getPlainVariantModelLoc(block, "_attached_nsew"))));
    }
    private Variant getPlainVariantModelLoc(Block block, String suffix) {
        return Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, suffix));
    }

    public void createTripwireHook() {
        // TODO: Code it
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
        var dry = new TextureMapping()
            .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(wrapTexture))
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(topTexture));
        var moist = new TextureMapping()
            .put(TextureSlot.DIRT, TextureMapping.getBlockTexture(wrapTexture))
            .put(TextureSlot.TOP,ResourceUtil.mapTextureMoist(topTexture));
        var dryModel = ModelTemplates.FARMLAND.create(topTexture, dry, blockGen.modelOutput);
        var moistModel = ModelTemplates.FARMLAND.create(
            ResourceUtil.mapTextureMoist(topTexture), moist, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(topTexture)
            .with(BlockModelGenerators.createEmptyOrFullDispatch(
                BlockStateProperties.MOISTURE, 7, moistModel, dryModel)));
    }

    public void createCactus(Block block) {
        var sideTexture = ResourceUtil.mapTextureSide(block);
        var bottomTexture = ResourceUtil.mapTextureBottom(block);
        var topTexture = ResourceUtil.mapTextureTop(block);

        var modelLocation = ModelLocationUtils.getModelLocation(block);

        blockGen.modelOutput.accept(modelLocation, () -> {
            var root = new JsonObject();
            root.addProperty("parent", "block/block");

            var textures = new JsonObject();
            textures.addProperty("particle", sideTexture.toString());
            textures.addProperty("bottom", bottomTexture.toString());
            textures.addProperty("top", topTexture.toString());
            textures.addProperty("side", sideTexture.toString());
            root.add("textures", textures);

            var elements = new JsonArray();

            // Top and bottom faces
            var el1 = new JsonObject();
            el1.add("from", toJsonArray(0, 0, 0));
            el1.add("to", toJsonArray(16, 16, 16));
            var faces1 = new JsonObject();
            faces1.add("down", face("0 0 16 16", "#bottom", "down"));
            faces1.add("up", face("0 0 16 16", "#top", "up"));
            el1.add("faces", faces1);
            elements.add(el1);

            // North/south faces
            var el2 = new JsonObject();
            el2.add("from", toJsonArray(0, 0, 1));
            el2.add("to", toJsonArray(16, 16, 15));
            var faces2 = new JsonObject();
            faces2.add("north", face("0 0 16 16", "#side", null));
            faces2.add("south", face("0 0 16 16", "#side", null));
            el2.add("faces", faces2);
            elements.add(el2);

            // West/east faces
            var el3 = new JsonObject();
            el3.add("from", toJsonArray(1, 0, 0));
            el3.add("to", toJsonArray(15, 16, 16));
            var faces3 = new JsonObject();
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

    public void createCrop(Block block, Property<Integer> ageProperty, int... ageToVisualStageMapping) {
        blockGen.createCropBlock(block, ageProperty, ageToVisualStageMapping);
    }

    public void createY(Block block, ResourceLocation modelLocation) {
        blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, modelLocation))
            .with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }

    public void createUPNWSE(Block block, ResourceLocation modelLocation) {
        blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, modelLocation))
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
        var array = new JsonArray();
        array.add(x);
        array.add(y);
        array.add(z);
        return array;
    }

    private JsonObject face(String uv, String texture, @Nullable String cullface) {
        var face = new JsonObject();
        String[] parts = uv.split(" ");
        var uvArr = new JsonArray();
        for (String p : parts) uvArr.add(Integer.parseInt(p));
        face.add("uv", uvArr);
        face.addProperty("texture", texture);
        if (cullface != null) face.addProperty("cullface", cullface);
        return face;
    }
}
