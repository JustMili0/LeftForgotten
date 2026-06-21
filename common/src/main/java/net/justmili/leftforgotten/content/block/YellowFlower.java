
package net.justmili.leftforgotten.content.block;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class YellowFlower extends FlowerBlock {
    public YellowFlower() {
        super(MobEffects.SATURATION, 7,
                Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.GRASS).instabreak()
                        .noCollission().offsetType(OffsetType.XZ).pushReaction(PushReaction.DESTROY));
    }
}
