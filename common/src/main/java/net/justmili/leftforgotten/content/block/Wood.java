
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.block.dev.CommonBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.material.MapColor;

public class Wood extends RotatedPillarBlock {
	public Wood() {
		super(CommonBlock.log(MapColor.WOOD, MapColor.WOOD));
	}
}