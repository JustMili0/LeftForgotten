
package net.justmili.leftforgotten.content.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class DiamondOre extends Block {
	public DiamondOre() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3f).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops());
	}
}
