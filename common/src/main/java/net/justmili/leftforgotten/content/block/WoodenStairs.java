
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class WoodenStairs extends StairBlock {
	public WoodenStairs() {
		super(BlockRegistry.WOODEN_PLANKS.get().defaultBlockState(), Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(2f, 3f).instrument(NoteBlockInstrument.BASS).ignitedByLava());
	}
}
