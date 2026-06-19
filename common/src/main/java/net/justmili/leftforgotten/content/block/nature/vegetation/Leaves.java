package net.justmili.leftforgotten.content.block.nature.vegetation;

import net.justmili.leftforgotten.content.block.CommonBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.material.MapColor;

public class Leaves extends LeavesBlock {
	public Leaves() {
		super(CommonBlock.leaves().mapColor(MapColor.COLOR_LIGHT_GREEN));
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(DISTANCE, DECAY_DISTANCE));
	}
}