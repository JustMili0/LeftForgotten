package net.justmili.leftforgotten.content.mechanics.gameplay;

import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.common.Maths;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class PreHungerHealing {
    private static final Map<Item, Float> HEALTH_OVERRIDES = Map.of(
        Items.PORKCHOP, 3f, Items.COOKED_PORKCHOP, 8f,
        Items.BEEF, 3f, Items.COOKED_BEEF, 8f, // Beef only added because it's supposed to be equal to the porkchop
        Items.BREAD, 5f, Items.MUSHROOM_STEM, 10f,
        Items.APPLE, 4f, Items.GOLDEN_APPLE, 20f
    );

    public static @Nullable InteractionResultHolder<ItemStack> handle(Level level, Player player, InteractionHand hand) {
        if (!Versions.hadNoHunger(level)) return null;
        var stack = player.getItemInHand(hand);
        var food = stack.getItem().getFoodProperties();
        if (food == null) return null;

        if (!canConsume(player, food)) return InteractionResultHolder.fail(stack);
        if (!level.isClientSide()) consume(level, player, hand, stack, food);

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }

    private static void consume(Level level, Player player, InteractionHand hand, ItemStack stack, FoodProperties food) {
        float heal = getHealValue(stack.getItem(), food);
        var result = stack.finishUsingItem(level, player);
        if (result != stack) player.setItemInHand(hand, result);
        player.heal(heal);
    }

    private static boolean canConsume(Player player, FoodProperties food) {
        return player.getHealth() < player.getMaxHealth() || food.canAlwaysEat() || player.getAbilities().invulnerable;
    }

    private static float getHealValue(Item item, FoodProperties food) {
        var override = HEALTH_OVERRIDES.get(item);
        if (override != null) return override;

        int nutrition = food.getNutrition();
        float saturation = nutrition * food.getSaturationModifier() * 2f;
        float heal = nutrition + saturation * 0.1f;
        return Maths.clamp(Maths.roundHalfUp(heal, 0), 0, 20);
    }
}