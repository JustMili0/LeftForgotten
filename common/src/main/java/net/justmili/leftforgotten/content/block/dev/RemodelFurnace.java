package net.justmili.leftforgotten.content.block.dev;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RemodelFurnace extends FurnaceBlock {
    public RemodelFurnace() {
        super(Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F).lightLevel(CommonBlock.litBlockEmission(13)));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("DO NOT USE").withStyle(ChatFormatting.WHITE, ChatFormatting.UNDERLINE));
        tooltip.add(Component.literal("This block is dev-only.").withStyle(ChatFormatting.WHITE));
        tooltip.add(Component.literal("It is used for remodeling a vanilla block equivalent of itself.").withStyle(ChatFormatting.WHITE));
    }
}
