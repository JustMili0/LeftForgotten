package net.justmili.leftforgotten.content.block;

import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class GlassPane extends IronBarsBlock {
    public GlassPane() {
        super(Properties.of().instrument(NoteBlockInstrument.HAT).strength(0.3F).sound(SoundType.GLASS).noOcclusion());
    }
}
