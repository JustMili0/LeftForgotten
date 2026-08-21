package net.justmili.leftforgotten.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(LeftForgotten.MODID,Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> HURT,
        MUSIC_13, MUSIC_BOO, MUSIC_CALM1, MUSIC_CALM2, MUSIC_CALM3,
        MUSIC_HAL1, MUSIC_HAL2, MUSIC_HAL3, MUSIC_HAL4,
        MUSIC_NUANCE1, MUSIC_NUANCE2, MUSIC_PIANO1, MUSIC_PIANO2, MUSIC_PIANO3,
        MUSIC_DROOPY_LIKES_YOUR_FACE;

    static {
        HURT = register("entity.player.hurt");
        MUSIC_13 = register("music.game.13");
        MUSIC_BOO = register("music.game.boo");
        MUSIC_CALM1 = register("music.game.calm1");
        MUSIC_CALM2 = register("music.game.calm2");
        MUSIC_CALM3 = register("music.game.calm3");
        MUSIC_HAL1 = register("music.game.hal1");
        MUSIC_HAL2 = register("music.game.hal2");
        MUSIC_HAL3 = register("music.game.hal3");
        MUSIC_HAL4 = register("music.game.hal4");
        MUSIC_NUANCE1 = register("music.game.nuance1");
        MUSIC_NUANCE2 = register("music.game.nuance2");
        MUSIC_PIANO1 = register("music.game.piano1");
        MUSIC_PIANO2 = register("music.game.piano2");
        MUSIC_PIANO3 = register("music.game.piano3");
        MUSIC_DROOPY_LIKES_YOUR_FACE = register("music.unused.droopy_likes_your_face");
    }

    private static RegistrySupplier<SoundEvent> register(String name) {
        return REGISTRY.register(name, () -> SoundEvent.createVariableRangeEvent(LeftForgotten.asResource(name)));
    }

    public static void register() {
        REGISTRY.register();
    }
}