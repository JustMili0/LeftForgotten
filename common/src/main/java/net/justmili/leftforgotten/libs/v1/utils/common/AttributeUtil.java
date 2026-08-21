package net.justmili.leftforgotten.libs.v1.utils.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class AttributeUtil {
    public static AttributeModifier create(ResourceLocation id, double value, AttributeModifier.Operation operation) {
        return new AttributeModifier(id.toString(), value, operation);
    }
    public static AttributeInstance get(LivingEntity entity, Attribute attribute) {
        return entity.getAttribute(attribute);
    }
    public static double getValue(LivingEntity entity, Attribute attribute) {
        return get(entity, attribute).getValue();
    }

    public static void addTransient(AttributeInstance instance, AttributeModifier modifier) {
        if (instance == null) return;
        instance.addTransientModifier(modifier);
    }
    public static void addPermanent(AttributeInstance instance, AttributeModifier modifier) {
        if (instance == null) return;
        instance.addPermanentModifier(modifier);
    }
    public static void addTransient(AttributeInstance instance, ResourceLocation id, double value, AttributeModifier.Operation operation) {
        if (instance == null) return;
        addTransient(instance, create(id, value, operation));
    }
    public static void addPermanent(AttributeInstance instance, ResourceLocation id, double value, AttributeModifier.Operation operation) {
        if (instance == null) return;
        addPermanent(instance, create(id, value, operation));
    }

    public static void addOrUpdate(AttributeInstance instance, AttributeModifier modifier) {
        // Added addOrUpdateTransientModifier in 1.20.5
        if (instance == null) return;
        addTransient(instance, modifier);
    }
    public static void addOrReplace(AttributeInstance instance, AttributeModifier modifier) {
        // Added addOrReplacePermanentModifier in 1.20.5
        if (instance == null) return;
        instance.removeModifier(modifier);
        addPermanent(instance, modifier);
    }
    public static void addOrUpdate(AttributeInstance instance, ResourceLocation id, double value, AttributeModifier.Operation operation) {
        // Added addOrUpdateTransientModifier in 1.20.5
        if (instance == null) return;
        addOrUpdate(instance, create(id, value, operation));
    }
    public static void addOrReplace(AttributeInstance instance, ResourceLocation id, double value, AttributeModifier.Operation operation) {
        // Added addOrReplacePermanentModifier in 1.20.5
        if (instance == null) return;
        var modifier = create(id, value, operation);
        instance.removeModifier(modifier);
        addOrReplace(instance, modifier);
    }
}
