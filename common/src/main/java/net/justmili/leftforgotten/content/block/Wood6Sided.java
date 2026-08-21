
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.libs.v1.utils.common.BlockBehaviorUtil;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.material.MapColor;

public class Wood6Sided extends RotatedPillarBlock {
    public Wood6Sided() {
        super(BlockBehaviorUtil.log(MapColor.WOOD, MapColor.WOOD));
    }
}
