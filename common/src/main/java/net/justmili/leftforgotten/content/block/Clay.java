package net.justmili.leftforgotten.content.block;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class Clay extends Block {
    public Clay() {
        super(Properties.of().mapColor(MapColor.CLAY).sound(SoundType.GRAVEL).strength(0.6f, 0.6f).instrument(NoteBlockInstrument.FLUTE));
    }
}