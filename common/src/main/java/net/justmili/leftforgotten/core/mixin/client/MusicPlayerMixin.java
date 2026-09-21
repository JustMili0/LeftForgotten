package net.justmili.leftforgotten.core.mixin.client;

import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.core.util.client.Sounds;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.libs.v1.utils.common.Maths;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.client.sounds.SoundManager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MusicManager.class)
public class MusicPlayerMixin {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    @Nullable
    private SoundInstance currentMusic;

    @Shadow
    private int nextSongDelay;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void lf$tryPlayTracks(CallbackInfo ci) {
        if (ClientUtil.level() == null || Versions.isOldVersion(ClientUtil.level())) return;

        ci.cancel();

        var music = this.currentMusic;
        int nextDelay = this.nextSongDelay;

        if (music != null && !minecraft.getSoundManager().isActive(music)) {
            music = null;
            nextDelay = Maths.min(nextDelay, Maths.randomInt(6000, 24000));
        }

        this.nextSongDelay = nextDelay;

        if (music == null && this.nextSongDelay-- <= 0) {
            var track = Sounds.getRandomTrack();
            var instance = SimpleSoundInstance.forMusic(track);
            if (instance.getSound() != SoundManager.EMPTY_SOUND) minecraft.getSoundManager().play(instance);
            this.nextSongDelay = 24000;
        }
    }
}