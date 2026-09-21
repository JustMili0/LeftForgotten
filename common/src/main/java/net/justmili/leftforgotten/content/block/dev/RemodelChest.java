package net.justmili.leftforgotten.content.block.dev;

import net.justmili.leftforgotten.libs.v1.utils.common.BlockBehaviorUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.PushReaction;

import java.util.List;

public class RemodelChest extends ChestBlock {
    public RemodelChest() {
        super(Properties.of().strength(Block.INDESTRUCTIBLE).noLootTable().noOcclusion().isValidSpawn(BlockBehaviorUtil::no).noTerrainParticles().pushReaction(PushReaction.BLOCK), () -> BlockEntityType.CHEST);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.literal("DO NOT USE").withStyle(ChatFormatting.WHITE, ChatFormatting.UNDERLINE));
        tooltip.add(Component.literal("This block is dev-only.").withStyle(ChatFormatting.WHITE));
        tooltip.add(Component.literal("It is used for remodeling a vanilla block equivalent of itself.").withStyle(ChatFormatting.WHITE));
    }
}