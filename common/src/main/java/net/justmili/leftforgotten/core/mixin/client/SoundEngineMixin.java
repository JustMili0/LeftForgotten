package net.justmili.leftforgotten.core.mixin.client;

import net.justmili.leftforgotten.core.registries.SoundRegistry;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.core.util.client.Sounds;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundEngine.class)
public class SoundEngineMixin {

    @Inject(method = "play", at = @At("HEAD"), cancellable = true)
    private void lf$tryCancelOrReplaceSound(SoundInstance sound, CallbackInfo ci) {
        if (sound == null || !Versions.isOldVersion(ClientUtil.level())) return;
        var engine = (SoundEngine) (Object) this;
        var soundPath = sound.getLocation();

        // Cancel stomach growl and chest opening/closing sounds as they didn't exist
        if (Sounds.STOMACH_GROWL.equals(soundPath) || Sounds.CHEST_OPEN.equals(soundPath) || Sounds.CHEST_CLOSE.equals(soundPath)) {
            ci.cancel();
            return;
        }

        // Replace vanilla hurt with alpha hurt
        if (Sounds.PLAYER_HURT.equals(soundPath)) {
            ci.cancel();
            engine.play(SimpleSoundInstance.forUI(SoundRegistry.HURT.get(), 1.0f));
            return;
        }
        // Play alpha hurt on top of other hurt sounds
        if (Sounds.HURT_FREEZE.equals(soundPath)
            || Sounds.HURT_FIRE.equals(soundPath)
            || Sounds.HURT_DROWN.equals(soundPath)
            || Sounds.HURT_BERRY.equals(soundPath)) {
            engine.play(SimpleSoundInstance.forUI(SoundRegistry.HURT.get(), 1.0f));
            // Don't cancel original sound
        }
    }
}