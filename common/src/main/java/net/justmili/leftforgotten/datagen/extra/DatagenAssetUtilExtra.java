package net.justmili.leftforgotten.datagen.extra;

import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class DatagenAssetUtilExtra {
    // Specifically to recreate the model of the furnace with stone texture above and below
    public static void createFurnaceCUSTOM(BlockModelGenerators blockGen, Block furnace, Block topBottomTexture) {
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
}