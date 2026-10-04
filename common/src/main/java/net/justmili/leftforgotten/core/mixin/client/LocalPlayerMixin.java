package net.justmili.leftforgotten.core.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    @ModifyReturnValue(method = "canStartSprinting", at = @At("RETURN"))
    private boolean lf$preventSprinting(boolean original) {
        return (!Versions.hadNoSprint(ClientUtil.level()) || ClientUtil.isNotSurvival()) && original;
    }
}
