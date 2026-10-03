package net.justmili.leftforgotten.content.block.dev;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RmdlChest extends ChestBlock {

    public RmdlChest(Properties properties) {
        super(properties, () -> BlockEntityType.CHEST);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Block for visual changes only - a \"Remodel\" block").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }
}
