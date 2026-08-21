package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.libs.v1.utils.common.BlockBehaviorUtil;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.material.MapColor;

public class Leaves extends LeavesBlock {
	public Leaves() {
		super(BlockBehaviorUtil.leaves().mapColor(MapColor.COLOR_LIGHT_GREEN));
	}
}