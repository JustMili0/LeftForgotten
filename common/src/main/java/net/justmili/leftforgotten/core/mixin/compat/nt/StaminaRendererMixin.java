package net.justmili.leftforgotten.core.mixin.compat.nt;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(targets = "mod.adrenix.nostalgic.helper.gameplay.stamina.StaminaRenderer")
public class StaminaRendererMixin {

    @ModifyReturnValue(method = "isVisible", at = @At("RETURN"))
    private static boolean lf$hideStamina(boolean original) {
        return Versions.hadNoSprint(ClientUtil.level())? false : original;
    }
}