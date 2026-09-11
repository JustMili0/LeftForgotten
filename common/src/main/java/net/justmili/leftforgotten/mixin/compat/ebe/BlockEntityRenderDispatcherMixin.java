package net.justmili.leftforgotten.mixin.compat.ebe;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.sugar.Local;
import net.justmili.leftforgotten.registries.LevelRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(value = BlockEntityRenderDispatcher.class, priority = 1500)
public abstract class BlockEntityRenderDispatcherMixin {

    @Dynamic
    @TargetHandler(mixin = "foundationgames.enhancedblockentities.mixin.BlockEntityRenderDispatcherMixin", name = "enhanced_bes$renderOverrides", prefix = "handler")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), require = 0, cancellable = true)
    private static void lf$avoidRenderOverrideIfChest(CallbackInfo ci, @Local(argsOnly = true) BlockEntity blockEntity) {
        if (blockEntity.getType() == BlockEntityType.CHEST && blockEntity.hasLevel() && lf$blockEntityInDimension(blockEntity, LevelRegistry.ALPHA)) ci.cancel();
    }

    @Unique
    private static boolean lf$blockEntityInDimension(BlockEntity blockEntity, ResourceKey<Level> dimension) {
        var level = blockEntity.getLevel();
        if (level == null) return false;
        return level.dimension().equals(dimension);
    }
}