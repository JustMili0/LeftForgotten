package net.justmili.leftforgotten.core.mixin;

import net.justmili.leftforgotten.core.registries.LevelRegistry;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {

    @Inject(method = "causeFoodExhaustion", at = @At("HEAD"), cancellable = true)
    private void lf$noExhaustion(float exhaustion, CallbackInfo ci) {
        // Prevent adding exhaustion to player
        if (((Player) (Object) this).level().dimension() == LevelRegistry.ALPHA) ci.cancel();
    }
}