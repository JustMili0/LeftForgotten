package net.justmili.leftforgotten.core.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.content.entity.OldBoatEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(LeftForgotten.MODID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<OldBoatEntity>> BOAT;

    static {
        //BOAT = REGISTRY.register("boat", () -> EntityType.Builder.<LFBoatEntity>of(LFBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build("boat"));
        BOAT = register("boat", EntityType.Builder.<OldBoatEntity>of(OldBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10));
    }

    private static <T extends Entity> RegistrySupplier<EntityType<T>> register(String id, EntityType.Builder<T> builder) {
        return REGISTRY.register(id, () -> builder.build(id));
    }

    public static void register() {
        REGISTRY.register();
    }
}