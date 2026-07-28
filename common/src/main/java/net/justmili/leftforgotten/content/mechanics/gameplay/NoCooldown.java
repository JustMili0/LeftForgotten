
package net.justmili.leftforgotten.content.mechanics.gameplay;

import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class NoCooldown {
    private static final UUID MODIFIER_UUID = UUID.fromString("9b91a426-cc5c-4a08-a0e5-7d00627cb3ef");
    private static final AttributeModifier baseModifier = new AttributeModifier(LeftForgotten.asResource("no_cooldown"),255.0, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier bcModifier = new AttributeModifier(LeftForgotten.asResource("no_cooldown"),2.0, AttributeModifier.Operation.ADD_VALUE);

    public static void onChangeDimension(ServerPlayer player, ResourceKey<Level> fromLevel, ResourceKey<Level> toLevel) {
        applyCooldown(player, toLevel);
    }

    public static void onPlayerRespawn(ServerPlayer player, boolean b, Entity.RemovalReason removalReason) {
        ResourceKey<Level> toDim = player.getRespawnDimension();

        applyCooldown(player, toDim);
    }

    public static void onPlayerJoin(ServerPlayer player) {
        ResourceKey<Level> toDim = player.level().dimension();

        applyCooldown(player, toDim);
    }

    private static void applyCooldown(Player player, ResourceKey<Level> toDim) {
        AttributeInstance attackSpeedAttr = player.getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeedAttr == null) return;

        // remove old bugged attributes
        Set<AttributeModifier> buggedAttributes = new HashSet<>();
        for (AttributeModifier modifier : attackSpeedAttr.getModifiers()) {
            if (modifier.toString().equals(MODIFIER_UUID.toString())) {
                buggedAttributes.add(modifier);
            }
        }
        for (AttributeModifier buggedAttribute : buggedAttributes) {
            attackSpeedAttr.removeModifier(buggedAttribute);
        }

        // then add the modifier to the player
        AttributeModifier modifier = Platform.isModLoaded("bettercombat") ? bcModifier : baseModifier;
        if (toDim.equals(LFResources.ALPHA_MINECRAFT)) {
            attackSpeedAttr.addTransientModifier(modifier);
        } else {
            attackSpeedAttr.removeModifier(modifier);
        }
    }
}