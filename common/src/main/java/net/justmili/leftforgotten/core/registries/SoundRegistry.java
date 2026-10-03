package net.justmili.leftforgotten.core.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(LeftForgotten.ID,Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> HURT,
        MUSIC_13, MUSIC_BOO, MUSIC_CALM1, MUSIC_CALM2, MUSIC_CALM3,
        MUSIC_HAL1, MUSIC_HAL2, MUSIC_HAL3, MUSIC_HAL4,
        MUSIC_NUANCE1, MUSIC_NUANCE2, MUSIC_PIANO1, MUSIC_PIANO2, MUSIC_PIANO3,
        MUSIC_DROOPY_LIKES_YOUR_FACE;

    static {
        HURT = sound("entity.player.hurt");
        MUSIC_13 = sound("music.game.13");
        MUSIC_BOO = sound("music.game.boo");
        MUSIC_CALM1 = sound("music.game.calm1");
        MUSIC_CALM2 = sound("music.game.calm2");
        MUSIC_CALM3 = sound("music.game.calm3");
        MUSIC_HAL1 = sound("music.game.hal1");
        MUSIC_HAL2 = sound("music.game.hal2");
        MUSIC_HAL3 = sound("music.game.hal3");
        MUSIC_HAL4 = sound("music.game.hal4");
        MUSIC_NUANCE1 = sound("music.game.nuance1");
        MUSIC_NUANCE2 = sound("music.game.nuance2");
        MUSIC_PIANO1 = sound("music.game.piano1");
        MUSIC_PIANO2 = sound("music.game.piano2");
        MUSIC_PIANO3 = sound("music.game.piano3");
        MUSIC_DROOPY_LIKES_YOUR_FACE = sound("music.unused.droopy_likes_your_face");
    }

    private static RegistrySupplier<SoundEvent> sound(String path) {
        return REGISTRY.register(path, () -> SoundEvent.createVariableRangeEvent(LeftForgotten.asId(path)));
    }

    public static void init() {
        REGISTRY.register();
    }
}