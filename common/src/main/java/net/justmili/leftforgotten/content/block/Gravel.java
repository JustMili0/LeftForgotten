
package net.justmili.leftforgotten.content.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class Gravel extends FallingBlock {
    public static final MapCodec<Gravel> CODEC = simpleCodec(Gravel::new);

    public Gravel() {
        this(Properties.of().mapColor(MapColor.STONE).sound(SoundType.GRAVEL).strength(0.6f).instrument(NoteBlockInstrument.SNARE));
    }

    private Gravel(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec() {
        return CODEC;
    }
}