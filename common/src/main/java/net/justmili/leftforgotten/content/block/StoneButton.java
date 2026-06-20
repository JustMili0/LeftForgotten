
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.block.dev.CommonBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class StoneButton extends ButtonBlock {
    public StoneButton() {
        super(CommonBlock.button(), BlockSetType.STONE, 20, false);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }
}