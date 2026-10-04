package net.justmili.leftforgotten.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.justmili.leftforgotten.config.Config;
import net.justmili.leftforgotten.core.util.Versions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChestBlock.class)
public class ChestBlockMixin {

    @Unique
    private static final VoxelShape FULL_BLOCK = Block.box(0, 0, 0, 16, 16, 16);

    @ModifyReturnValue(method = "getShape", at = @At("RETURN"))
    private VoxelShape lf$tryReshapeChest(VoxelShape original, BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        if (!(getter instanceof Level level)) return original;
        if (!Config.chestRemodel.get() || !Versions.hadBlockyChests(level)) return original;
        return FULL_BLOCK;
    }
}