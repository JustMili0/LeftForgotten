
package net.justmili.leftforgotten.content.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class DiamondOre extends DropExperienceBlock {
	public DiamondOre() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(3f).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops(), UniformInt.of(3, 7));
	}
}
