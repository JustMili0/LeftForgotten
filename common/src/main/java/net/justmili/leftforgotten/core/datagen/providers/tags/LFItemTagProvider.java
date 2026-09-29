package net.justmili.leftforgotten.core.datagen.providers.tags;

import net.justmili.leftforgotten.core.references.LFBlockItemIds;
import net.justmili.leftforgotten.core.references.LFItemIds;
import net.justmili.leftforgotten.libs.v1.references.BlockItemId;
import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.tags.ItemTags.*;

public class LFItemTagProvider extends IntrinsicHolderTagsProvider<Item> {

    public LFItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.ITEM, lookupProvider, item -> BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        add(LFBlockItemIds.GRASS_BLOCK, DIRT);
        add(LFBlockItemIds.DIRT, DIRT);
        add(LFBlockItemIds.GRAVEL, c("gravels"));
        add(LFBlockItemIds.SAND, c("sands"), c("sands/colorless"), SAND, SMELTS_TO_GLASS);

        var animal_foods = c("animal_foods");
        add(LFBlockItemIds.RED_FLOWER, animal_foods, BEE_FOOD, FLOWERS, SMALL_FLOWERS);
        add(LFBlockItemIds.YELLOW_FLOWER, animal_foods, BEE_FOOD, FLOWERS, RABBIT_FOOD, SMALL_FLOWERS);
        add(LFBlockItemIds.RED_MUSHROOM, c("mushrooms"));
        add(LFBlockItemIds.BROWN_MUSHROOM, c("mushrooms"));
        add(LFBlockItemIds.CACTUS, animal_foods, c("crops"), c("crops/cactus"), CAMEL_FOOD);
        add(LFBlockItemIds.SAPLING, SAPLINGS);
        add(LFBlockItemIds.LEAVES, COMPLETES_FIND_TREE_TUTORIAL, LEAVES);

        add(LFBlockItemIds.WOOD, COMPLETES_FIND_TREE_TUTORIAL, LOGS, LOGS_THAT_BURN, OAK_LOGS);
        add(LFBlockItemIds.WOOD_6_SIDED, COMPLETES_FIND_TREE_TUTORIAL, LOGS, LOGS_THAT_BURN, OAK_LOGS);
        add(LFBlockItemIds.WOODEN_PLANKS, PLANKS);
        add(LFBlockItemIds.WOODEN_STAIRS, STAIRS, WOODEN_STAIRS);
        add(LFBlockItemIds.WOODEN_SLAB, SLABS, WOODEN_SLABS);
        add(LFBlockItemIds.FENCE, c(FENCES), c("fences/wooden"), FENCES, WOODEN_FENCES);
        add(LFBlockItemIds.FENCE_GATE, c(FENCE_GATES), c("fence_gates/wooden") /* no vanilla tag */, FENCE_GATES);
        add(LFBlockItemIds.DOOR, DOORS, WOODEN_DOORS);
        add(LFBlockItemIds.TRAPDOOR, TRAPDOORS, WOODEN_TRAPDOORS);
        add(LFBlockItemIds.PRESSURE_PLATE, WOODEN_PRESSURE_PLATES);
        add(LFBlockItemIds.BUTTON, BUTTONS, WOODEN_BUTTONS);

        add(LFBlockItemIds.STONE, c("ore_bearing_ground/stone"), c("stones"));
        add(LFBlockItemIds.STONE_STAIRS, STAIRS);
        add(LFBlockItemIds.STONE_SLAB, SLABS);
        add(LFBlockItemIds.STONE_BUTTON, BUTTONS, STONE_BUTTONS);

        var cobble = c("cobblestones");
        add(LFBlockItemIds.COBBLESTONE, cobble, c("cobblestones/normal"), STONE_CRAFTING_MATERIALS, STONE_TOOL_MATERIALS);
        add(LFBlockItemIds.COBBLESTONE_STAIRS, STAIRS);
        add(LFBlockItemIds.COBBLESTONE_SLAB, SLABS);
        add(LFBlockItemIds.COBBLESTONE_WALL, WALLS);

        add(LFBlockItemIds.MOSSY_COBBLESTONE, cobble, c("cobblestones/mossy"));
        add(LFBlockItemIds.MOSSY_COBBLESTONE_STAIRS, STAIRS);
        add(LFBlockItemIds.MOSSY_COBBLESTONE_SLAB, SLABS);
        add(LFBlockItemIds.MOSSY_COBBLESTONE_WALL, WALLS);

        var ores = c("ores");
        var ores_in_stone = c("ores_in_ground/stone");
        var ores_singular = c("ore_rates/singular");
        var ores_dense = c("ore_rates/dense");
        add(LFBlockItemIds.COAL_ORE, c(COAL_ORES), ores_singular, ores, ores_in_stone, COAL_ORES);
        add(LFBlockItemIds.IRON_ORE, c(IRON_ORES), ores_singular, ores, ores_in_stone, IRON_ORES);
        add(LFBlockItemIds.GOLD_ORE, c(GOLD_ORES), ores_singular, ores, ores_in_stone, GOLD_ORES, PIGLIN_LOVED);
        add(LFBlockItemIds.REDSTONE_ORE, c(REDSTONE_ORES),ores_dense, ores, ores_in_stone, REDSTONE_ORES);
        add(LFBlockItemIds.DIAMOND_ORE, c(DIAMOND_ORES), ores_singular, ores, ores_in_stone, DIAMOND_ORES);

        add(LFBlockItemIds.BRICK_STAIRS, STAIRS);
        add(LFBlockItemIds.BRICK_SLAB, SLABS);
        add(LFBlockItemIds.BRICK_WALL, WALLS);

        var storage_blocks = c("storage_blocks");
        add(LFBlockItemIds.OBSIDIAN, c("obsidian"));
        add(LFBlockItemIds.IRON_BLOCK, c("iron_blocks"), storage_blocks);
        add(LFBlockItemIds.GOLD_BLOCK, c("gold_blocks"), storage_blocks, PIGLIN_LOVED);
        add(LFBlockItemIds.DIAMOND_BLOCK, c("diamond_blocks"), storage_blocks);

        add(LFBlockItemIds.BOOKSHELF, c("bookshelves"));
        add(LFBlockItemIds.GLASS, c("colorless_glass"), c("glass_blocks"), c("silica_glass"));
        add(LFBlockItemIds.GLASS_PANE, c("colorless_glass_panes"), c("glass_panes"));
        add(LFBlockItemIds.IRON_DOOR, DOORS);

        add(LFItemIds.BOAT, BOATS);
        add(LFItemIds.BRICK, c("brick_ingots"), c("ingots"), DECORATED_POT_INGREDIENTS);
    }

    @SafeVarargs
    private void add(ResourceKey<Item> id, TagKey<Item>... tags) {
        for (var tag : tags) {
            this.tag(tag).add(id);
        }
    }

    @SafeVarargs
    private void add(BlockItemId id, TagKey<Item>... tags) {
        for (var tag : tags) {
            this.tag(tag).add(id.item());
        }
    }

    private static TagKey<Item> c(String path) { // Common
        return TagKey.create(Registries.ITEM, ResourceUtil.asCommon(path));
    }

    private static TagKey<Item> c(TagKey<Item> tag) { // Common Dupe
        return TagKey.create(Registries.ITEM, ResourceUtil.asCommon(tag.location().getPath()));
    }
}