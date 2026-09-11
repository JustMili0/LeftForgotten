
package net.justmili.leftforgotten.content.mechanics.gameplay;

import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.libs.v1.utils.common.AttribUtil;
import net.justmili.leftforgotten.util.Versions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class NoCooldown {
    static final AttributeModifier baseModifier = AttribUtil.create(LeftForgotten.asId("no_cooldown_base"), 200, AttributeModifier.Operation.ADDITION);
    static final AttributeModifier bcModifier = AttribUtil.create(LeftForgotten.asId("no_cooldown_bc"), 2, AttributeModifier.Operation.ADDITION);

    public static void onChangeDimension(ServerPlayer player, ResourceKey<Level> fromLevel, ResourceKey<Level> toLevel) {
        applyCooldown(player, toLevel);
    }

    public static void onPlayerRespawn(ServerPlayer player, boolean bl) {
        var toDim = player.getRespawnDimension();
        applyCooldown(player, toDim);
    }

    public static void onPlayerJoin(ServerPlayer player) {
        var toDim = player.level().dimension();
        applyCooldown(player, toDim);
    }

    static void applyCooldown(Player player, ResourceKey<Level> toDim) {
        var attrib = AttribUtil.get(player, Attributes.ATTACK_SPEED);
        if (attrib == null) return;

        // then add the modifier to the player
        var modifier = Platform.isModLoaded("bettercombat") ? bcModifier : baseModifier;
        if (Versions.hadNoAttackCooldown(toDim)) {
            AttribUtil.addOrUpdate(attrib, modifier);
        } else {
            attrib.removeModifier(modifier);
        }
    }
}