
package net.justmili.leftforgotten.content.block;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class BrickSlab extends SlabBlock {
	public BrickSlab() {
        super(Properties.of().mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F));
	}
}
