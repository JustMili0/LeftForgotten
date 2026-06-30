package net.justmili.leftforgotten.content.mechanics.events;

import dev.architectury.event.EventResult;
import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.registries.LFResources;
import net.justmili.leftforgotten.libs.v1.utils.TickUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MoveToAlpha {
    private static int returnY() {
        return !Platform.isModLoaded("bigglobe")? -88 : -1049;
    }

    public static EventResult onEntityHurt(LivingEntity entity, DamageSource source, float v) {
        if (!(entity instanceof ServerPlayer player)) return EventResult.pass();
        if (source == null) return EventResult.pass();
        if (!player.level().dimension().equals(Level.OVERWORLD)) return EventResult.pass();
        if (!source.is(DamageTypes.FELL_OUT_OF_WORLD)) return EventResult.pass();

        ServerLevel newLevel = player.getServer().getLevel(LFResources.Levels.ALPHA_MINECRAFT);
        if (newLevel == null) return EventResult.pass();

        player.teleportTo(newLevel, player.getX(), 156, player.getZ(), player.getYRot(), player.getXRot());

        return EventResult.interruptFalse();
    }

    public static EventResult onHurtByDimensionEntry(LivingEntity entity, DamageSource source, float v) {
        if (!(entity instanceof ServerPlayer player)) return EventResult.pass();
        if (source == null) return EventResult.pass();
        if (!player.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return EventResult.pass();
        if (!source.is(DamageTypes.FALL)) return EventResult.pass(); // Filter only for fall aka for entry
        if (v > 512f) return EventResult.pass(); // Cancel the damage
        if (player.getHealth() - v > 0) return EventResult.pass();

        player.setHealth(2);
        player.hurt(player.damageSources().fall(), 1f);

        return EventResult.interruptFalse();
    }

    public static void onPlayerTick(Player ticking) {
        if (!(ticking instanceof ServerPlayer player)) return;
        if (!player.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return;
        if (player.getY() < 196) return;

        ServerLevel overworld = player.getServer().getLevel(Level.OVERWORLD);
        if (overworld == null) return;

        Vec3 momentum = player.getDeltaMovement();
        player.teleportTo(overworld, player.getX(), returnY(), player.getZ(), player.getYRot(), player.getXRot());
        player.setDeltaMovement(momentum);
        player.startFallFlying();

        // Schedule effect for next tick
        TickUtil.waitTicks(1, () ->
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 0, false, false)));
    }
}