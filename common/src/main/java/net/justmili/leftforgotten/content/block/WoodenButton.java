
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.libs.v1.utils.common.BlockBehaviorUtil;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class WoodenButton extends ButtonBlock {
    public WoodenButton() {
        super(BlockSetType.OAK, 30, BlockBehaviorUtil.button());
    }
}