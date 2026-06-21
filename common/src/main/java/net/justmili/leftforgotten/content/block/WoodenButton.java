
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.block.dev.CommonBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class WoodenButton extends ButtonBlock {
    public WoodenButton() {
        super(BlockSetType.OAK, 30, CommonBlock.button());
    }
}