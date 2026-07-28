package net.justmili.leftforgotten.mixin.neoforge.client;

import net.justmili.leftforgotten.libs.v1.utils.ClientUtil;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "squeek/appleskin/client/HUDOverlayHandler$Overlay", remap = false)
public class UnrenderAppleSkinHud {

    @Inject(method = "render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V",
        at = @At("HEAD"), cancellable = true, require = 0)
    private void unrenderAppleSkinHud(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (!ClientUtil.inDimension(LFResources.ALPHA_MINECRAFT)) return;
        ci.cancel();
    }
}