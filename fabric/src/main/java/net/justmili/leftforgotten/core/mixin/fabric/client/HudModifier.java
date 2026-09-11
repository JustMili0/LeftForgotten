package net.justmili.leftforgotten.core.mixin.fabric.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.core.util.Versions;
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

import static net.justmili.leftforgotten.client.CommonHudModifier.Common.*;
import static net.justmili.leftforgotten.client.CommonHudModifier.Fabric.*;

@Mixin(value = Gui.class, priority = 2500)
public abstract class HudModifier {

    @Shadow
    protected abstract int getVehicleMaxHearts(LivingEntity vehicle);

    // Draw identifier for renderPlayerHealth's redirectBlit profiler section
    @Unique
    private Stack<String> lf$currentProfiler = new Stack<>();

    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void lf$logProfilePushes(ProfilerFiller filler, String name, Operation<Void> original) {
        lf$currentProfiler.push(name);
        original.call(filler, name);
    }

    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V"))
    private void lf$logProfilePopPushes(ProfilerFiller filler, String name, Operation<Void> original) {
        lf$currentProfiler.pop();
        lf$currentProfiler.push(name);
        original.call(filler, name);
    }

    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V"))
    private void lf$logProfilePops(ProfilerFiller filler, Operation<Void> original) {
        lf$currentProfiler.pop();
        original.call(filler);
    }

    // Player HP - move down, account for AbstractHorse jump bar when saddled
    @ModifyVariable(method = "renderHearts", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int lf$moveHeartsDown(int y) {
        if (!Versions.hadOldHUD(ClientUtil.dimension())) return y;

        return y + playerHpH - yOffset();
    }

    // Food disable
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/Gui;getVehicleMaxHearts(Lnet/minecraft/world/entity/LivingEntity;)I"))
    private int lf$disableFoodBar(Gui gui, LivingEntity vehicle, Operation<Integer> original) {
        if (Versions.hadOldHUD(ClientUtil.dimension())) return -1;

        return this.getVehicleMaxHearts(vehicle);
    }

    // Armor - flip sprites, move right and down, account for AbstractHorse jump bar when saddled
    @WrapOperation(method = "renderArmor(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/entity/player/Player;IIII)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V"))
    private static void lf$redirectArmorBlit(GuiGraphics graphics, ResourceLocation sprite, int x, int y, int width, int height, Operation<Void> original) {
        if (!Versions.hadOldHUD(ClientUtil.dimension())) {
            original.call(graphics, sprite, x, y, width, height);
            return;
        }

        var atlasSprite = ClientUtil.client().getGuiSprites().getSprite(sprite);
        int x1 = mirrorX(x) + armorW;
        int y1 = y + armorH - yOffset() + extraHealthRowsOffset();

        renderFlippedSprite(graphics, atlasSprite, x1, y1, width, height);
    }

    // Air Level - move left and down, account for AbstractHorse jump bar when saddled
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V"))
    private void lf$redirectAirBlit(GuiGraphics graphics, ResourceLocation sprite, int x, int y, int width, int height, Operation<Void> original) {
        if (!Versions.hadOldHUD(ClientUtil.dimension())) {
            original.call(graphics, sprite, x, y, width, height);
            return;
        }

        if (this.lf$currentProfiler.peek().equals("air")) { // Air level move left and down
            // Flip the way it goes
            int barEnd = ClientUtil.width() / 2 + 51;
            int mirroredX = 2 * barEnd - 9 - x;

            graphics.blitSprite(sprite, mirroredX - airLvlW, y - airLvlH + yOffset() - extraHealthRowsOffset(), width, height);
        } else {
            original.call(graphics, sprite, x, y, width, height);
        }
    }

    // EXP bar disable
    @Inject(at = @At("HEAD"), method = "renderExperienceBar", cancellable = true)
    private void lf$renderExperienceBar(CallbackInfo ci) {
        if (Versions.hadOldHUD(ClientUtil.dimension())) ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "renderExperienceLevel", cancellable = true)
    private void lf$renderExperienceLevel(CallbackInfo ci) {
        if (Versions.hadOldHUD(ClientUtil.dimension())) ci.cancel();
    }

    // Mount HP move, account for AbstractHorse jump bar when saddled and Armor
    @ModifyVariable(method = "renderVehicleHealth", at = @At("STORE"), ordinal = 2)
    private int lf$moveMountHealthY(int y) {
        if (!Versions.hadOldHUD(ClientUtil.dimension()) || ClientUtil.notSurvivalOrHideGui()) return y;

        if (ClientUtil.player().getArmorValue() == 0) return mountHpOffset() + mountHpH_na + mountHpH;
        return mountHpOffset();
    }

    @Inject(at = @At("HEAD"), method = "renderVehicleHealth", cancellable = true)
    private void lf$mountHealthCreativeCancel(GuiGraphics graphics, CallbackInfo ci) {
        if (Versions.hadOldHUD(ClientUtil.dimension()) && ClientUtil.notSurvivalOrHideGui()) ci.cancel();
    }
}