package net.justmili.leftforgotten.mixin.client;

import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.justmili.leftforgotten.registries.SoundRegistry;
import net.justmili.leftforgotten.util.Versions;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundEngine.class)
public class SoundEngineMixin {

    @Unique
    private static final ResourceLocation
        STOMACH_GROWL = ResourceUtil.asPath("subtle_effects:entity.player.stomach_growl"),
        CHEST_OPEN = SoundEvents.CHEST_OPEN.getLocation(),
        CHEST_CLOSE = SoundEvents.CHEST_CLOSE.getLocation(),
        PLAYER_HURT = SoundEvents.PLAYER_HURT.getLocation(),
        HURT_FREEZE = SoundEvents.PLAYER_HURT_FREEZE.getLocation(),
        HURT_FIRE = SoundEvents.PLAYER_HURT_ON_FIRE.getLocation(),
        HURT_DROWN = SoundEvents.PLAYER_HURT_DROWN.getLocation(),
        HURT_BERRY = SoundEvents.PLAYER_HURT_SWEET_BERRY_BUSH.getLocation();

    @Inject(method = "play", at = @At("HEAD"), cancellable = true)
    private void lf$onPlay(SoundInstance sound, CallbackInfo ci) {
        if (sound == null || Versions.isOldVersion(ClientUtil.dimension())) return;
        var soundPath = sound.getLocation();

        // Cancel stomach growl and chest opening/closing sounds as they didn't exist
        if (STOMACH_GROWL.equals(soundPath) || CHEST_OPEN.equals(soundPath) || CHEST_CLOSE.equals(soundPath)) {
            ci.cancel();
            return;
        }

        // Replace vanilla hurt with alpha hurt
        if (PLAYER_HURT.equals(soundPath)) {
            ci.cancel();
            ((SoundEngine)(Object)this).play(SimpleSoundInstance.forUI(SoundRegistry.HURT.get(), 1.0f));
            return;
        }
        // Play alpha hurt on top of other hurt sounds
        if (HURT_FREEZE.equals(soundPath)
            || HURT_FIRE.equals(soundPath)
            || HURT_DROWN.equals(soundPath)
            || HURT_BERRY.equals(soundPath)) {
            ((SoundEngine)(Object)this).play(SimpleSoundInstance.forUI(SoundRegistry.HURT.get(), 1.0f));
            // Don't cancel original sound
        }
    }
}
