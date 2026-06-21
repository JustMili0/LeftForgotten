
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.block.dev.CommonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class WoodenTrapdoor extends TrapDoorBlock {
	public WoodenTrapdoor() {
		super(Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(3f).noOcclusion().isRedstoneConductor(CommonBlock::never).instrument(NoteBlockInstrument.BASS).ignitedByLava(), BlockSetType.OAK);
	}
}
