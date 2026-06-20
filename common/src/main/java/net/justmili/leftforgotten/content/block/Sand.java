
package net.justmili.leftforgotten.content.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class Sand extends FallingBlock {
    public static final MapCodec<Sand> CODEC = simpleCodec(Sand::new);

    public Sand() {
        this(Properties.of().mapColor(MapColor.SAND).sound(SoundType.SAND).strength(0.5f).instrument(NoteBlockInstrument.SNARE));
    }

    private Sand(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec() {
        return CODEC;
    }
}