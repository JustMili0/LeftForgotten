package net.justmili.leftforgotten.mixin.client;

import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.util.Versions;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    @Inject(method = "canStartSprinting", at = @At("RETURN"), cancellable = true)
    private void lf$preventSprinting(CallbackInfoReturnable<Boolean> cir) {
        if (Versions.hadNoSprint(ClientUtil.dimension())) cir.setReturnValue(false);
    }
}