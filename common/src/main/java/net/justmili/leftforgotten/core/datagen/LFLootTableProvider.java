package net.justmili.leftforgotten.core.datagen;

import com.google.common.collect.Streams;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.registries.LFBlocks;
import net.justmili.leftforgotten.registries.LFItems;
import net.justmili.leftforgotten.libs.v1.utils.datagen.extensions.KnownBlocksLootProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class LFLootTableProvider extends LootTableProvider {
    public LFLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), List.of(
            new SubProviderEntry(LFBlockLootProvider::new, LootContextParamSets.BLOCK),
            new SubProviderEntry(LFChestLootProvider::new, LootContextParamSets.CHEST)
        ), registries);
    }

    public static class LFBlockLootProvider extends BlockLootSubProvider implements KnownBlocksLootProvider {
        protected LFBlockLootProvider(HolderLookup.Provider provider) {
            super(Set.of(), FeatureFlags.DEFAULT_FLAGS, provider);
        }

        @Override
        public void generate() {
            var FORTUNE = registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE);

            // Nature / Ground
            add(LFBlocks.GRASS_BLOCK.get(), createSingleItemTableWithSilkTouch(LFBlocks.GRASS_BLOCK.get(), LFBlocks.DIRT.get()));
            dropSelf(LFBlocks.DIRT.get());
            add(LFBlocks.FARMLAND.get(), createSingleItemTableWithSilkTouch(LFBlocks.FARMLAND.get(), LFBlocks.DIRT.get()));
            add(LFBlocks.GRAVEL.get(), createSilkTouchDispatchTable(LFBlocks.GRAVEL.get(),
                LootItem.lootTableItem(Items.FLINT)
                    .when(BonusLevelTableCondition.bonusLevelFlatChance(FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F))
                    .otherwise(LootItem.lootTableItem(LFBlocks.GRAVEL.get()))));
            dropSelf(LFBlocks.SAND.get());
            add(LFBlocks.CLAY.get(), createSilkTouchDispatchTable(LFBlocks.CLAY.get(),
                LootItem.lootTableItem(LFItems.CLAY_BALL.get())
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4)))));

            // Nature / Vegetation
            dropSelf(LFBlocks.RED_FLOWER.get());
            dropSelf(LFBlocks.YELLOW_FLOWER.get());
            dropSelf(LFBlocks.RED_MUSHROOM.get());
            dropSelf(LFBlocks.BROWN_MUSHROOM.get());
            dropSelf(LFBlocks.CACTUS.get());
            dropSelf(LFBlocks.SAPLING.get());
            add(LFBlocks.LEAVES.get(), createLeavesDrops(LFBlocks.LEAVES.get(), LFBlocks.SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

            // Building / Wood
            dropSelf(LFBlocks.WOOD.get());
            dropSelf(LFBlocks.WOOD_6_SIDED.get());
            dropSelf(LFBlocks.WOODEN_PLANKS.get());
            dropSelf(LFBlocks.WOODEN_STAIRS.get());
            dropSelf(LFBlocks.WOODEN_SLAB.get());
            dropSelf(LFBlocks.FENCE.get());
            dropSelf(LFBlocks.FENCE_GATE.get());
            add(LFBlocks.DOOR.get(), createDoorTable(LFBlocks.DOOR.get()));
            dropSelf(LFBlocks.TRAPDOOR.get());
            dropSelf(LFBlocks.PRESSURE_PLATE.get());
            dropSelf(LFBlocks.BUTTON.get());

            // Nature / Underground
            add(LFBlocks.COAL_ORE.get(), createOreDrop(LFBlocks.COAL_ORE.get(), Items.COAL));
            dropSelf(LFBlocks.IRON_ORE.get());
            dropSelf(LFBlocks.GOLD_ORE.get());
            add(LFBlocks.REDSTONE_ORE.get(), createRedstoneOreDrops(LFBlocks.REDSTONE_ORE.get()));
            add(LFBlocks.DIAMOND_ORE.get(), createOreDrop(LFBlocks.DIAMOND_ORE.get(), Items.DIAMOND));
            add(LFBlocks.STONE.get(), createSingleItemTableWithSilkTouch(LFBlocks.STONE.get(), LFBlocks.COBBLESTONE.get()));

            // Building / Stone
            dropSelf(LFBlocks.STONE_STAIRS.get());
            dropSelf(LFBlocks.STONE_SLAB.get());
            dropSelf(LFBlocks.STONE_PRESSURE_PLATE.get());
            dropSelf(LFBlocks.STONE_BUTTON.get());
            dropSelf(LFBlocks.COBBLESTONE.get());
            dropSelf(LFBlocks.COBBLESTONE_STAIRS.get());
            dropSelf(LFBlocks.COBBLESTONE_SLAB.get());
            dropSelf(LFBlocks.COBBLESTONE_WALL.get());
            dropSelf(LFBlocks.MOSSY_COBBLESTONE.get());
            dropSelf(LFBlocks.MOSSY_COBBLESTONE_STAIRS.get());
            dropSelf(LFBlocks.MOSSY_COBBLESTONE_SLAB.get());
            dropSelf(LFBlocks.MOSSY_COBBLESTONE_WALL.get());
            dropSelf(LFBlocks.BRICKS.get());
            dropSelf(LFBlocks.BRICK_STAIRS.get());
            dropSelf(LFBlocks.BRICK_SLAB.get());
            dropSelf(LFBlocks.BRICK_WALL.get());

            // Building / Deco
            dropSelf(LFBlocks.OBSIDIAN.get());
            add(LFBlocks.GLASS.get(), createSilkTouchOnlyTable(LFBlocks.GLASS.get()));
            add(LFBlocks.GLASS_PANE.get(), createSilkTouchOnlyTable(LFBlocks.GLASS_PANE.get()));
            add(LFBlocks.BOOKSHELF.get(), createSilkTouchDispatchTable(LFBlocks.BOOKSHELF.get(),
                LootItem.lootTableItem(Items.BOOK)
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));
            dropSelf(LFBlocks.TNT.get());
            dropSelf(LFBlocks.IRON_BLOCK.get());
            dropSelf(LFBlocks.GOLD_BLOCK.get());
            dropSelf(LFBlocks.DIAMOND_BLOCK.get());

            // Building / Iron
            add(LFBlocks.IRON_DOOR.get(), createDoorTable(LFBlocks.IRON_DOOR.get()));

            // Deb
            dropSelf(LFBlocks.REMODEL_CRAFTING_TABLE.get());
            dropSelf(LFBlocks.REMODEL_FURNACE.get());
            dropSelf(LFBlocks.REMODEL_CHEST.get());
        }

        // this exact method exists on Forge, and is implemented via mixin by us on Fabric.
        // this makes it so any blocks in the environment that we don't datagen (e.g. Vanilla blocks) doesn't stop us from generating.
        // a similar thing needs to exist for any additional sub-providers later on.
        @Override
        public Iterable<Block> getKnownBlocks() {
            return Streams.stream(LFBlocks.REGISTRY).map(Supplier::get).toList();
        }
    }

    public static class LFChestLootProvider implements LootTableSubProvider {
        public static final ResourceKey<LootTable> HOUSES_LOOT_KEY = ResourceKey.create(Registries.LOOT_TABLE, LeftForgotten.asResource("chests/house"));
        public LFChestLootProvider(HolderLookup.Provider provider) {
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
            output.accept(HOUSES_LOOT_KEY, LootTable.lootTable()
                // Broken wooden pickaxe
                .withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.2f))
                    .add(LootItem.lootTableItem(Items.WOODEN_PICKAXE)
                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.5f, 0.95f)))))
                // Sticks
                .withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .setBonusRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.3f))
                    .add(LootItem.lootTableItem(Items.STICK)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 7)))))
                // Feathers
                .withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .setBonusRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.25f))
                    .add(LootItem.lootTableItem(Items.FEATHER)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
                // Bones
                .withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.25f))
                    .add(LootItem.lootTableItem(Items.BONE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
                // String
                .withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.3f))
                    .add(LootItem.lootTableItem(Items.STRING)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))))
                // Dirt
                .withPool(LootPool.lootPool()
                    .setRolls(UniformGenerator.between(0, 3))
                    .when(LootItemRandomChanceCondition.randomChance(0.3f))
                    .add(LootItem.lootTableItem(LFItems.DIRT.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 7)))))
                // Cobblestone
                .withPool(LootPool.lootPool()
                    .setRolls(UniformGenerator.between(0, 3))
                    .when(LootItemRandomChanceCondition.randomChance(0.3f))
                    .add(LootItem.lootTableItem(LFItems.COBBLESTONE.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5)))))
                // Wood
                .withPool(LootPool.lootPool()
                    .setRolls(UniformGenerator.between(0, 3))
                    .when(LootItemRandomChanceCondition.randomChance(0.4f))
                    .add(LootItem.lootTableItem(LFItems.WOOD.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))))
                // Wooden Planks
                .withPool(LootPool.lootPool()
                    .setRolls(UniformGenerator.between(0, 3))
                    .when(LootItemRandomChanceCondition.randomChance(0.4f))
                    .add(LootItem.lootTableItem(LFItems.WOODEN_PLANKS.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6)))))
            );
        }
    }
}