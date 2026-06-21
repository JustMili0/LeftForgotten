
package net.justmili.leftforgotten.content.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class WoodenPlanks extends Block {
	public WoodenPlanks() {
		super(Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(2f, 3f).instrument(NoteBlockInstrument.BASS).ignitedByLava());
	}
}
