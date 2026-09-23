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

        add(LFBlockItemIds.BRITTLE_BEDROCK, LAVA_POOL_STONE_CANNOT_REPLACE, FEATURES_CANNOT_REPLACE, INFINIBURN_END, DRAGON_IMMUNE, GEODE_INVALID_BLOCKS, WITHER_IMMUNE);

        add(LFBlockItemIds.GRASS_BLOCK,
            SNIFFER_DIGGABLE_BLOCK,
            RABBITS_SPAWNABLE_ON,
            WOLVES_SPAWNABLE_ON,
            PARROTS_SPAWNABLE_ON,
            OVERWORLD_CARVER_REPLACEABLES,
            BIG_DRIPLEAF_PLACEABLE,
            MOSS_REPLACEABLE,
            LUSH_GROUND_REPLACEABLE,
            MINEABLE_WITH_SHOVEL,
            VALID_SPAWN,
            SCULK_REPLACEABLE,
            GOATS_SPAWNABLE_ON,
            FROGS_SPAWNABLE_ON,
            BAMBOO_PLANTABLE_ON,
            SCULK_REPLACEABLE_WORLD_GEN,
            AZALEA_GROWS_ON,
            ANIMALS_SPAWNABLE_ON,
            NETHER_CARVER_REPLACEABLES,
            DIRT,
            FOXES_SPAWNABLE_ON,
            ENDERMAN_HOLDABLE,
            AZALEA_ROOT_REPLACEABLE,
            DEAD_BUSH_MAY_PLACE_ON
        );
        add(LFBlockItemIds.DIRT,
            CONVERTABLE_TO_MUD,
            DEAD_BUSH_MAY_PLACE_ON,
            NETHER_CARVER_REPLACEABLES,
            AZALEA_ROOT_REPLACEABLE,
            MOSS_REPLACEABLE,
            SNIFFER_DIGGABLE_BLOCK,
            SCULK_REPLACEABLE,
            OVERWORLD_CARVER_REPLACEABLES,
            BIG_DRIPLEAF_PLACEABLE,
            SCULK_REPLACEABLE_WORLD_GEN,
            LUSH_GROUND_REPLACEABLE,
            AZALEA_GROWS_ON,
            BAMBOO_PLANTABLE_ON,
            MINEABLE_WITH_SHOVEL,
            ENDERMAN_HOLDABLE
        );
        add(LFBlockItemIds.FARMLAND, BIG_DRIPLEAF_PLACEABLE, MINEABLE_WITH_SHOVEL);

        add(LFBlockItemIds.GRAVEL,
            LUSH_GROUND_REPLACEABLE,
            SCULK_REPLACEABLE,
            SCULK_REPLACEABLE_WORLD_GEN,
            c("gravel"),
            TRAIL_RUINS_REPLACEABLE,
            MINEABLE_WITH_SHOVEL,
            OVERWORLD_CARVER_REPLACEABLES,
            ENDERMAN_HOLDABLE,
            BAMBOO_PLANTABLE_ON,
            AZALEA_ROOT_REPLACEABLE,
            GOATS_SPAWNABLE_ON
        );
        add(LFBlockItemIds.SAND,
            LUSH_GROUND_REPLACEABLE,
            AZALEA_ROOT_REPLACEABLE,
            SMELTS_TO_GLASS,
            MINEABLE_WITH_SHOVEL,
            BAMBOO_PLANTABLE_ON,
            cd(SAND),
            SCULK_REPLACEABLE_WORLD_GEN,
            SCULK_REPLACEABLE,
            DEAD_BUSH_MAY_PLACE_ON,
            ENDERMAN_HOLDABLE,
            OVERWORLD_CARVER_REPLACEABLES,
            c("colorless_sand"),
            RABBITS_SPAWNABLE_ON,
            SAND
        );
        add(LFBlockItemIds.CLAY,
            AXOLOTLS_SPAWNABLE_ON,
            MINEABLE_WITH_SHOVEL,
            BIG_DRIPLEAF_PLACEABLE,
            SCULK_REPLACEABLE_WORLD_GEN,
            AZALEA_ROOT_REPLACEABLE,
            LUSH_GROUND_REPLACEABLE,
            ENDERMAN_HOLDABLE,
            SCULK_REPLACEABLE,
            SMALL_DRIPLEAF_PLACEABLE
        );

        add(LFBlockItemIds.RED_FLOWER, ENDERMAN_HOLDABLE, SWORD_EFFICIENT, FLOWERS, SMALL_FLOWERS);
        add(LFBlockItemIds.YELLOW_FLOWER, ENDERMAN_HOLDABLE, SWORD_EFFICIENT, FLOWERS, SMALL_FLOWERS);
        add(LFBlockItemIds.RED_MUSHROOM, MINEABLE_WITH_AXE, SWORD_EFFICIENT, ENDERMAN_HOLDABLE);
        add(LFBlockItemIds.BROWN_MUSHROOM, MINEABLE_WITH_AXE, SWORD_EFFICIENT, ENDERMAN_HOLDABLE);
        add(LFBlockItemIds.CACTUS, ENDERMAN_HOLDABLE);
        add(LFBlockItemIds.SAPLING, MINEABLE_WITH_AXE, SWORD_EFFICIENT, SAPLINGS);
        add(LFBlockItemIds.LEAVES,
            LAVA_POOL_STONE_CANNOT_REPLACE,
            PARROTS_SPAWNABLE_ON,
            MINEABLE_WITH_HOE,
            LEAVES,
            REPLACEABLE_BY_TREES,
            SWORD_EFFICIENT,
            COMPLETES_FIND_TREE_TUTORIAL
        );

        add(LFBlockItemIds.WOOD,
            LOGS_THAT_BURN,
            OVERWORLD_NATURAL_LOGS,
            TagRegistry.ALPHA_NATURAL_LOGS,
            SNAPS_GOAT_HORN,
            COMPLETES_FIND_TREE_TUTORIAL,
            LOGS,
            LAVA_POOL_STONE_CANNOT_REPLACE,
            PARROTS_SPAWNABLE_ON,
            MINEABLE_WITH_AXE,
            OAK_LOGS
        );
        add(LFBlockItemIds.WOOD_6_SIDED,
            LAVA_POOL_STONE_CANNOT_REPLACE,
            LOGS,
            LOGS_THAT_BURN,
            PARROTS_SPAWNABLE_ON,
            MINEABLE_WITH_AXE,
            COMPLETES_FIND_TREE_TUTORIAL,
            OAK_LOGS
        );
        add(LFBlockItemIds.WOODEN_PLANKS, PLANKS, MINEABLE_WITH_AXE);
        add(LFBlockItemIds.WOODEN_STAIRS, STAIRS, WOODEN_STAIRS, MINEABLE_WITH_AXE);
        add(LFBlockItemIds.WOODEN_SLAB, SLABS, WOODEN_SLABS, MINEABLE_WITH_AXE, MINEABLE_WITH_PICKAXE /* intentional, see: old slabs */);
        add(LFBlockItemIds.FENCE, MINEABLE_WITH_AXE, FENCES, WOODEN_FENCES, cd(FENCES), cd(WOODEN_FENCES));
        add(LFBlockItemIds.FENCE_GATE, UNSTABLE_BOTTOM_CENTER, FENCE_GATES, cd(FENCE_GATES), c("wooden_fence_gates") /* no vanilla tag */, MINEABLE_WITH_AXE);
        add(LFBlockItemIds.DOOR, MINEABLE_WITH_AXE, DOORS, WOODEN_DOORS);
        add(LFBlockItemIds.TRAPDOOR, MINEABLE_WITH_AXE, WOODEN_TRAPDOORS, TRAPDOORS);
        add(LFBlockItemIds.PRESSURE_PLATE, PRESSURE_PLATES, WALL_POST_OVERRIDE, WOODEN_PRESSURE_PLATES, MINEABLE_WITH_AXE);
        add(LFBlockItemIds.BUTTON, WOODEN_BUTTONS, BUTTONS, MINEABLE_WITH_AXE);

        add(LFBlockItemIds.STONE,
            MINEABLE_WITH_PICKAXE,
            LUSH_GROUND_REPLACEABLE,
            STONE_ORE_REPLACEABLES,
            DRIPSTONE_REPLACEABLE,
            GOATS_SPAWNABLE_ON,
            SNAPS_GOAT_HORN,
            NETHER_CARVER_REPLACEABLES,
            SCULK_REPLACEABLE_WORLD_GEN,
            SCULK_REPLACEABLE,
            c("ore_bearing_ground/stone"),
            MOSS_REPLACEABLE,
            BASE_STONE_OVERWORLD,
            c("stone"),
            OVERWORLD_CARVER_REPLACEABLES,
            AZALEA_ROOT_REPLACEABLE
        );
        add(LFBlockItemIds.STONE_STAIRS, MINEABLE_WITH_PICKAXE, STAIRS);
        add(LFBlockItemIds.STONE_SLAB, MINEABLE_WITH_PICKAXE, SLABS);
        add(LFBlockItemIds.STONE_PRESSURE_PLATE, STONE_PRESSURE_PLATES, PRESSURE_PLATES, WALL_POST_OVERRIDE, MINEABLE_WITH_PICKAXE);
        add(LFBlockItemIds.STONE_BUTTON, STONE_BUTTONS, BUTTONS, MINEABLE_WITH_PICKAXE);

        var cobble = c("cobblestone");
        add(LFBlockItemIds.COBBLESTONE, cobble, c("normal_cobblestone"), MINEABLE_WITH_PICKAXE);
        add(LFBlockItemIds.COBBLESTONE_STAIRS, MINEABLE_WITH_PICKAXE, STAIRS);
        add(LFBlockItemIds.COBBLESTONE_SLAB, MINEABLE_WITH_PICKAXE, SLABS);
        add(LFBlockItemIds.COBBLESTONE_WALL, WALLS, MINEABLE_WITH_PICKAXE);

        add(LFBlockItemIds.MOSSY_COBBLESTONE, cobble, c("mossy_cobblestone"), MINEABLE_WITH_PICKAXE);
        add(LFBlockItemIds.MOSSY_COBBLESTONE_STAIRS, MINEABLE_WITH_PICKAXE, STAIRS);
        add(LFBlockItemIds.MOSSY_COBBLESTONE_SLAB, MINEABLE_WITH_PICKAXE, SLABS);
        add(LFBlockItemIds.MOSSY_COBBLESTONE_WALL, WALLS, MINEABLE_WITH_PICKAXE);

        var ores = c("ores");
        var ores_in_stone = c("ores_in_ground/stone");
        var ores_singular = c("ore_rates/singular");
        var ores_dense = c("ore_rates/dense");
        add(LFBlockItemIds.COAL_ORE, ores_singular, COAL_ORES, cd(COAL_ORES), SNAPS_GOAT_HORN, ores_in_stone, ores, MINEABLE_WITH_PICKAXE);
        add(LFBlockItemIds.IRON_ORE,
            NEEDS_STONE_TOOL,
            ores_singular,
            SNAPS_GOAT_HORN,
            ores_in_stone,
            IRON_ORES,
            OVERWORLD_CARVER_REPLACEABLES,
            MINEABLE_WITH_PICKAXE,
            cd(IRON_ORES)
        );
        add(LFBlockItemIds.GOLD_ORE, cd(GOLD_ORES), ores, MINEABLE_WITH_PICKAXE, NEEDS_IRON_TOOL, GOLD_ORES, GUARDED_BY_PIGLINS, ores_in_stone);
        add(LFBlockItemIds.REDSTONE_ORE, ores_dense, ores_in_stone, ores, REDSTONE_ORES, MINEABLE_WITH_PICKAXE, cd(REDSTONE_ORES), NEEDS_IRON_TOOL);
        add(LFBlockItemIds.DIAMOND_ORE, ores_singular, DIAMOND_ORES, cd(DIAMOND_ORES), ores_in_stone, ores, MINEABLE_WITH_PICKAXE, NEEDS_IRON_TOOL);

        add(LFBlockItemIds.BRICKS, MINEABLE_WITH_PICKAXE);
        add(LFBlockItemIds.BRICK_STAIRS, MINEABLE_WITH_PICKAXE, STAIRS);
        add(LFBlockItemIds.BRICK_SLAB, MINEABLE_WITH_PICKAXE, SLABS);
        add(LFBlockItemIds.BRICK_WALL, MINEABLE_WITH_PICKAXE, WALLS);

        var storage_blocks = c("storage_blocks");
        add(LFBlockItemIds.OBSIDIAN, c("obsidian"), NEEDS_DIAMOND_TOOL, DRAGON_IMMUNE, MINEABLE_WITH_PICKAXE);
        add(LFBlockItemIds.IRON_BLOCK, BEACON_BASE_BLOCKS, storage_blocks, NEEDS_STONE_TOOL, c("iron_blocks"), MINEABLE_WITH_PICKAXE);
        add(LFBlockItemIds.GOLD_BLOCK, c("gold_blocks"), GUARDED_BY_PIGLINS, storage_blocks, NEEDS_IRON_TOOL, MINEABLE_WITH_PICKAXE, BEACON_BASE_BLOCKS);
        add(LFBlockItemIds.DIAMOND_BLOCK, BEACON_BASE_BLOCKS, storage_blocks, NEEDS_IRON_TOOL, MINEABLE_WITH_PICKAXE, c("diamond_blocks"));

        add(LFBlockItemIds.BOOKSHELF, ENCHANTMENT_POWER_PROVIDER, c("bookshelves"), MINEABLE_WITH_AXE);
        add(LFBlockItemIds.GLASS, IMPERMEABLE, c("silica_glass"), c("glass_blocks"), c("colorless_glass"));
        add(LFBlockItemIds.GLASS_PANE, c("glass_panes"), c("colorless_glass_panes"));
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

    private static TagKey<Block> cd(TagKey<Block> tag) { // Common Dupe
        return TagKey.create(Registries.BLOCK, ResourceUtil.asCommon(tag.location().getPath()));
    }
}