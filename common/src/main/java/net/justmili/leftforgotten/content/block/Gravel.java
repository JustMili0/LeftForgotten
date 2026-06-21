
package net.justmili.leftforgotten.content.block;

import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class Gravel extends FallingBlock {
	public Gravel() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.GRAVEL).strength(0.6f).instrument(NoteBlockInstrument.SNARE));
	}
}
