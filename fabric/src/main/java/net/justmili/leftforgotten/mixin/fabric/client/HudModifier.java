package net.justmili.leftforgotten.mixin.fabric.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Stack;

import static net.justmili.leftforgotten.client.CommonHudModifier.Common.extraHealthRowsOffset;
import static net.justmili.leftforgotten.client.CommonHudModifier.Common.renderFlippedBlit;
import static net.justmili.leftforgotten.client.CommonHudModifier.Fabric.*;

@Mixin(value = Gui.class, priority = 2500)
public abstract class HudModifier {

    @Shadow
    protected abstract int getVehicleMaxHearts(LivingEntity vehicle);

    // Draw identifier for renderPlayerHealth's redirectBlit profiler section
    @Unique
    private Stack<String> currentProfiler = new Stack<>();
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void logProfilePushes(ProfilerFiller filler, String name, Operation<Void> original) {
        currentProfiler.push(name);
        original.call(filler, name);
    }
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V"))
    private void logProfilePopPushes(ProfilerFiller filler, String name, Operation<Void> original) {
        currentProfiler.pop();
        currentProfiler.push(name);
        original.call(filler, name);
    }
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V"))
    private void logProfilePops(ProfilerFiller filler, Operation<Void> original) {
        currentProfiler.pop();
        original.call(filler);
    }

    // Player HP - move down, account for AbstractHorse jump bar when saddled
    @ModifyVariable(method = "renderHearts", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int moveHeartsDown(int y) {
        if (!CommonClient.inAlpha()) return y;

        return y+playerHpH-yOffset();
    }

    // Food disable
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/Gui;getVehicleMaxHearts(Lnet/minecraft/world/entity/LivingEntity;)I"))
    private int disableFoodBar(Gui gui, LivingEntity vehicle, Operation<Integer> original) {
        if (CommonClient.inAlpha()) return -1;

        return this.getVehicleMaxHearts(vehicle);
    }

    // Armor and Air Level, flip armor sprites, account for AbstractHorse jump bar when saddled
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"))
    private void redirectBlit(GuiGraphics graphics, ResourceLocation atlasLocation, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight, Operation<Void> original) {
        if (!CommonClient.inAlpha()) {
            original.call(graphics, atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight);
            return;
        }

        if (this.currentProfiler.peek().equals("armor")) { // Armor move right and down
            int barStart = ClientUtil.width() / 2-91;
            int mirroredX = 2 * barStart+72-x;
            int x1 = mirroredX+armorW;
            int y1 = y+armorH-yOffset()+extraHealthRowsOffset();

            renderFlippedBlit(graphics, atlasLocation, x1, y1, uWidth, vHeight, uOffset, vOffset);

        } else if (this.currentProfiler.peek().equals("air")) { // Air level move left and down
            int barEnd = ClientUtil.width() / 2+51;
            int mirroredX = 2 * barEnd-9-x;

            graphics.blit(atlasLocation, mirroredX-airLvlW, y-airLvlH+yOffset()-extraHealthRowsOffset(), uOffset, vOffset, uWidth, vHeight);
        } else {
            original.call(graphics, atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight);
        }
    }

    // EXP bar disable
    @Inject(at = @At("HEAD"), method = "renderExperienceBar", cancellable = true)
    private void renderExperienceBar(CallbackInfo ci) {
        if (CommonClient.inAlpha()) ci.cancel();
    }
    // EXP level renders with EXP bar on 1.20.1

    // Mount HP move, account for AbstractHorse jump bar when saddled and Armor
    @ModifyVariable(method = "renderVehicleHealth", at = @At("STORE"), ordinal = 2)
    private int moveMountHealthY(int y) {
        if (CommonClient.notInAlpha() || ClientUtil.notSurvivalOrHideGui()) return y;

        if (ClientUtil.player().getArmorValue() == 0) return mountHpOffset() + mountHpH_na + mountHpH;
        return mountHpOffset();
    }

    @Inject(at = @At("HEAD"), method = "renderVehicleHealth", cancellable = true)
    private void mountHealthCreativeCancel(GuiGraphics graphics, CallbackInfo ci) {
        if (CommonClient.inAlpha() && ClientUtil.notSurvivalOrHideGui()) ci.cancel();
    }
}