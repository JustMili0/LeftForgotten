
package net.justmili.leftforgotten.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.Collections;
import java.util.List;

public class RedFlower extends FlowerBlock {
    public RedFlower() {
        super(MobEffects.NIGHT_VISION, 5,
            Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.GRASS).instabreak()
                .noCollission().offsetType(OffsetType.XZ).pushReaction(PushReaction.DESTROY));
    }
}