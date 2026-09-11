package net.justmili.leftforgotten.core.mixin.fabric.client;

import net.justmili.leftforgotten.client.CommonVersionOverlay;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class VersionOverlay {

    @Inject(at = @At("TAIL"), method = "render")
    public void lf$render(GuiGraphics graphics, float partialTick, CallbackInfo ci) {
        if (!ClientUtil.isDebugScreenOn() && Versions.hadVersionOverlay(ClientUtil.dimension())) CommonVersionOverlay.render(graphics);
    }
}