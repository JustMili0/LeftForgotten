package net.justmili.leftforgotten.core.mixin.fabric.compat.nt;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(targets = "mod.adrenix.nostalgic.helper.candy.hud.HudHelper")
public class HudHelperMixin {

    @ModifyExpressionValue(method = "apply(Lnet/minecraft/client/gui/GuiGraphics;Lmod/adrenix/nostalgic/helper/candy/hud/HudElement;)V", at = @At(value = "INVOKE", target = "Lmod/adrenix/nostalgic/helper/candy/hud/HudHelper;isJumpMeterShown()Z"))
    private static boolean lf$skipExpBarShift(boolean original) {
        return Versions.hadOldHUD(ClientUtil.level()) || original;
    }
}