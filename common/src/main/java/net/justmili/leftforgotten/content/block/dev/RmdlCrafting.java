package net.justmili.leftforgotten.content.block.dev;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CraftingTableBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RmdlCrafting extends CraftingTableBlock {

    public RmdlCrafting(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Block for visual changes only - a \"Remodel\" block").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }
}