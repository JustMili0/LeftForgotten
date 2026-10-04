package net.justmili.leftforgotten.core.datagen.providers;

import com.google.common.collect.Streams;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.justmili.leftforgotten.core.registries.ItemRegistry;
import net.justmili.leftforgotten.libs.v1.utils.common.datagen.extensions.KnownBlocksLootProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class LFLootTableProvider extends LootTableProvider {
    public LFLootTableProvider(PackOutput output) {
        super(output, Set.of(), List.of(
            new SubProviderEntry(LFBlockLootProvider::new, LootContextParamSets.BLOCK)
        ));
    }

    public static class LFBlockLootProvider extends BlockLootSubProvider implements KnownBlocksLootProvider {
        protected LFBlockLootProvider() {
            super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
        }

        @Override
        public void generate() {

            // Nature / Ground
            silkOrElse(BlockRegistry.GRASS_BLOCK, BlockRegistry.DIRT);
            self(BlockRegistry.DIRT);
            other(BlockRegistry.FARMLAND, BlockRegistry.DIRT);
            var gravel = BlockRegistry.GRAVEL;
            loot(gravel, createSilkTouchDispatchTable(gravel.get(), this.applyExplosionCondition(gravel.get(), LootItem.lootTableItem(Items.FLINT)
                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.1f, 0.14285715f, 0.25f, 1f))
                .otherwise(LootItem.lootTableItem(gravel.get()))
            )));
            self(BlockRegistry.SAND);
            silkOrElse(BlockRegistry.CLAY, ItemRegistry.CLAY_BALL.get(), ConstantValue.exactly(4));

            // Nature / Vegetation
            self(BlockRegistry.RED_FLOWER);
            self(BlockRegistry.YELLOW_FLOWER);
            self(BlockRegistry.RED_MUSHROOM);
            self(BlockRegistry.BROWN_MUSHROOM);
            self(BlockRegistry.CACTUS);
            self(BlockRegistry.SAPLING);
            loot(BlockRegistry.LEAVES, createLeavesDrops(BlockRegistry.LEAVES.get(), BlockRegistry.SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

            // Building / Wood
            self(BlockRegistry.WOOD);
            self(BlockRegistry.WOOD_6_SIDED);
            self(BlockRegistry.WOODEN_PLANKS);
            self(BlockRegistry.WOODEN_STAIRS);
            self(BlockRegistry.WOODEN_SLAB);
            self(BlockRegistry.FENCE);
            self(BlockRegistry.FENCE_GATE);
            door(BlockRegistry.DOOR);
            self(BlockRegistry.TRAPDOOR);
            self(BlockRegistry.PRESSURE_PLATE);
            self(BlockRegistry.BUTTON);

            // Nature / Underground
            ore(BlockRegistry.COAL_ORE, Items.COAL);
            self(BlockRegistry.IRON_ORE);
            self(BlockRegistry.GOLD_ORE);
            loot(BlockRegistry.REDSTONE_ORE, createRedstoneOreDrops(BlockRegistry.REDSTONE_ORE.get()));
            ore(BlockRegistry.DIAMOND_ORE, Items.DIAMOND);
            silkOrElse(BlockRegistry.STONE, BlockRegistry.COBBLESTONE);

            // Building / Stone
            self(BlockRegistry.STONE_STAIRS);
            self(BlockRegistry.STONE_SLAB);
            self(BlockRegistry.STONE_PRESSURE_PLATE);
            self(BlockRegistry.STONE_BUTTON);
            self(BlockRegistry.COBBLESTONE);
            self(BlockRegistry.COBBLESTONE_STAIRS);
            self(BlockRegistry.COBBLESTONE_SLAB);
            self(BlockRegistry.COBBLESTONE_WALL);
            self(BlockRegistry.MOSSY_COBBLESTONE);
            self(BlockRegistry.MOSSY_COBBLESTONE_STAIRS);
            self(BlockRegistry.MOSSY_COBBLESTONE_SLAB);
            self(BlockRegistry.MOSSY_COBBLESTONE_WALL);
            self(BlockRegistry.BRICKS);
            self(BlockRegistry.BRICK_STAIRS);
            self(BlockRegistry.BRICK_SLAB);
            self(BlockRegistry.BRICK_WALL);

            // Building / Deco
            self(BlockRegistry.OBSIDIAN);
            silkOnly(BlockRegistry.GLASS);
            silkOnly(BlockRegistry.GLASS_PANE);
            silkOrElse(BlockRegistry.BOOKSHELF, Items.BOOK, ConstantValue.exactly(3));
            self(BlockRegistry.TNT);
            self(BlockRegistry.IRON_BLOCK);
            self(BlockRegistry.GOLD_BLOCK);
            self(BlockRegistry.DIAMOND_BLOCK);

            // Building / Iron
            door(BlockRegistry.IRON_DOOR);

            // Dev.. dev blocks don't have loot tables
        }

        // this exact method exists on Forge, and is implemented via mixin by us on Fabric.
        // this makes it so any blocks in the environment that we don't datagen (e.g. Vanilla blocks) doesn't stop us from generating.
        // a similar thing needs to exist for any additional sub-providers later on.
        @Override
        public Iterable<Block> getKnownBlocks() {
            return Streams.stream(BlockRegistry.REGISTRY).map(Supplier::get).toList();
        }

        private void loot(RegistrySupplier<Block> block, LootTable.Builder builder) {
            this.add(block.get(), builder);
        }

        private void self(RegistrySupplier<Block> block) {
            this.dropSelf(block.get());
        }

        private void other(RegistrySupplier<Block> block, RegistrySupplier<Block> other) {
            this.dropOther(block.get(), other.get());
        }

        private void silkOnly(RegistrySupplier<Block> block) {
            loot(block, createSilkTouchOnlyTable(block.get()));
        }

        private void silkOrElse(RegistrySupplier<Block> block, RegistrySupplier<Block> other) {
            loot(block, createSingleItemTableWithSilkTouch(block.get(), other.get()));
        }

        private void silkOrElse(RegistrySupplier<Block> block, Item other, ConstantValue otherDrop) {
            loot(block, createSingleItemTableWithSilkTouch(block.get(), other, otherDrop));
        }

        private void ore(RegistrySupplier<Block> block, Item other) {
            loot(block, createOreDrop(block.get(), other));
        }

        private void door(RegistrySupplier<Block> block) {
            loot(block, createDoorTable(block.get()));
        }
    }
}