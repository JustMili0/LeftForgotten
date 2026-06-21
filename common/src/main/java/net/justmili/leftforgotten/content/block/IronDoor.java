package net.justmili.leftforgotten.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class IronDoor extends DoorBlock {
    public IronDoor() {
        super(BlockSetType.IRON, Properties.of()
            .mapColor(MapColor.METAL)
            .requiresCorrectToolForDrops()
            .strength(5.0f)
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY));
    }
}