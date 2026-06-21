
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.block.dev.CommonBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.material.MapColor;

public class Wood6Sided extends RotatedPillarBlock {
    public Wood6Sided() {
        super(CommonBlock.log(MapColor.WOOD, MapColor.WOOD));
    }
}
