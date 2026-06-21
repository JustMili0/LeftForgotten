
package net.justmili.leftforgotten.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class Stone extends Block {
	public Stone() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(1.5f, 6f).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops());
	}
}
