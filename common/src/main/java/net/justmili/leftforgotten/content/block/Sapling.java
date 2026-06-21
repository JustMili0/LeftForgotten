package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.world.block.grower.TreeGrower;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.material.MapColor;

public class Sapling extends SaplingBlock {
    public Sapling() {
        super(new TreeGrower(), Properties.copy(Blocks.OAK_SAPLING).mapColor(MapColor.COLOR_LIGHT_GREEN));
    }
}
