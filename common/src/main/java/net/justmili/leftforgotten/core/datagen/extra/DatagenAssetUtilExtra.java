package net.justmili.leftforgotten.core.datagen.extra;

import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;

import java.util.Optional;

public class DatagenAssetUtilExtra {
    // Specifically to recreate the model of the furnace with stone texture above and below
    public static void createOldFurnace(BlockModelGenerators blockGen, Block furnace, Block topBottomTexture) {
        var unlitMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(furnace))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFront(furnace))
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(topBottomTexture));
        var litMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(furnace))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFrontOn(furnace))
            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(topBottomTexture));

        var unlitModel = ModelTemplates.CUBE_ORIENTABLE.create(furnace, unlitMapping, blockGen.modelOutput);
        var litModel = ModelTemplates.CUBE_ORIENTABLE.create(
            TextureMapping.getBlockTexture(furnace, "_on"), litMapping, blockGen.modelOutput);

        blockGen.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(furnace)
                .with(BlockModelGenerators.createHorizontalFacingDispatch())
                .with(PropertyDispatch.property(BlockStateProperties.LIT)
                    .select(false, Variant.variant().with(VariantProperties.MODEL, unlitModel))
                    .select(true, Variant.variant().with(VariantProperties.MODEL, litModel)))
        );

        blockGen.delegateItemModel(furnace, unlitModel);
    }

    public static void createOldChest(BlockModelGenerators blockGen, Block block) {
        var singleMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFront(block));

        var leftMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFrontRight(block))
            .put(TextureSlot.SOUTH, ResourceUtil.mapTextureBackRight(block));

        var rightMapping = new TextureMapping()
            .put(TextureSlot.SIDE, ResourceUtil.mapTextureSide(block))
            .put(TextureSlot.TOP, ResourceUtil.mapTextureTop(block))
            .put(TextureSlot.FRONT, ResourceUtil.mapTextureFrontLeft(block))
            .put(TextureSlot.SOUTH, ResourceUtil.mapTextureBackLeft(block));

        var customBackOrientable = new ModelTemplate(Optional.of(ResourceUtil.asBlockPath("orientable")),
            Optional.empty(), TextureSlot.TOP, TextureSlot.FRONT, TextureSlot.SIDE, TextureSlot.SOUTH);

        var singleModel = ModelTemplates.CUBE_ORIENTABLE.create(block, singleMapping, blockGen.modelOutput);
        var leftModel = customBackOrientable.create(ResourceUtil.mapTextureLeft(block), leftMapping, blockGen.modelOutput);
        var rightModel = customBackOrientable.create(ResourceUtil.mapTextureRight(block), rightMapping, blockGen.modelOutput);

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
}