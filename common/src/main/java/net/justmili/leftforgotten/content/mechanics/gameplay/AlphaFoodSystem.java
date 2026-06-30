package net.justmili.leftforgotten.content.mechanics.gameplay;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.EventResult;
import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.Arrays;
import java.util.Map;

public class AlphaFoodSystem {
    /**
     * DEV NOTES
     * Everything works perfectly
     * Boosted Golden Carrot's healing value cause of its high saturation because yes
     *
     * Bugs:
     * - If you hold right-click on a planted berry bush, you will start eating normally
     *
     * Actions taken:
     * - None, I'm not debugging that BS
     *       ~ Millie
     */
    private record FoodEntry(float health, FoodAction... actions) {
        boolean hasAction(FoodAction action) {
            return Arrays.asList(actions).contains(action);
        }
    }
    private static final Map<Item, FoodEntry> FOOD_HEALTH = Map.ofEntries(
        Map.entry(Items.PORKCHOP, new FoodEntry(1.5F)),
        Map.entry(Items.COOKED_PORKCHOP, new FoodEntry(4.0F)),
        Map.entry(Items.BEEF, new FoodEntry(1.5F)),
        Map.entry(Items.COOKED_BEEF, new FoodEntry(4.0F)),
        Map.entry(Items.MUTTON, new FoodEntry(1.0F)),
        Map.entry(Items.COOKED_MUTTON, new FoodEntry(3.0F)),
        Map.entry(Items.CHICKEN, new FoodEntry(1.0F, FoodAction.HUNGERS_WITH_CHANCE)),
        Map.entry(Items.COOKED_CHICKEN, new FoodEntry(3.0F)),
        Map.entry(Items.COD, new FoodEntry(1.0F)),
        Map.entry(Items.COOKED_COD, new FoodEntry(2.5F)),
        Map.entry(Items.SALMON, new FoodEntry(1.0F)),
        Map.entry(Items.COOKED_SALMON, new FoodEntry(3.0F)),
        Map.entry(Items.TROPICAL_FISH, new FoodEntry(0.5F)),
        Map.entry(Items.PUFFERFISH, new FoodEntry(0.5F, FoodAction.POISONS, FoodAction.HUNGERS, FoodAction.CAUSES_NAUSEA)),
        Map.entry(Items.RABBIT, new FoodEntry(1.5F)),
        Map.entry(Items.COOKED_RABBIT, new FoodEntry(2.5F)),
        Map.entry(Items.RABBIT_STEW, new FoodEntry(5.0F, FoodAction.RESULT_BOWL)),
        Map.entry(Items.BREAD, new FoodEntry(2.5F)),
        Map.entry(Items.COOKIE, new FoodEntry(0.5F)),
        Map.entry(Items.PUMPKIN_PIE, new FoodEntry(4.0F)),
        Map.entry(Items.CAKE, new FoodEntry(0.5F)),
        Map.entry(Items.APPLE, new FoodEntry(2.0F)),
        Map.entry(Items.GOLDEN_APPLE, new FoodEntry(10.0F)),
        Map.entry(Items.ENCHANTED_GOLDEN_APPLE, new FoodEntry(10.0F)),
        Map.entry(Items.CARROT, new FoodEntry(1.5F)),
        Map.entry(Items.GOLDEN_CARROT, new FoodEntry(6.0F)),
        Map.entry(Items.BAKED_POTATO, new FoodEntry(2.5F)),
        Map.entry(Items.POTATO, new FoodEntry(0.5F)),
        Map.entry(Items.POISONOUS_POTATO, new FoodEntry(1.0F, FoodAction.POISONS_WITH_CHANCE)),
        Map.entry(Items.BEETROOT, new FoodEntry(0.5F)),
        Map.entry(Items.BEETROOT_SOUP, new FoodEntry(3.0F, FoodAction.RESULT_BOWL)),
        Map.entry(Items.MELON_SLICE, new FoodEntry(1.0F)),
        Map.entry(Items.SWEET_BERRIES, new FoodEntry(0.5F)),
        Map.entry(Items.GLOW_BERRIES, new FoodEntry(0.5F)),
        Map.entry(Items.CHORUS_FRUIT, new FoodEntry(2.0F, FoodAction.TELEPORTS)),
        Map.entry(Items.MUSHROOM_STEW, new FoodEntry(4.5F, FoodAction.RESULT_BOWL)),
        Map.entry(Items.SUSPICIOUS_STEW, new FoodEntry(3.0F, FoodAction.RESULT_BOWL, FoodAction.GIVES_EFFECT_FROM_TYPE)),
        Map.entry(Items.ROTTEN_FLESH, new FoodEntry(2.0F, FoodAction.HUNGERS_WITH_CHANCE)),
        Map.entry(Items.SPIDER_EYE, new FoodEntry(1.0F, FoodAction.POISONS)),
        Map.entry(Items.HONEY_BOTTLE, new FoodEntry(1.5F, FoodAction.CURES_POISON, FoodAction.RESULT_BOTTLE, FoodAction.ALWAYS_EDIBLE)),
        Map.entry(Items.DRIED_KELP, new FoodEntry(0.5F))
    );
    private enum FoodAction {
        RESULT_BOWL, RESULT_BOTTLE,
        TELEPORTS, ALWAYS_EDIBLE,
        CURES_POISON, POISONS_WITH_CHANCE, POISONS,
        HUNGERS_WITH_CHANCE, HUNGERS,
        CAUSES_NAUSEA,
        GIVES_EFFECT_FROM_TYPE
    }

    public static CompoundEventResult<ItemStack> onRightClickItem(Player player, InteractionHand hand) {
        if (player.level().dimension() != LFResources.Levels.ALPHA_MINECRAFT) return CompoundEventResult.pass();
        if (hand != InteractionHand.MAIN_HAND) return CompoundEventResult.interruptTrue(player.getItemInHand(hand));
        ItemStack stack = player.getItemInHand(hand);

        FoodEntry entry = FOOD_HEALTH.get(stack.getItem());
        if (entry != null) {
            Item item = stack.getItem();
            boolean canEat = !healthCheck(player) || entry.hasAction(FoodAction.ALWAYS_EDIBLE);
            if (canEat) {
                if (!CommonClient.nonSurvivalGamemode()) {
                    stack.shrink(1);
                    player.getInventory().setChanged();
                    player.setHealth(Math.min(player.getHealth() + entry.health(), player.getMaxHealth()));
                }
                playConsumptionSound(player.level(), player.getX(), player.getY(), player.getZ(), item);
                for (FoodAction action : entry.actions()) {
                    applyAction(action, player, stack, item);
                }
            }
            return CompoundEventResult.interruptTrue(player.getItemInHand(hand));
        }

        if (stack.getItem().isEdible()) {
            return CompoundEventResult.interruptTrue(stack);
        }

        return CompoundEventResult.pass();
    }

    public static EventResult onRightClickBlock(Player player, InteractionHand hand, BlockPos pos, Direction face) {
        if (hand != InteractionHand.MAIN_HAND) return EventResult.pass();
        if (player.level().dimension() != LFResources.Levels.ALPHA_MINECRAFT) return EventResult.pass();
        ItemStack stack = player.getItemInHand(hand);

        if (FOOD_HEALTH.containsKey(stack.getItem())) return EventResult.pass();
        if (stack.getItem().isEdible()) return EventResult.interruptTrue();

        return EventResult.pass();
    }

    private static void applyAction(FoodAction action, Player player, ItemStack stack, Item item) {
        switch (action) {
            case RESULT_BOWL -> giveResultItem(player, Items.BOWL);
            case RESULT_BOTTLE -> giveResultItem(player, Items.GLASS_BOTTLE);
            case CURES_POISON -> player.removeEffect(MobEffects.POISON);
            case POISONS_WITH_CHANCE -> applyPoisonWithChance(player);
            case POISONS -> applyPoison(player, item);
            case HUNGERS_WITH_CHANCE -> applyHungerWithChance(player, item);
            case HUNGERS -> player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 300, 2));
            case CAUSES_NAUSEA -> player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0));
            case TELEPORTS -> applyChorusTeleport(player);
            case GIVES_EFFECT_FROM_TYPE -> applySuspiciousStewEffect(player, stack);
            default -> {}
        }
    }
    private static void giveResultItem(Player player, Item resultItem) {
        ItemStack result = new ItemStack(resultItem);
        if (!player.getInventory().add(result)) {
            player.drop(result, false);
        }
    }
    private static void applyPoisonWithChance(Player player) {
        if (player.level().random.nextFloat() < 0.6F) {
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 80, 0));
        }
    }
    private static void applyPoison(Player player, Item item) {
        if (item == Items.SPIDER_EYE) {
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
        } else if (item == Items.PUFFERFISH) {
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 1200, 1));
        }
    }
    private static void applyHungerWithChance(Player player, Item item) {
        if (item == Items.ROTTEN_FLESH && player.level().random.nextFloat() < 0.8F) {
            player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0));
        } else if (item == Items.CHICKEN && player.level().random.nextFloat() < 0.3F) {
            player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0));
        }
    }

    private static void applySuspiciousStewEffect(Player player, ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains("Effects", 9)) return;
        ListTag list = tag.getList("Effects", 10);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag entry = list.getCompound(i);
            int duration = entry.contains("EffectDuration", 99) ? entry.getInt("EffectDuration") : 160;
            MobEffect effect = MobEffect.byId(entry.getInt("EffectId"));
            if (effect != null) player.addEffect(new MobEffectInstance(effect, duration));
        }
    }
    private static void applyChorusTeleport(Player player) {
        Level level = player.level();
        for (int attempt = 0; attempt < 16; attempt++) {
            double x = player.getX() + (level.random.nextDouble() - 0.5D) * 16.0D;
            double y = player.getY() + (level.random.nextInt(16) - 8);
            double z = player.getZ() + (level.random.nextDouble() - 0.5D) * 16.0D;
            y = Math.max(level.getMinBuildHeight(), Math.min(level.getMaxBuildHeight() - 1, y));
            double groundY = findGroundY(level, x, y, z);
            if (groundY >= 0) {
                player.teleportTo(x, groundY, z);
                return;
            }
        }
    }
    private static double findGroundY(Level level, double x, double startY, double z) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos((int) x, (int) startY, (int) z);
        while (pos.getY() > level.getMinBuildHeight()) {
            if (level.getBlockState(pos).isSolid()) {
                BlockPos landing = pos.above();
                BlockPos head = landing.above();
                if (!level.getBlockState(landing).isSolid() && !level.getBlockState(head).isSolid()) {
                    return landing.getY();
                }
            }
            pos.move(0, -1, 0);
        }
        return -1;
    }

    private static void playConsumptionSound(LevelAccessor world, double x, double y, double z, Item item) {
        float pitch = (float) (0.8 + Math.random() * 0.4);
        var sound = item == Items.HONEY_BOTTLE ? SoundEvents.HONEY_DRINK : SoundEvents.GENERIC_EAT;
        if (world instanceof Level level) {
            if (!level.isClientSide()) {
                level.playSound(null, x, y, z, sound, SoundSource.NEUTRAL, 1.0f, pitch);
            } else {
                level.playLocalSound(x, y, z, sound, SoundSource.NEUTRAL, 1.0f, pitch, false);
            }
        }
    }
    private static boolean healthCheck(Player player) {
        return player.getHealth() >= player.getMaxHealth();
    }
}