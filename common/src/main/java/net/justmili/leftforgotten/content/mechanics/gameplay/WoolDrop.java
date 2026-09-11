
package net.justmili.leftforgotten.content.mechanics.gameplay;

import dev.architectury.event.EventResult;
import net.justmili.leftforgotten.util.Versions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class WoolDrop {
    public static EventResult onEntityHurt(LivingEntity entity, DamageSource source, float value) {
        if (!(entity instanceof Sheep sheep) || !Versions.upToAlpha(entity.level().dimension())) return EventResult.pass();
        var level = sheep.level();
        if (level.isClientSide()) return EventResult.pass();
        if (sheep.isSheared()) return EventResult.pass();

        var woolItem = switch (sheep.getColor()) {
            case WHITE -> Items.WHITE_WOOL;
            case ORANGE -> Items.ORANGE_WOOL;
            case MAGENTA -> Items.MAGENTA_WOOL;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_WOOL;
            case YELLOW -> Items.YELLOW_WOOL;
            case LIME -> Items.LIME_WOOL;
            case PINK -> Items.PINK_WOOL;
            case GRAY -> Items.GRAY_WOOL;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_WOOL;
            case CYAN -> Items.CYAN_WOOL;
            case PURPLE -> Items.PURPLE_WOOL;
            case BLUE -> Items.BLUE_WOOL;
            case BROWN -> Items.BROWN_WOOL;
            case GREEN -> Items.GREEN_WOOL;
            case RED -> Items.RED_WOOL;
            case BLACK -> Items.BLACK_WOOL;
        };

        int count = level.getRandom().nextInt(3) + 1;
        var stack = new ItemStack(woolItem, count);
        var drop = new ItemEntity(level, sheep.getX(), sheep.getY() + 0.4, sheep.getZ(), stack);

        level.addFreshEntity(drop);
        sheep.setSheared(true);

        return EventResult.pass();
    }
}