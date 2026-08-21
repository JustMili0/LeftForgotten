package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.libs.v1.utils.common.BlockBehaviorUtil;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class BrittleBedrock extends Block {
    public BrittleBedrock() {
        super(Properties.of()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(Block.INDESTRUCTIBLE, 6.5f)
            .noLootTable().isValidSpawn(BlockBehaviorUtil::no));
    }
}
