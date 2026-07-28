package net.justmili.leftforgotten.mixin.fabric.client;

import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.client.CommonVersionOverlay;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.justmili.leftforgotten.libs.v1.utils.ClientUtil.isDebugScreenOn;
import static net.justmili.leftforgotten.libs.v1.utils.ClientUtil.client;

@Mixin(Gui.class)
public abstract class VersionOverlay {

    @Inject(at = @At("TAIL"), method = "render")
    public void render(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (isDebugScreenOn()) return;
        if (!CommonClient.inAlpha()) return;

        CommonVersionOverlay.render(client, graphics);
    }
}