package net.justmili.leftforgotten.core.util.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.core.registries.SoundRegistry;
import net.justmili.leftforgotten.libs.v1.utils.common.Maths;
import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

import java.util.List;

@Environment(EnvType.CLIENT)
public class Sounds {
    public static final ResourceLocation
        STOMACH_GROWL = ResourceUtil.asPath("subtle_effects:entity.player.stomach_growl"),
        CHEST_OPEN = SoundEvents.CHEST_OPEN.getLocation(),
        CHEST_CLOSE = SoundEvents.CHEST_CLOSE.getLocation(),
        PLAYER_HURT = SoundEvents.PLAYER_HURT.getLocation(),
        HURT_FREEZE = SoundEvents.PLAYER_HURT_FREEZE.getLocation(),
        HURT_FIRE = SoundEvents.PLAYER_HURT_ON_FIRE.getLocation(),
        HURT_DROWN = SoundEvents.PLAYER_HURT_DROWN.getLocation(),
        HURT_BERRY = SoundEvents.PLAYER_HURT_SWEET_BERRY_BUSH.getLocation();

    public static SoundEvent getRandomTrack() {
        var tracks = getMusicTracks();
        while (true) {
            var track = tracks.get(Maths.random.nextInt(tracks.size()));
            if (track == SoundRegistry.MUSIC_13.get() && !Maths.chance(0.02f)) continue;
            if (track == SoundRegistry.MUSIC_DROOPY_LIKES_YOUR_FACE.get() && !Maths.chance(0.2f)) continue;
            return track;
        }
    }

    public static List<SoundEvent> getMusicTracks() {
        return List.of(
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
            SoundRegistry.MUSIC_13.get(),
            SoundRegistry.MUSIC_DROOPY_LIKES_YOUR_FACE.get()
        );
    }
}
