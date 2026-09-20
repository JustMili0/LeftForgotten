package net.justmili.leftforgotten.client;

import net.justmili.leftforgotten.core.util.client.Remodels;
import net.justmili.leftforgotten.libs.v1.utils.client.RenderingUtil;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ClassicBlockModels implements BakedModel {
    protected final BakedModel wrapped;

    public ClassicBlockModels(BakedModel wrapped) {
        this.wrapped = wrapped;
    }

    protected @Nullable BakedModel getBakedModel(BlockState state) {
        if (state == null) return this.wrapped;

        var block = state.getBlock();
        var remodel = Remodels.of(block);
        if (remodel == block) return this.wrapped;

        // Chest here only exists purely for EBE purposes really
        return state.is(Blocks.CHEST)? null : RenderingUtil.getBlockModel(remodel.withPropertiesOf(state));
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction direction, RandomSource random) {
        var replacedModel = this.getBakedModel(state);

        if (replacedModel != null) {
            return replacedModel.getQuads(state, direction, random);
        }

        return List.of();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return this.wrapped.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.wrapped.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return this.wrapped.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return this.wrapped.isCustomRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return this.wrapped.getParticleIcon();
    }

    @Override
    public ItemTransforms getTransforms() {
        return this.wrapped.getTransforms();
    }

    @Override
    public ItemOverrides getOverrides() {
        return this.wrapped.getOverrides();
    }
}

