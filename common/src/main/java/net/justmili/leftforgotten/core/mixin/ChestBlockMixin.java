package net.justmili.leftforgotten.core.mixin;

import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.core.util.Versions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBlock.class)
public class ChestBlockMixin {

    @Unique
    private static final VoxelShape FULL_BLOCK = Block.box(0, 0, 0, 16, 16, 16);

    @Inject(method = "getShape", at = @At("HEAD"), cancellable = true)
    private void lf$tryReshapeChest(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context,
                                    CallbackInfoReturnable<VoxelShape> cir) {
        if (!(getter instanceof Level level)) return;
        if (!Versions.hadBlockyChests(level)) return;
        if (Config.chestRemodel.isNull() || !Config.chestRemodel.get()) return;
        if ((Object) this != Blocks.CHEST) return;

        cir.setReturnValue(FULL_BLOCK);
    }
}