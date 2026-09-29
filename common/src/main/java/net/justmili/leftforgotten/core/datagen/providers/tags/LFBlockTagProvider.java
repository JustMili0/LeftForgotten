package net.justmili.leftforgotten.core.datagen.providers.tags;

import net.justmili.leftforgotten.core.references.LFBlockItemIds;
import net.justmili.leftforgotten.core.registries.TagRegistry;
import net.justmili.leftforgotten.libs.v1.references.BlockItemId;
import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.tags.BlockTags.*;

public class LFBlockTagProvider extends IntrinsicHolderTagsProvider<Block> {

    public LFBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.BLOCK, lookupProvider, block -> BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        add(LFBlockItemIds.BRITTLE_BEDROCK,
            DRAGON_IMMUNE,
            FEATURES_CANNOT_REPLACE,
            GEODE_INVALID_BLOCKS,
            INFINIBURN_END,
            LAVA_POOL_STONE_CANNOT_REPLACE,
            WITHER_IMMUNE
        );

        add(LFBlockItemIds.GRASS_BLOCK,
            ANIMALS_SPAWNABLE_ON,
            AZALEA_GROWS_ON,
            AZALEA_ROOT_REPLACEABLE,
            BAMBOO_PLANTABLE_ON,
            BIG_DRIPLEAF_PLACEABLE,
            DEAD_BUSH_MAY_PLACE_ON,
            DIRT,
            ENDERMAN_HOLDABLE,
            FROGS_SPAWNABLE_ON,
            FOXES_SPAWNABLE_ON,
            GOATS_SPAWNABLE_ON,
            LUSH_GROUND_REPLACEABLE,
            MINEABLE_WITH_SHOVEL,
            MOSS_REPLACEABLE,
            NETHER_CARVER_REPLACEABLES,
            OVERWORLD_CARVER_REPLACEABLES,
            PARROTS_SPAWNABLE_ON,
            RABBITS_SPAWNABLE_ON,
            SCULK_REPLACEABLE,
            SCULK_REPLACEABLE_WORLD_GEN,
            SNIFFER_DIGGABLE_BLOCK,
            VALID_SPAWN,
            WOLVES_SPAWNABLE_ON
        );
        add(LFBlockItemIds.DIRT,
            AZALEA_GROWS_ON,
            AZALEA_ROOT_REPLACEABLE,
            BAMBOO_PLANTABLE_ON,
            BIG_DRIPLEAF_PLACEABLE,
            CONVERTABLE_TO_MUD,
            DEAD_BUSH_MAY_PLACE_ON,
            DIRT,
            ENDERMAN_HOLDABLE,
            LUSH_GROUND_REPLACEABLE,
            MINEABLE_WITH_SHOVEL,
            MOSS_REPLACEABLE,
            NETHER_CARVER_REPLACEABLES,
            OVERWORLD_CARVER_REPLACEABLES,
            SCULK_REPLACEABLE,
            SCULK_REPLACEABLE_WORLD_GEN,
            SNIFFER_DIGGABLE_BLOCK
        );
        add(LFBlockItemIds.FARMLAND, BIG_DRIPLEAF_PLACEABLE, MINEABLE_WITH_SHOVEL);

        add(LFBlockItemIds.GRAVEL,
            c("gravel"),
            AZALEA_ROOT_REPLACEABLE,
            BAMBOO_PLANTABLE_ON,
            ENDERMAN_HOLDABLE,
            GOATS_SPAWNABLE_ON,
            LUSH_GROUND_REPLACEABLE,
            MINEABLE_WITH_SHOVEL,
            OVERWORLD_CARVER_REPLACEABLES,
            SCULK_REPLACEABLE,
            SCULK_REPLACEABLE_WORLD_GEN,
            TRAIL_RUINS_REPLACEABLE
        );
        add(LFBlockItemIds.SAND,
            c("colorless_sand"),
            c("sand"),
            AZALEA_GROWS_ON,
            AZALEA_ROOT_REPLACEABLE,
            BAMBOO_PLANTABLE_ON,
            DEAD_BUSH_MAY_PLACE_ON,
            ENDERMAN_HOLDABLE,
            LUSH_GROUND_REPLACEABLE,
            MINEABLE_WITH_SHOVEL,
            OVERWORLD_CARVER_REPLACEABLES,
            RABBITS_SPAWNABLE_ON,
            SAND,
            SCULK_REPLACEABLE,
            SCULK_REPLACEABLE_WORLD_GEN,
            SMELTS_TO_GLASS
        );
        add(LFBlockItemIds.CLAY,
            AXOLOTLS_SPAWNABLE_ON,
            AZALEA_ROOT_REPLACEABLE,
            BIG_DRIPLEAF_PLACEABLE,
            ENDERMAN_HOLDABLE,
            LUSH_GROUND_REPLACEABLE,
            MINEABLE_WITH_SHOVEL,
            SCULK_REPLACEABLE,
            SCULK_REPLACEABLE_WORLD_GEN,
            SMALL_DRIPLEAF_PLACEABLE
        );

        add(LFBlockItemIds.RED_FLOWER, ENDERMAN_HOLDABLE, FLOWERS, SMALL_FLOWERS, SWORD_EFFICIENT);
        add(LFBlockItemIds.YELLOW_FLOWER, ENDERMAN_HOLDABLE, FLOWERS, SMALL_FLOWERS, SWORD_EFFICIENT);
        add(LFBlockItemIds.RED_MUSHROOM, ENDERMAN_HOLDABLE, MINEABLE_WITH_AXE, SWORD_EFFICIENT);
        add(LFBlockItemIds.BROWN_MUSHROOM, ENDERMAN_HOLDABLE, MINEABLE_WITH_AXE, SWORD_EFFICIENT);
        add(LFBlockItemIds.CACTUS, ENDERMAN_HOLDABLE);
        add(LFBlockItemIds.SAPLING, MINEABLE_WITH_AXE, SAPLINGS, SWORD_EFFICIENT);
        add(LFBlockItemIds.LEAVES,
            COMPLETES_FIND_TREE_TUTORIAL,
            LAVA_POOL_STONE_CANNOT_REPLACE,
            LEAVES,
            MINEABLE_WITH_HOE,
            PARROTS_SPAWNABLE_ON,
            REPLACEABLE_BY_TREES,
            SWORD_EFFICIENT
        );

        add(LFBlockItemIds.WOOD,
            TagRegistry.ALPHA_NATURAL_LOGS,
            COMPLETES_FIND_TREE_TUTORIAL,
            LAVA_POOL_STONE_CANNOT_REPLACE,
            LOGS,
            LOGS_THAT_BURN,
            MINEABLE_WITH_AXE,
            OAK_LOGS,
            OVERWORLD_NATURAL_LOGS,
            PARROTS_SPAWNABLE_ON,
            SNAPS_GOAT_HORN
        );
        add(LFBlockItemIds.WOOD_6_SIDED,
            COMPLETES_FIND_TREE_TUTORIAL,
            LAVA_POOL_STONE_CANNOT_REPLACE,
            LOGS,
            LOGS_THAT_BURN,
            MINEABLE_WITH_AXE,
            OAK_LOGS,
            PARROTS_SPAWNABLE_ON
        );
        add(LFBlockItemIds.WOODEN_PLANKS, MINEABLE_WITH_AXE, PLANKS);
        add(LFBlockItemIds.WOODEN_STAIRS, MINEABLE_WITH_AXE, STAIRS, WOODEN_STAIRS);
        add(LFBlockItemIds.WOODEN_SLAB, MINEABLE_WITH_AXE, MINEABLE_WITH_PICKAXE /* intentional, see: old slabs */, SLABS, WOODEN_SLABS);
        add(LFBlockItemIds.FENCE, c(FENCES), c(WOODEN_FENCES), FENCES, MINEABLE_WITH_AXE, WOODEN_FENCES);
        add(LFBlockItemIds.FENCE_GATE, c(FENCE_GATES), c("wooden_fence_gates") /* no vanilla tag */, FENCE_GATES, MINEABLE_WITH_AXE, UNSTABLE_BOTTOM_CENTER);
        add(LFBlockItemIds.DOOR, DOORS, MINEABLE_WITH_AXE, WOODEN_DOORS);
        add(LFBlockItemIds.TRAPDOOR, MINEABLE_WITH_AXE, TRAPDOORS, WOODEN_TRAPDOORS);
        add(LFBlockItemIds.PRESSURE_PLATE, MINEABLE_WITH_AXE, PRESSURE_PLATES, WALL_POST_OVERRIDE, WOODEN_PRESSURE_PLATES);
        add(LFBlockItemIds.BUTTON, BUTTONS, MINEABLE_WITH_AXE, WOODEN_BUTTONS);

        add(LFBlockItemIds.STONE,
            c("ore_bearing_ground/stone"),
            c("stone"),
            AZALEA_ROOT_REPLACEABLE,
            BASE_STONE_OVERWORLD,
            DRIPSTONE_REPLACEABLE,
            GOATS_SPAWNABLE_ON,
            LUSH_GROUND_REPLACEABLE,
            MINEABLE_WITH_PICKAXE,
            MOSS_REPLACEABLE,
            NETHER_CARVER_REPLACEABLES,
            OVERWORLD_CARVER_REPLACEABLES,
            SCULK_REPLACEABLE,
            SCULK_REPLACEABLE_WORLD_GEN,
            SNAPS_GOAT_HORN,
            STONE_ORE_REPLACEABLES
        );
        add(LFBlockItemIds.STONE_STAIRS, MINEABLE_WITH_PICKAXE, STAIRS);
        add(LFBlockItemIds.STONE_SLAB, MINEABLE_WITH_PICKAXE, SLABS);
        add(LFBlockItemIds.STONE_PRESSURE_PLATE, MINEABLE_WITH_PICKAXE, PRESSURE_PLATES, STONE_PRESSURE_PLATES, WALL_POST_OVERRIDE);
        add(LFBlockItemIds.STONE_BUTTON, BUTTONS, MINEABLE_WITH_PICKAXE, STONE_BUTTONS);

        var cobble = c("cobblestone");
        add(LFBlockItemIds.COBBLESTONE, cobble, c("normal_cobblestone"), MINEABLE_WITH_PICKAXE);
        add(LFBlockItemIds.COBBLESTONE_STAIRS, MINEABLE_WITH_PICKAXE, STAIRS);
        add(LFBlockItemIds.COBBLESTONE_SLAB, MINEABLE_WITH_PICKAXE, SLABS);
        add(LFBlockItemIds.COBBLESTONE_WALL, MINEABLE_WITH_PICKAXE, WALLS);

        add(LFBlockItemIds.MOSSY_COBBLESTONE, cobble, c("mossy_cobblestone"), MINEABLE_WITH_PICKAXE);
        add(LFBlockItemIds.MOSSY_COBBLESTONE_STAIRS, MINEABLE_WITH_PICKAXE, STAIRS);
        add(LFBlockItemIds.MOSSY_COBBLESTONE_SLAB, MINEABLE_WITH_PICKAXE, SLABS);
        add(LFBlockItemIds.MOSSY_COBBLESTONE_WALL, MINEABLE_WITH_PICKAXE, WALLS);

        var ores = c("ores");
        var ores_in_stone = c("ores_in_ground/stone");
        var ores_singular = c("ore_rates/singular");
        var ores_dense = c("ore_rates/dense");
        add(LFBlockItemIds.COAL_ORE, c(COAL_ORES), ores_singular, ores, ores_in_stone, COAL_ORES, MINEABLE_WITH_PICKAXE, SNAPS_GOAT_HORN);
        add(LFBlockItemIds.IRON_ORE, c(IRON_ORES), ores_singular, ores, ores_in_stone, IRON_ORES, MINEABLE_WITH_PICKAXE, NEEDS_STONE_TOOL, OVERWORLD_CARVER_REPLACEABLES, SNAPS_GOAT_HORN);
        add(LFBlockItemIds.GOLD_ORE, c(GOLD_ORES), ores_singular, ores, ores_in_stone, GOLD_ORES, GUARDED_BY_PIGLINS, MINEABLE_WITH_PICKAXE, NEEDS_IRON_TOOL);
        add(LFBlockItemIds.REDSTONE_ORE, c(REDSTONE_ORES), ores_dense, ores, ores_in_stone, MINEABLE_WITH_PICKAXE, NEEDS_IRON_TOOL, REDSTONE_ORES);
        add(LFBlockItemIds.DIAMOND_ORE, c(DIAMOND_ORES), ores_singular, ores, ores_in_stone, DIAMOND_ORES, MINEABLE_WITH_PICKAXE, NEEDS_IRON_TOOL);

        add(LFBlockItemIds.BRICKS, MINEABLE_WITH_PICKAXE);
        add(LFBlockItemIds.BRICK_STAIRS, MINEABLE_WITH_PICKAXE, STAIRS);
        add(LFBlockItemIds.BRICK_SLAB, MINEABLE_WITH_PICKAXE, SLABS);
        add(LFBlockItemIds.BRICK_WALL, MINEABLE_WITH_PICKAXE, WALLS);

        var storage_blocks = c("storage_blocks");
        add(LFBlockItemIds.OBSIDIAN, c("obsidian"), DRAGON_IMMUNE, MINEABLE_WITH_PICKAXE, NEEDS_DIAMOND_TOOL);
        add(LFBlockItemIds.IRON_BLOCK, c("iron_blocks"), storage_blocks, BEACON_BASE_BLOCKS, MINEABLE_WITH_PICKAXE, NEEDS_STONE_TOOL);
        add(LFBlockItemIds.GOLD_BLOCK, c("gold_blocks"), storage_blocks, BEACON_BASE_BLOCKS, GUARDED_BY_PIGLINS, MINEABLE_WITH_PICKAXE, NEEDS_IRON_TOOL);
        add(LFBlockItemIds.DIAMOND_BLOCK, c("diamond_blocks"), storage_blocks, BEACON_BASE_BLOCKS, MINEABLE_WITH_PICKAXE, NEEDS_IRON_TOOL);

        add(LFBlockItemIds.BOOKSHELF, c("bookshelves"), ENCHANTMENT_POWER_PROVIDER, MINEABLE_WITH_AXE);
        add(LFBlockItemIds.GLASS, c("colorless_glass"), c("glass_blocks"), c("silica_glass"), IMPERMEABLE);
        add(LFBlockItemIds.GLASS_PANE, c("colorless_glass_panes"), c("glass_panes"));
        add(LFBlockItemIds.TNT, ENDERMAN_HOLDABLE);
        add(LFBlockItemIds.IRON_DOOR, DOORS, MINEABLE_WITH_PICKAXE);
    }

    @SafeVarargs
    private void add(BlockItemId id, TagKey<Block>... tags) {
        for (var tag : tags) {
            this.tag(tag).add(id.block());
        }
    }

    private static TagKey<Block> c(String path) { // Common
        return TagKey.create(Registries.BLOCK, ResourceUtil.asCommon(path));
    }

    private static TagKey<Block> c(TagKey<Block> tag) { // Common Dupe
        return TagKey.create(Registries.BLOCK, ResourceUtil.asCommon(tag.location().getPath()));
    }
}