package net.justmili.leftforgotten.core.mixin.neoforge.compat.nt;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(targets = "mod.adrenix.nostalgic.neoforge.event.ClientEventHandler")
public class ClientEventHandlerMixin {

    @Definition(id = "HIDE_EXPERIENCE_BAR", field = "Lmod/adrenix/nostalgic/tweak/config/CandyTweak;HIDE_EXPERIENCE_BAR:Lmod/adrenix/nostalgic/tweak/factory/TweakFlag;")
    @Definition(id = "get", method = "Lmod/adrenix/nostalgic/tweak/factory/TweakFlag;get()Ljava/lang/Object;")
    @Expression("HIDE_EXPERIENCE_BAR.get()")
    @ModifyExpressionValue(method = "setupHighestGuiOverlayPre", at = @At("MIXINEXTRAS:EXPRESSION"))
    private static Object lf$noExpBarShift(Object original) {
        return Versions.hadOldHUD(ClientUtil.level())? false : original;
    }
}