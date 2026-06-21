package net.justmili.leftforgotten.content.block.dev;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RemodelChest extends ChestBlock {
    public RemodelChest() {
        super(Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5F).sound(SoundType.WOOD).ignitedByLava(), () -> BlockEntityType.CHEST);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("DO NOT USE").withStyle(ChatFormatting.WHITE, ChatFormatting.UNDERLINE));
        tooltip.add(Component.literal("This block is dev-only.").withStyle(ChatFormatting.WHITE));
        tooltip.add(Component.literal("It is used for remodeling a vanilla block equivalent of itself.").withStyle(ChatFormatting.WHITE));
    }
}
