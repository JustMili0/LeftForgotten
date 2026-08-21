package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.libs.v1.utils.common.BlockBehaviorUtil;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class BrownMushroom extends MushroomBlock {
    public BrownMushroom() {
        super(Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS)
                .hasPostProcess(BlockBehaviorUtil::no)
                .pushReaction(PushReaction.DESTROY),
            TreeFeatures.HUGE_BROWN_MUSHROOM
        );
    }
}