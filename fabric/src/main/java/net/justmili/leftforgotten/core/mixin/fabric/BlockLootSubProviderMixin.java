package net.justmili.leftforgotten.core.mixin.fabric;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.justmili.leftforgotten.libs.v1.utils.common.datagen.extensions.KnownBlocksLootProvider;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Iterator;

@Mixin(BlockLootSubProvider.class)
public abstract class BlockLootSubProviderMixin {

    @ModifyExpressionValue(method = "generate(Ljava/util/function/BiConsumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/DefaultedRegistry;iterator()Ljava/util/Iterator;"))
    private Iterator<Block> lf$useKnownBlocks(Iterator<Block> original) {
        if (this instanceof KnownBlocksLootProvider provider) {
            return provider.getKnownBlocks().iterator();
        }

        return original;
    }
}
