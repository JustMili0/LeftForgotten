package net.justmili.leftforgotten.content.mechanics.events;

import dev.architectury.event.EventResult;
import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.core.registries.LevelRegistry;
import net.justmili.leftforgotten.core.util.Versions;
import net.justmili.leftforgotten.libs.v1.utils.common.TickUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Set;

public class MoveToAlpha {

    public static EventResult onEntityHurt(LivingEntity entity, DamageSource source, float value) {
        if (!Versions.isOverworld(entity.level())
            || source == null
            || !source.is(DamageTypes.FELL_OUT_OF_WORLD)
        ) return EventResult.pass();
        if (!(Versions.get(entity, LevelRegistry.ALPHA) instanceof ServerLevel level)) return EventResult.pass();

        entity.teleportTo(level, entity.getX(), 156, entity.getZ(), Set.of(), entity.getYRot(), entity.getXRot());

        return EventResult.interruptFalse();
    }

    public static EventResult onHurtByDimensionEntry(LivingEntity entity, DamageSource source, float value) {
        if (!Versions.isHighestLayer(entity.level())
            || source == null
            || !source.is(DamageTypes.FALL)
            || value > 512f
            || entity.getHealth() - value > 0
        ) return EventResult.pass();

        entity.setHealth(2);
        entity.hurt(entity.damageSources().fall(), 1f);

        return EventResult.interruptFalse();
    }

    public static void onPlayerTick(Player player) {
        if (!Versions.isHighestLayer(player.level()) || player.getY() < 196) return;
        if (!(Versions.get(player, Level.OVERWORLD) instanceof ServerLevel level)) return;

        var delta = player.getDeltaMovement();
        boolean wasFallFlying = player.isFallFlying();
        player.teleportTo(level, player.getX(), getReturnY(), player.getZ(), RelativeMovement.ROTATION, player.getYRot(), player.getXRot());
        player.setDeltaMovement(delta);
        if (wasFallFlying) player.startFallFlying();

        // Schedule effect for next tick
        TickUtil.waitTicks(1, () -> player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 40, 0, false, false)));
    }

    static int getReturnY() {
        return Platform.isModLoaded("bigglobe")? -1049 : -88;
    }
}