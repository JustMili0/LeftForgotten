package net.justmili.leftforgotten.core.datagen;

import net.justmili.leftforgotten.core.util.ResourceUtil;
import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class LFBlockTagProvider extends IntrinsicHolderTagsProvider<Block> {
    public static final TagKey<Block> FORGE_COBBLE = TagKey.create(Registries.BLOCK, ResourceUtil.asForge("cobblestone"));
    public static final TagKey<Block> FORGE_STONE = TagKey.create(Registries.BLOCK, ResourceUtil.asForge("stone"));

    public LFBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.BLOCK, lookupProvider, block -> BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        /**
         * Forge and Fabric tags
         */
        // FORGE
        this.tag(FORGE_COBBLE)
            .add(LFBlocks.COBBLESTONE.get());
        this.tag(FORGE_STONE)
            .add(LFBlocks.STONE.get());
        // FABRIC
        // idk does Fabric have additional tags like forge

        /**
         * ADDS TO VANILLA TAGS
         */
        this.tag(BlockTags.SMALL_FLOWERS)
            .add(LFBlocks.RED_FLOWER.get(), LFBlocks.YELLOW_FLOWER.get());

        this.tag(BlockTags.STONE_ORE_REPLACEABLES)
            .add(LFBlocks.STONE.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(LFBlocks.COBBLESTONE.get(), LFBlocks.COBBLESTONE_WALL.get(), LFBlocks.COBBLESTONE_STAIRS.get(),
                LFBlocks.COBBLESTONE_SLAB.get(),
                LFBlocks.STONE.get(), LFBlocks.STONE_SLAB.get(), LFBlocks.STONE_STAIRS.get(),
                LFBlocks.STONE_PRESSURE_PLATE.get(), LFBlocks.STONE_BUTTON.get(),
                LFBlocks.COAL_ORE.get(), LFBlocks.IRON_ORE.get(), LFBlocks.GOLD_ORE.get(), LFBlocks.DIAMOND_ORE.get(), LFBlocks.REDSTONE_ORE.get(),
                LFBlocks.OBSIDIAN.get(), LFBlocks.BRICKS.get(), LFBlocks.BRICK_STAIRS.get(), LFBlocks.BRICK_SLAB.get(), LFBlocks.BRICK_WALL.get(),
                LFBlocks.IRON_DOOR.get(),
                LFBlocks.MOSSY_COBBLESTONE.get(), LFBlocks.MOSSY_COBBLESTONE_STAIRS.get(), LFBlocks.MOSSY_COBBLESTONE_SLAB.get(), LFBlocks.MOSSY_COBBLESTONE_WALL.get(),
                LFBlocks.GLASS.get(), LFBlocks.GLASS_PANE.get(),
                LFBlocks.WOODEN_SLAB.get(), // intentional, see: old slabs
                LFBlocks.IRON_BLOCK.get(),
                LFBlocks.GOLD_BLOCK.get(),
                LFBlocks.DIAMOND_BLOCK.get()
            );

        this.tag(BlockTags.NEEDS_STONE_TOOL)
            .add(LFBlocks.IRON_ORE.get(),
                LFBlocks.IRON_BLOCK.get()
            );

        this.tag(BlockTags.NEEDS_IRON_TOOL)
            .add(LFBlocks.GOLD_ORE.get(),
                LFBlocks.DIAMOND_ORE.get(),
                LFBlocks.REDSTONE_ORE.get(),
                LFBlocks.GOLD_BLOCK.get(),
                LFBlocks.DIAMOND_BLOCK.get()
            );

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(LFBlocks.OBSIDIAN.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
            .add(LFBlocks.WOODEN_PLANKS.get(), LFBlocks.WOOD.get(), LFBlocks.WOOD_6_SIDED.get(),
                LFBlocks.WOODEN_STAIRS.get(), LFBlocks.WOODEN_SLAB.get(),
                LFBlocks.FENCE.get(), LFBlocks.FENCE_GATE.get(),
                LFBlocks.DOOR.get(), LFBlocks.TRAPDOOR.get(),
                LFBlocks.PRESSURE_PLATE.get(), LFBlocks.BUTTON.get(),
                LFBlocks.BOOKSHELF.get()
            );

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
            .add(LFBlocks.GRASS_BLOCK.get(), LFBlocks.DIRT.get(), LFBlocks.FARMLAND.get(),
                LFBlocks.SAND.get(), LFBlocks.GRAVEL.get(), LFBlocks.CLAY.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE)
            .add(LFBlocks.LEAVES.get());

        this.tag(BlockTags.ANIMALS_SPAWNABLE_ON)
            .add(LFBlocks.GRASS_BLOCK.get());

        this.tag(BlockTags.DIRT)
            .add(LFBlocks.GRASS_BLOCK.get(), LFBlocks.DIRT.get());

        this.tag(BlockTags.SAND)
            .add(LFBlocks.SAND.get());

        this.tag(BlockTags.LEAVES)
            .add(LFBlocks.LEAVES.get());

        this.tag(BlockTags.LOGS)
            .add(LFBlocks.WOOD.get(), LFBlocks.WOOD_6_SIDED.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
            .add(LFBlocks.WOOD.get(), LFBlocks.WOOD_6_SIDED.get());

        this.tag(BlockTags.PLANKS)
            .add(LFBlocks.WOODEN_PLANKS.get());

        this.tag(BlockTags.WOODEN_STAIRS)
            .add(LFBlocks.WOODEN_STAIRS.get());

        this.tag(BlockTags.WOODEN_SLABS)
            .add(LFBlocks.WOODEN_SLAB.get());

        this.tag(BlockTags.FENCES)
            .add(LFBlocks.FENCE.get());

        this.tag(BlockTags.WOODEN_FENCES)
            .add(LFBlocks.FENCE.get());

        this.tag(BlockTags.FENCE_GATES)
            .add(LFBlocks.FENCE_GATE.get());

        this.tag(BlockTags.DOORS)
            .add(LFBlocks.DOOR.get(), LFBlocks.IRON_DOOR.get());

        this.tag(BlockTags.WOODEN_DOORS)
            .add(LFBlocks.DOOR.get());

        this.tag(BlockTags.TRAPDOORS)
            .add(LFBlocks.TRAPDOOR.get());

        this.tag(BlockTags.WOODEN_TRAPDOORS)
            .add(LFBlocks.TRAPDOOR.get());

        this.tag(BlockTags.PRESSURE_PLATES)
            .add(LFBlocks.PRESSURE_PLATE.get(), LFBlocks.STONE_PRESSURE_PLATE.get());

        this.tag(BlockTags.WOODEN_PRESSURE_PLATES)
            .add(LFBlocks.PRESSURE_PLATE.get());

        this.tag(BlockTags.BUTTONS)
            .add(LFBlocks.BUTTON.get(), LFBlocks.STONE_BUTTON.get());

        this.tag(BlockTags.WOODEN_BUTTONS)
            .add(LFBlocks.BUTTON.get());

        this.tag(BlockTags.WALLS)
            .add(LFBlocks.COBBLESTONE_WALL.get(), LFBlocks.MOSSY_COBBLESTONE_WALL.get(), LFBlocks.BRICK_WALL.get());

        this.tag(BlockTags.STAIRS)
            .add(LFBlocks.WOODEN_STAIRS.get(), LFBlocks.STONE_STAIRS.get(), LFBlocks.COBBLESTONE_STAIRS.get(),
                LFBlocks.MOSSY_COBBLESTONE_STAIRS.get(), LFBlocks.BRICK_STAIRS.get());

        this.tag(BlockTags.SLABS)
            .add(LFBlocks.WOODEN_SLAB.get(), LFBlocks.STONE_SLAB.get(), LFBlocks.COBBLESTONE_SLAB.get(),
                LFBlocks.MOSSY_COBBLESTONE_SLAB.get(), LFBlocks.BRICK_SLAB.get());

        this.tag(BlockTags.COAL_ORES)
            .add(LFBlocks.COAL_ORE.get());

        this.tag(BlockTags.IRON_ORES)
            .add(LFBlocks.IRON_ORE.get());

        this.tag(BlockTags.GOLD_ORES)
            .add(LFBlocks.GOLD_ORE.get());

        this.tag(BlockTags.REDSTONE_ORES)
            .add(LFBlocks.REDSTONE_ORE.get());

        this.tag(BlockTags.DIAMOND_ORES)
            .add(LFBlocks.DIAMOND_ORE.get());

        this.tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(LFBlocks.STONE.get());

        this.tag(BlockTags.SAPLINGS)
            .add(LFBlocks.SAPLING.get());

        this.tag(BlockTags.FLOWERS)
            .add(LFBlocks.RED_FLOWER.get(), LFBlocks.YELLOW_FLOWER.get());

        this.tag(BlockTags.MOSS_REPLACEABLE)
            .add(LFBlocks.STONE.get(), LFBlocks.DIRT.get(), LFBlocks.GRASS_BLOCK.get(),
                LFBlocks.GRAVEL.get(), LFBlocks.SAND.get());

        this.tag(BlockTags.IMPERMEABLE)
            .add(LFBlocks.GLASS.get(), LFBlocks.GLASS_PANE.get());

        this.tag(BlockTags.SWORD_EFFICIENT)
            .add(LFBlocks.LEAVES.get());

        this.tag(BlockTags.WALL_POST_OVERRIDE)
            .add(LFBlocks.PRESSURE_PLATE.get(), LFBlocks.STONE_PRESSURE_PLATE.get(),
                LFBlocks.BUTTON.get(), LFBlocks.STONE_BUTTON.get());

        this.tag(BlockTags.VALID_SPAWN)
            .add(LFBlocks.GRASS_BLOCK.get());

        this.tag(BlockTags.STONE_BUTTONS)
            .add(LFBlocks.STONE_BUTTON.get());

        this.tag(BlockTags.STONE_PRESSURE_PLATES)
            .add(LFBlocks.STONE_PRESSURE_PLATE.get());

        this.tag(BlockTags.MUSHROOM_GROW_BLOCK)
            .add(LFBlocks.DIRT.get(), LFBlocks.GRASS_BLOCK.get());

        this.tag(BlockTags.DEAD_BUSH_MAY_PLACE_ON)
            .add(LFBlocks.SAND.get(), LFBlocks.GRAVEL.get(), LFBlocks.DIRT.get());

        this.tag(BlockTags.REPLACEABLE_BY_TREES)
            .add(LFBlocks.GRASS_BLOCK.get());

        this.tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)
            .add(LFBlocks.WOOD.get(), LFBlocks.WOOD_6_SIDED.get(), LFBlocks.WOODEN_PLANKS.get());
    }
}
