package net.justmili.leftforgotten.core.mixin.compat.nt;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(targets = "mod.adrenix.nostalgic.listener.client.GuiListener")
public class GuiListenerMixin {

    @Definition(id = "OLD_VERSION_OVERLAY", field = "Lmod/adrenix/nostalgic/tweak/config/CandyTweak;OLD_VERSION_OVERLAY:Lmod/adrenix/nostalgic/tweak/factory/TweakFlag;")
    @Definition(id = "get", method = "Lmod/adrenix/nostalgic/tweak/factory/TweakFlag;get()Ljava/lang/Object;")
    @Expression("OLD_VERSION_OVERLAY.get()")
    @ModifyExpressionValue(method = "renderTextOverlay(Lnet/minecraft/client/gui/GuiGraphics;Z)V", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private static Object lf$hideVersionOverlay(Object original) {
        if (Versions.hadVersionOverlay(ClientUtil.level())) return false;
        return original;
    }
}
