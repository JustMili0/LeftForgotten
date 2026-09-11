package net.justmili.leftforgotten.core.mixinsquared.forge;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;

public class CancelPerlinNoiseOverwrite implements MixinCanceller {
    @Override
    public boolean shouldCancel(List<String> list, String mixinClassName) {
        return mixinClassName.equals("com.ishland.c2me.opts.math.mixin.MixinOctavePerlinNoiseSampler") ||
            mixinClassName.equals("com.ishland.c2me.opts.math.mixin.MixinPerlinNoiseSampler");
    }
}