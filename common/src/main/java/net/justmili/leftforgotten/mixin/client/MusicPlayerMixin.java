package net.justmili.leftforgotten.mixin.client;

import net.justmili.leftforgotten.registries.SoundRegistry;
import net.justmili.leftforgotten.registries.extra.LFResources;
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
    @Shadow @Final private Minecraft minecraft;
    @Shadow @Nullable private SoundInstance currentMusic;
    @Shadow private int nextSongDelay;

    @Unique
    private static List<SoundEvent> ALPHA_TRACKS = null;
    @Unique
    private static List<SoundEvent> getTracks() {
        if (ALPHA_TRACKS == null) {
            ALPHA_TRACKS = List.of(
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
        return ALPHA_TRACKS;
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onTick(CallbackInfo ci) {
        if (ClientUtil.level() == null) return;
        if (!ClientUtil.inDimension(LFResources.ALPHA_MINECRAFT)) return;

        ci.cancel();

        if (this.currentMusic != null) {
            if (!this.minecraft.getSoundManager().isActive(this.currentMusic)) {
                this.currentMusic = null;
                this.nextSongDelay = Math.min(this.nextSongDelay, Mth.nextInt(RandomSource.create(), 6000, 24000));
            }
        }

        this.nextSongDelay = Math.min(this.nextSongDelay, 24000);
        if (this.currentMusic == null && this.nextSongDelay-- <= 0) {
            SoundEvent track = pickTrack();
            SoundInstance instance = SimpleSoundInstance.forMusic(track);
            this.currentMusic = instance;
            if (this.currentMusic.getSound() != SoundManager.EMPTY_SOUND) {
                minecraft.getSoundManager().play(instance);
            }
            this.nextSongDelay = Integer.MAX_VALUE;
        }
    }

    private SoundEvent pickTrack() {
        List<SoundEvent> tracks = getTracks();
        while (true) {
            SoundEvent track = tracks.get(MathUtil.random.nextInt(tracks.size()));
            if (track == SoundRegistry.MUSIC_13.get() && MathUtil.chance(0.02f)) continue;
            if (track == SoundRegistry.MUSIC_DROOPY_LIKES_YOUR_FACE.get() && MathUtil.chance(0.2f)) continue;
            return track;
        }
    }
}