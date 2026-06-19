package net.justmili.leftforgotten.mixin.neoforge.compat;

import net.minecraft.world.level.LevelHeightAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(targets = "dev.theagameplayer.mtchunkgeneration.world.level.levelgen.MTLayer", remap = false)
public abstract class MTLayerMixin {
    @Redirect(method = "lambda$doCreateBiomes$2",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelHeightAccessor;getMaxSection()I"),
        require = 0)
    private int lf$fixExclusiveMaxSection(LevelHeightAccessor heightAccessor) {
        // MTChunkGeneration adds one to an already-exclusive bound.
        return heightAccessor.getMaxSection() - 1;
    }
}