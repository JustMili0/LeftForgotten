package net.justmili.leftforgotten.content.entity;

import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.registries.EntityRegistry;
import net.justmili.leftforgotten.registries.ItemRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class LFBoatEntity extends Boat {
    static final double BREAK_SPEED_THRESHOLD = 0.2;
    static final float MAX_HEALTH = 4.0F;
    public float health = MAX_HEALTH;

    public LFBoatEntity(EntityType<? extends Boat> type, Level level) {
        super(type, level);
    }

    public LFBoatEntity(Level level, double x, double y, double z) {
        this(EntityRegistry.BOAT.get(), level);
        setPos(x, y, z);
        xo = x;
        yo = y;
        zo = z;
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(ItemRegistry.BOAT.get());
    }

    @Override
    public boolean getPaddleState(int side) {
        return false;
    }

    public void breakOnImpactOnServer() {
        if (!level().isClientSide && !isRemoved()) {
            spawnAtLocation(new ItemStack(ItemRegistry.WOODEN_PLANKS.get(), 3));
            spawnAtLocation(new ItemStack(Items.STICK, 2));
            discard();
        }
    }

    @Override
    public void tick() {
        var delta = getDeltaMovement();
        double speedBefore = Math.sqrt(delta.x * delta.x + delta.z * delta.z);

        super.tick();

        var newDelta = getDeltaMovement();
        double speedAfter = Math.sqrt(newDelta.x * newDelta.x + newDelta.z * newDelta.z);
        if (speedBefore > BREAK_SPEED_THRESHOLD && speedAfter < speedBefore * 0.4) {
            if (level().isClientSide) BoatImpactPacket.send(getId());
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (isInvulnerableTo(source)) return false;
        if (!level().isClientSide) {
            boolean isCreative = source.getEntity() instanceof Player player && player.isCreative();
            if (isCreative) {
                discard();
                return true;
            }
            health -= amount;
            if (health <= 0.0F && !isRemoved()) {
                spawnAtLocation(new ItemStack(ItemRegistry.WOODEN_PLANKS.get(), 3));
                spawnAtLocation(new ItemStack(Items.STICK, 2));
                discard();
            }
        }
        return true;
    }

    @Override
    public boolean isControlledByLocalInstance() {
        if (Platform.isModLoaded("wurst") ||
            Platform.isModLoaded("wurstclient") ||
            Platform.isModLoaded("meteor-client") ||
            Platform.isModLoaded("meteor") ||
            Platform.isModLoaded("liquidbounce") ||
            Platform.isModLoaded("future") ||
            Platform.isModLoaded("impact") ||
            Platform.isModLoaded("ares") ||
            Platform.isModLoaded("sigma") ||
            Platform.isModLoaded("inertia")) {
            return false;
        }
        return super.isControlledByLocalInstance();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("Health", health);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        health = tag.contains("Health")? tag.getFloat("Health") : MAX_HEALTH;
    }
}