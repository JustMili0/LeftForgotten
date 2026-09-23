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
import net.minecraft.tags.ItemTags;
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

        add(LFBlockItemIds.DIRT, ItemTags.DIRT);
        add(LFBlockItemIds.GRAVEL, c("gravel"));
        add(LFBlockItemIds.SAND, cd(SAND), c("colorless_sand"), SAND, SMELTS_TO_GLASS);

        add(LFBlockItemIds.RED_FLOWER, FLOWERS, SMALL_FLOWERS);
        add(LFBlockItemIds.YELLOW_FLOWER, FLOWERS, SMALL_FLOWERS);
        add(LFBlockItemIds.RED_MUSHROOM, c("mushrooms"));
        add(LFBlockItemIds.BROWN_MUSHROOM, c("mushrooms"));
        add(LFBlockItemIds.SAPLING, SAPLINGS);
        add(LFBlockItemIds.LEAVES, COMPLETES_FIND_TREE_TUTORIAL, LEAVES);

        add(LFBlockItemIds.WOOD, COMPLETES_FIND_TREE_TUTORIAL, LOGS, LOGS_THAT_BURN, OAK_LOGS);
        add(LFBlockItemIds.WOOD_6_SIDED, COMPLETES_FIND_TREE_TUTORIAL, LOGS, LOGS_THAT_BURN, OAK_LOGS);
        add(LFBlockItemIds.WOODEN_PLANKS, PLANKS);
        add(LFBlockItemIds.WOODEN_STAIRS, WOODEN_STAIRS, STAIRS);
        add(LFBlockItemIds.WOODEN_SLAB, WOODEN_SLABS, SLABS);
        add(LFBlockItemIds.FENCE, cd(FENCES), cd(WOODEN_FENCES), WOODEN_FENCES, FENCES);
        add(LFBlockItemIds.FENCE_GATE, cd(FENCE_GATES), c("wooden_fence_gates") /* no vanilla tag */, FENCE_GATES);
        add(LFBlockItemIds.DOOR, DOORS, WOODEN_DOORS);
        add(LFBlockItemIds.TRAPDOOR, TRAPDOORS, WOODEN_TRAPDOORS);
        add(LFBlockItemIds.PRESSURE_PLATE, WOODEN_PRESSURE_PLATES);
        add(LFBlockItemIds.BUTTON, BUTTONS, WOODEN_BUTTONS);

        add(LFBlockItemIds.STONE, c("ore_bearing_ground/stone"), c("stone"));
        add(LFBlockItemIds.STONE_STAIRS, STAIRS);
        add(LFBlockItemIds.STONE_SLAB, SLABS);
        add(LFBlockItemIds.STONE_BUTTON, BUTTONS, STONE_BUTTONS);

        var cobble = c("cobblestone");
        add(LFBlockItemIds.COBBLESTONE, cobble, c("normal_cobblestone"), STONE_CRAFTING_MATERIALS, STONE_TOOL_MATERIALS);
        add(LFBlockItemIds.COBBLESTONE_STAIRS, STAIRS);
        add(LFBlockItemIds.COBBLESTONE_SLAB, SLABS);
        add(LFBlockItemIds.COBBLESTONE_WALL, WALLS);

        add(LFBlockItemIds.MOSSY_COBBLESTONE, cobble, c("mossy_cobblestone"));
        add(LFBlockItemIds.MOSSY_COBBLESTONE_STAIRS, STAIRS);
        add(LFBlockItemIds.MOSSY_COBBLESTONE_SLAB, SLABS);
        add(LFBlockItemIds.MOSSY_COBBLESTONE_WALL, WALLS);

        var ores = c("ores");
        var ores_in_stone = c("ores_in_ground/stone");
        var ores_singular = c("ore_rates/singular");
        var ores_dense = c("ore_rates/dense");
        add(LFBlockItemIds.COAL_ORE, COAL_ORES, cd(COAL_ORES), ores, ores_singular, ores_in_stone);
        add(LFBlockItemIds.IRON_ORE, IRON_ORES, cd(IRON_ORES), ores, ores_singular, ores_in_stone);
        add(LFBlockItemIds.GOLD_ORE, GOLD_ORES, cd(GOLD_ORES), ores, ores_singular, ores_in_stone, PIGLIN_LOVED);
        add(LFBlockItemIds.REDSTONE_ORE, REDSTONE_ORES, cd(REDSTONE_ORES), ores, ores_dense, ores_in_stone);
        add(LFBlockItemIds.DIAMOND_ORE, DIAMOND_ORES, cd(DIAMOND_ORES), ores, ores_singular, ores_in_stone);

        add(LFBlockItemIds.BRICK_STAIRS, STAIRS);
        add(LFBlockItemIds.BRICK_SLAB, SLABS);
        add(LFBlockItemIds.BRICK_WALL, WALLS);

        add(LFBlockItemIds.OBSIDIAN, c("obsidian"));
        add(LFBlockItemIds.IRON_BLOCK, c("iron_blocks"), c("storage_blocks"));
        add(LFBlockItemIds.GOLD_BLOCK, c("gold_blocks"), c("storage_blocks"), PIGLIN_LOVED);
        add(LFBlockItemIds.DIAMOND_BLOCK, c("diamond_blocks"), c("storage_blocks"));

        add(LFBlockItemIds.BOOKSHELF, c("bookshelves"));
        add(LFBlockItemIds.GLASS, c("glass_blocks"), c("colorless_glass"), c("silica_glass"));
        add(LFBlockItemIds.GLASS_PANE, c("glass_panes"), c("colorless_glass_panes"));
        add(LFBlockItemIds.IRON_DOOR, DOORS);

        add(LFItemIds.BOAT, BOATS);
        add(LFItemIds.BRICK, DECORATED_POT_INGREDIENTS, c("brick_ingots"), c("ingots"));
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

    private static TagKey<Item> cd(TagKey<Item> tag) { // Common Dupe
        return TagKey.create(Registries.ITEM, ResourceUtil.asCommon(tag.location().getPath()));
    }
}