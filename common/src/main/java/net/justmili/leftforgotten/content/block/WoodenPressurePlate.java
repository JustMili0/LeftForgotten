
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.block.dev.CommonBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class WoodenPressurePlate extends PressurePlateBlock {
	public WoodenPressurePlate() {
		super(Sensitivity.EVERYTHING, Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.5f).noOcclusion().isRedstoneConductor(CommonBlock::never).forceSolidOn().instrument(NoteBlockInstrument.BASS).ignitedByLava(), BlockSetType.OAK);
	}
}
