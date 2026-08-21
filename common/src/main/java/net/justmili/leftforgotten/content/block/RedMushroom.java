package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.libs.v1.utils.common.BlockBehaviorUtil;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class RedMushroom extends MushroomBlock {
    public RedMushroom() {
        super(TreeFeatures.HUGE_RED_MUSHROOM, Properties.of()
            .mapColor(MapColor.COLOR_RED)
            .noCollission()
            .randomTicks()
            .instabreak()
            .sound(SoundType.GRASS)
            .hasPostProcess(BlockBehaviorUtil::yes)
            .pushReaction(PushReaction.DESTROY));
    }
}