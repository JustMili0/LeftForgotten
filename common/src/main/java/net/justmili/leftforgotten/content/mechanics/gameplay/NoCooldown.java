
package net.justmili.leftforgotten.content.mechanics.gameplay;

import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.common.AttribUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class NoCooldown {
    static final boolean hasBetterCombat = Platform.isModLoaded("bettercombat");
    static final AttributeModifier modifier = AttribUtil.create(LeftForgotten.asId("no_cooldown_base"), 200, AttributeModifier.Operation.ADDITION);

    public static void onPlayerJoin(ServerPlayer player) {
        if (hasBetterCombat) return;
        applyCooldown(player, player.level());
    }

    public static void onChangeDimension(ServerPlayer player, ResourceKey<Level> fromLevel, ResourceKey<Level> toLevel) {
        if (hasBetterCombat) return;
        applyCooldown(player, Versions.get(player, toLevel));
    }

    public static void onPlayerRespawn(ServerPlayer player, boolean alive) {
        if (hasBetterCombat) return;
        applyCooldown(player, Versions.get(player, player.getRespawnDimension()));
    }

    static void applyCooldown(Player player, Level level) {
        var attrib = AttribUtil.get(player, Attributes.ATTACK_SPEED);
        if (attrib == null) return;
        if (Versions.hadNoAttackCooldown(level)) AttribUtil.addOrUpdate(attrib, modifier);
    }
}