
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.block.dev.CommonBlock;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class Glass extends AbstractGlassBlock {
	public Glass() {
        super(Properties
                .of().mapColor(MapColor.NONE)
                .sound(SoundType.GLASS)
                .strength(0.3f)
                .noOcclusion()
                .isRedstoneConductor(CommonBlock::never)
                .isSuffocating(CommonBlock::never)
                .isViewBlocking(CommonBlock::never));
    }
}
