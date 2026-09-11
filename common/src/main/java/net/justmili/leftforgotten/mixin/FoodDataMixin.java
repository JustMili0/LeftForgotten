package net.justmili.leftforgotten.mixin;

import net.justmili.leftforgotten.registries.LevelRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void lf$noHungerOrRegen(Player player, CallbackInfo ci) {
        if (player.level().dimension() != LevelRegistry.ALPHA) return;
        // Prevent player from losing hunger or saturation as well as prevent natural regeneration
        ci.cancel();
    }
}