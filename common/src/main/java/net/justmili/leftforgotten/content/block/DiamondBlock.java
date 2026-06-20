package net.justmili.leftforgotten.content.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class DiamondBlock extends Block {
    public DiamondBlock() {
        super(Properties.of().mapColor(MapColor.DIAMOND).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL));
    }
}
