package net.justmili.leftforgotten.content.mechanics.events;

import dev.architectury.event.EventResult;
import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.libs.v1.utils.common.TickUtil;
import net.justmili.leftforgotten.registries.extra.LFResources;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Set;

public class MoveToAlpha {
    static int returnY() {
        return !Platform.isModLoaded("bigglobe")? -88 : -1049;
    }

    public static EventResult onEntityHurt(LivingEntity entity, DamageSource source, float v) {
        if (source == null) return EventResult.pass();
        if (!entity.level().dimension().equals(Level.OVERWORLD)) return EventResult.pass();
        if (!source.is(DamageTypes.FELL_OUT_OF_WORLD)) return EventResult.pass();

        var newLevel = entity.getServer().getLevel(LFResources.ALPHA_MINECRAFT);
        if (newLevel == null) return EventResult.pass();

        entity.teleportTo(newLevel, entity.getX(), 156, entity.getZ(), Set.of(), entity.getYRot(), entity.getXRot());

        return EventResult.interruptFalse();
    }

    public static EventResult onHurtByDimensionEntry(LivingEntity entity, DamageSource source, float v) {
        if (!(entity instanceof ServerPlayer player)) return EventResult.pass();
        if (source == null) return EventResult.pass();
        if (!player.level().dimension().equals(LFResources.ALPHA_MINECRAFT)) return EventResult.pass();
        if (!source.is(DamageTypes.FALL)) return EventResult.pass(); // Filter only for fall aka for entry
        if (v > 512f) return EventResult.pass(); // Cancel the damage
        if (player.getHealth() - v > 0) return EventResult.pass();

        player.setHealth(2);
        player.hurt(player.damageSources().fall(), 1f);

        return EventResult.interruptFalse();
    }

    public static void onPlayerTick(Player ticking) {
        if (!(ticking instanceof ServerPlayer player)) return;
        if (!player.level().dimension().equals(LFResources.ALPHA_MINECRAFT)) return;
        if (player.getY() < 196) return;

        var overworld = player.getServer().getLevel(Level.OVERWORLD);
        if (overworld == null) return;

        var delta = player.getDeltaMovement();
        player.teleportTo(overworld, player.getX(), returnY(), player.getZ(), player.getYRot(), player.getXRot());
        player.setDeltaMovement(delta);
        player.startFallFlying();

        // Schedule effect for next tick
        TickUtil.waitTicks(1, () ->
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 0, false, false)));
    }
}