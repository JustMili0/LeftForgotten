package net.justmili.leftforgotten.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

public class LFSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(LeftForgotten.MODID, Registries.SOUND_EVENT);

    /**ISSUE:
     * All the music events are reistered and everything but when played via /playsound or via other means, no sound is heared.
     * player.entity.hurt does work tho and I have no fucking clue why or how
     */
    public static final RegistrySupplier<SoundEvent> HURT = registerSound("entity.player.hurt");
    public static final RegistrySupplier<SoundEvent> MUSIC_13 = registerSound("music.game.13");
    public static final RegistrySupplier<SoundEvent> MUSIC_BOO = registerSound("music.game.boo");
    public static final RegistrySupplier<SoundEvent> MUSIC_CALM1 = registerSound("music.game.calm1");
    public static final RegistrySupplier<SoundEvent> MUSIC_CALM2 = registerSound("music.game.calm2");
    public static final RegistrySupplier<SoundEvent> MUSIC_CALM3 = registerSound("music.game.calm3");
    public static final RegistrySupplier<SoundEvent> MUSIC_HAL1 = registerSound("music.game.hal1");
    public static final RegistrySupplier<SoundEvent> MUSIC_HAL2 = registerSound("music.game.hal2");
    public static final RegistrySupplier<SoundEvent> MUSIC_HAL3 = registerSound("music.game.hal3");
    public static final RegistrySupplier<SoundEvent> MUSIC_HAL4 = registerSound("music.game.hal4");
    public static final RegistrySupplier<SoundEvent> MUSIC_NUANCE1 = registerSound("music.game.nuance1");
    public static final RegistrySupplier<SoundEvent> MUSIC_NUANCE2 = registerSound("music.game.nuance2");
    public static final RegistrySupplier<SoundEvent> MUSIC_PIANO1 = registerSound("music.game.piano1");
    public static final RegistrySupplier<SoundEvent> MUSIC_PIANO2 = registerSound("music.game.piano2");
    public static final RegistrySupplier<SoundEvent> MUSIC_PIANO3 = registerSound("music.game.piano3");
    public static final RegistrySupplier<SoundEvent> MUSIC_DROOPY_LIKES_YOUR_FACE = registerSound("music.unused.droopy_likes_your_face");

    private static RegistrySupplier<SoundEvent> registerSound(String name) {
        return REGISTRY.register(name, () -> SoundEvent.createVariableRangeEvent(LeftForgotten.asResource(name)));
    }

    public static void register() {
        REGISTRY.register();
    }
}