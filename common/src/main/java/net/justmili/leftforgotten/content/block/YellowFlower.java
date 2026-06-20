
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

public class YellowFlower extends FlowerBlock {
    public YellowFlower() {
        super(MobEffects.SATURATION, 100,
            Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.GRASS).instabreak()
                .noCollission().offsetType(OffsetType.XZ).pushReaction(PushReaction.DESTROY));
    }

    //@Override
    //public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
    //    return 100;
    //}

    //@Override
    //public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
    //    return 60;
    //}

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this));
    }

    @Override
    protected boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
        return groundState.is(BlockTags.DIRT);
    }

    @Override
    protected boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState groundState = worldIn.getBlockState(blockpos);
        return this.mayPlaceOn(groundState, worldIn, blockpos);
    }
}