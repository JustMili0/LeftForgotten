package net.justmili.leftforgotten.datagen.providers;

import com.google.common.collect.Streams;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.registries.BlockRegistry;
import net.justmili.leftforgotten.registries.ItemRegistry;
import net.justmili.leftforgotten.libs.v1.utils.common.datagen.extensions.KnownBlocksLootProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
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
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class LFLootTableProvider extends LootTableProvider {
    public LFLootTableProvider(PackOutput output) {
        super(output, Set.of(), List.of(
            new SubProviderEntry(LFBlockLootProvider::new, LootContextParamSets.BLOCK),
            new SubProviderEntry(LFChestLootProvider::new, LootContextParamSets.CHEST)
        ));
    }

    public static class LFBlockLootProvider extends BlockLootSubProvider implements KnownBlocksLootProvider {
        protected LFBlockLootProvider() {
            super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
        }

        @Override
        public void generate() {

            // Nature / Ground
            add(BlockRegistry.GRASS_BLOCK.get(), createSingleItemTableWithSilkTouch(BlockRegistry.GRASS_BLOCK.get(), BlockRegistry.DIRT.get()));
            dropSelf(BlockRegistry.DIRT.get());
            add(BlockRegistry.FARMLAND.get(), createSingleItemTableWithSilkTouch(BlockRegistry.FARMLAND.get(), BlockRegistry.DIRT.get()));
            add(BlockRegistry.GRAVEL.get(), createSilkTouchDispatchTable(BlockRegistry.GRAVEL.get(),
                LootItem.lootTableItem(Items.FLINT)
                    .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F))
                    .otherwise(LootItem.lootTableItem(BlockRegistry.GRAVEL.get()))));
            dropSelf(BlockRegistry.SAND.get());
            add(BlockRegistry.CLAY.get(), createSilkTouchDispatchTable(BlockRegistry.CLAY.get(),
                LootItem.lootTableItem(ItemRegistry.CLAY_BALL.get())
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4)))));

            // Nature / Vegetation
            dropSelf(BlockRegistry.RED_FLOWER.get());
            dropSelf(BlockRegistry.YELLOW_FLOWER.get());
            dropSelf(BlockRegistry.RED_MUSHROOM.get());
            dropSelf(BlockRegistry.BROWN_MUSHROOM.get());
            dropSelf(BlockRegistry.CACTUS.get());
            dropSelf(BlockRegistry.SAPLING.get());
            add(BlockRegistry.LEAVES.get(), createLeavesDrops(BlockRegistry.LEAVES.get(), BlockRegistry.SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

            // Building / Wood
            dropSelf(BlockRegistry.WOOD.get());
            dropSelf(BlockRegistry.WOOD_6_SIDED.get());
            dropSelf(BlockRegistry.WOODEN_PLANKS.get());
            dropSelf(BlockRegistry.WOODEN_STAIRS.get());
            dropSelf(BlockRegistry.WOODEN_SLAB.get());
            dropSelf(BlockRegistry.FENCE.get());
            dropSelf(BlockRegistry.FENCE_GATE.get());
            add(BlockRegistry.DOOR.get(), createDoorTable(BlockRegistry.DOOR.get()));
            dropSelf(BlockRegistry.TRAPDOOR.get());
            dropSelf(BlockRegistry.PRESSURE_PLATE.get());
            dropSelf(BlockRegistry.BUTTON.get());

            // Nature / Underground
            add(BlockRegistry.COAL_ORE.get(), createOreDrop(BlockRegistry.COAL_ORE.get(), Items.COAL));
            dropSelf(BlockRegistry.IRON_ORE.get());
            dropSelf(BlockRegistry.GOLD_ORE.get());
            add(BlockRegistry.REDSTONE_ORE.get(), createRedstoneOreDrops(BlockRegistry.REDSTONE_ORE.get()));
            add(BlockRegistry.DIAMOND_ORE.get(), createOreDrop(BlockRegistry.DIAMOND_ORE.get(), Items.DIAMOND));
            add(BlockRegistry.STONE.get(), createSingleItemTableWithSilkTouch(BlockRegistry.STONE.get(), BlockRegistry.COBBLESTONE.get()));

            // Building / Stone
            dropSelf(BlockRegistry.STONE_STAIRS.get());
            dropSelf(BlockRegistry.STONE_SLAB.get());
            dropSelf(BlockRegistry.STONE_PRESSURE_PLATE.get());
            dropSelf(BlockRegistry.STONE_BUTTON.get());
            dropSelf(BlockRegistry.COBBLESTONE.get());
            dropSelf(BlockRegistry.COBBLESTONE_STAIRS.get());
            dropSelf(BlockRegistry.COBBLESTONE_SLAB.get());
            dropSelf(BlockRegistry.COBBLESTONE_WALL.get());
            dropSelf(BlockRegistry.MOSSY_COBBLESTONE.get());
            dropSelf(BlockRegistry.MOSSY_COBBLESTONE_STAIRS.get());
            dropSelf(BlockRegistry.MOSSY_COBBLESTONE_SLAB.get());
            dropSelf(BlockRegistry.MOSSY_COBBLESTONE_WALL.get());
            dropSelf(BlockRegistry.BRICKS.get());
            dropSelf(BlockRegistry.BRICK_STAIRS.get());
            dropSelf(BlockRegistry.BRICK_SLAB.get());
            dropSelf(BlockRegistry.BRICK_WALL.get());

            // Building / Deco
            dropSelf(BlockRegistry.OBSIDIAN.get());
            add(BlockRegistry.GLASS.get(), createSilkTouchOnlyTable(BlockRegistry.GLASS.get()));
            add(BlockRegistry.GLASS_PANE.get(), createSilkTouchOnlyTable(BlockRegistry.GLASS_PANE.get()));
            add(BlockRegistry.BOOKSHELF.get(), createSilkTouchDispatchTable(BlockRegistry.BOOKSHELF.get(),
                LootItem.lootTableItem(Items.BOOK)
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));
            dropSelf(BlockRegistry.TNT.get());
            dropSelf(BlockRegistry.IRON_BLOCK.get());
            dropSelf(BlockRegistry.GOLD_BLOCK.get());
            dropSelf(BlockRegistry.DIAMOND_BLOCK.get());

            // Building / Iron
            add(BlockRegistry.IRON_DOOR.get(), createDoorTable(BlockRegistry.IRON_DOOR.get()));

            // Deb
            dropSelf(BlockRegistry.REMODEL_CRAFTING_TABLE.get());
            dropSelf(BlockRegistry.REMODEL_FURNACE.get());
            dropSelf(BlockRegistry.REMODEL_CHEST.get());
        }

        // this exact method exists on Forge, and is implemented via mixin by us on Fabric.
        // this makes it so any blocks in the environment that we don't datagen (e.g. Vanilla blocks) doesn't stop us from generating.
        // a similar thing needs to exist for any additional sub-providers later on.
        @Override
        public Iterable<Block> getKnownBlocks() {
            return Streams.stream(BlockRegistry.REGISTRY).map(Supplier::get).toList();
        }
    }

    public static class LFChestLootProvider implements LootTableSubProvider {
        protected LFChestLootProvider() {
        }

        @Override
        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> output) {
            output.accept(LeftForgotten.asResource("chests/house"), LootTable.lootTable()
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
                    .add(LootItem.lootTableItem(ItemRegistry.DIRT.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 7)))))
                // Cobblestone
                .withPool(LootPool.lootPool()
                    .setRolls(UniformGenerator.between(0, 3))
                    .when(LootItemRandomChanceCondition.randomChance(0.3f))
                    .add(LootItem.lootTableItem(ItemRegistry.COBBLESTONE.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5)))))
                // Wood
                .withPool(LootPool.lootPool()
                    .setRolls(UniformGenerator.between(0, 3))
                    .when(LootItemRandomChanceCondition.randomChance(0.4f))
                    .add(LootItem.lootTableItem(ItemRegistry.WOOD.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))))
                // Wooden Planks
                .withPool(LootPool.lootPool()
                    .setRolls(UniformGenerator.between(0, 3))
                    .when(LootItemRandomChanceCondition.randomChance(0.4f))
                    .add(LootItem.lootTableItem(ItemRegistry.WOODEN_PLANKS.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6)))))
            );
        }
    }
}