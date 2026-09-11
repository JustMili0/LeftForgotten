package net.justmili.leftforgotten.core.mixin.client;

import net.justmili.leftforgotten.core.registries.SoundRegistry;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.client.ClientUtil;
import net.justmili.leftforgotten.libs.v1.utils.common.MathUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

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

    @Unique
    private static List<SoundEvent> lf$ALPHA_TRACKS = null;

    @Unique
    private static List<SoundEvent> lf$getTracks() {
        if (lf$ALPHA_TRACKS == null) {
            lf$ALPHA_TRACKS = List.of(
                SoundRegistry.MUSIC_13.get(),
                SoundRegistry.MUSIC_BOO.get(),
                SoundRegistry.MUSIC_CALM1.get(),
                SoundRegistry.MUSIC_CALM2.get(),
                SoundRegistry.MUSIC_CALM3.get(),
                SoundRegistry.MUSIC_HAL1.get(),
                SoundRegistry.MUSIC_HAL2.get(),
                SoundRegistry.MUSIC_HAL3.get(),
                SoundRegistry.MUSIC_HAL4.get(),
                SoundRegistry.MUSIC_NUANCE1.get(),
                SoundRegistry.MUSIC_NUANCE2.get(),
                SoundRegistry.MUSIC_PIANO1.get(),
                SoundRegistry.MUSIC_PIANO2.get(),
                SoundRegistry.MUSIC_PIANO3.get(),
                SoundRegistry.MUSIC_DROOPY_LIKES_YOUR_FACE.get()
            );
        }
        return lf$ALPHA_TRACKS;
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void lf$onTick(CallbackInfo ci) {
        if (ClientUtil.level() == null || Versions.isOldVersion(ClientUtil.dimension())) return;

        ci.cancel();

        var music = this.currentMusic;
        int nextDelay = this.nextSongDelay;

        if (music != null) {
            if (!minecraft.getSoundManager().isActive(music)) {
                music = null;
                nextDelay = Math.min(nextDelay, Mth.nextInt(RandomSource.create(), 6000, 24000));
            }
        }

        this.nextSongDelay = Math.min(nextDelay, 24000);
        if (music == null && this.nextSongDelay-- <= 0) {
            var track = lf$pickTrack();
            var instance = SimpleSoundInstance.forMusic(track);
            music = instance;
            if (music.getSound() != SoundManager.EMPTY_SOUND) {
                minecraft.getSoundManager().play(instance);
            }
            this.nextSongDelay = Integer.MAX_VALUE;
        }
    }

    @Unique
    private SoundEvent lf$pickTrack() {
        var tracks = lf$getTracks();
        while (true) {
            var track = tracks.get(MathUtil.random.nextInt(tracks.size()));
            if (track == SoundRegistry.MUSIC_13.get() && MathUtil.chance(0.02f)) continue;
            if (track == SoundRegistry.MUSIC_DROOPY_LIKES_YOUR_FACE.get() && MathUtil.chance(0.2f)) continue;
            return track;
        }
    }
}