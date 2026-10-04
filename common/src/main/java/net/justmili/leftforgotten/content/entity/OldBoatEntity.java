package net.justmili.leftforgotten.content.entity;

import net.justmili.leftforgotten.core.network.OldBoatImpactPacket;
import net.justmili.leftforgotten.core.registries.EntityRegistry;
import net.justmili.leftforgotten.core.registries.ItemRegistry;
import net.justmili.leftforgotten.libs.v1.utils.common.Maths;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class OldBoatEntity extends Boat {
    static final double BREAK_SPEED_THRESHOLD = 0.2;
    static final float MAX_HEALTH = 4.0F;
    public float health = MAX_HEALTH;

    public OldBoatEntity(EntityType<? extends Boat> type, Level level) {
        super(type, level);
    }

    public OldBoatEntity(Level level, double x, double y, double z) {
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
        var oldDelta = getDeltaMovement();
        double speedBefore = Maths.sqrt(oldDelta.x * oldDelta.x + oldDelta.z * oldDelta.z);

        super.tick();

        var newDelta = getDeltaMovement();
        double speedAfter = Maths.sqrt(newDelta.x * newDelta.x + newDelta.z * newDelta.z);
        if (speedBefore > BREAK_SPEED_THRESHOLD && speedAfter < speedBefore * 0.4) {
            if (level().isClientSide) OldBoatImpactPacket.send(getId());
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (isInvulnerableTo(source) || level().isClientSide()) return false;

        boolean isCreative = source.getEntity() instanceof Player player && player.getAbilities().instabuild;
        if (isCreative) {
            discard();
            return true;
        }

        health -= amount;
        if (health <= 0 && !isRemoved()) {
            spawnAtLocation(new ItemStack(ItemRegistry.WOODEN_PLANKS.get(), 3));
            spawnAtLocation(new ItemStack(Items.STICK, 2));
            discard();
        }

        return true;
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