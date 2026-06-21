package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.block.dev.CommonBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.material.MapColor;

public class Leaves extends LeavesBlock {
	public Leaves() {
		super(CommonBlock.leaves().mapColor(MapColor.COLOR_LIGHT_GREEN));
	}
}