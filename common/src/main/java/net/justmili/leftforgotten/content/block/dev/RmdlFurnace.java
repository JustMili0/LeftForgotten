package net.justmili.leftforgotten.content.block.dev;

import net.justmili.leftforgotten.libs.v1.utils.common.BlockBehaviorUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FurnaceBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RmdlFurnace extends FurnaceBlock {

    public RmdlFurnace(Properties properties) {
        super(properties.lightLevel(BlockBehaviorUtil.litBlockEmission(13)));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Block for visual changes only - a \"Remodel\" block").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }
}
