package net.justmili.leftforgotten.content.block;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class IronDoor extends DoorBlock {
    public IronDoor() {
        super(Properties.of()
            .mapColor(MapColor.METAL)
            .requiresCorrectToolForDrops()
            .strength(5.0f)
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY), BlockSetType.IRON);
    }
}
