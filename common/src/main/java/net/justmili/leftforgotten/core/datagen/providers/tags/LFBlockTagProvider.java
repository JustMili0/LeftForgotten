package net.justmili.leftforgotten.core.datagen.providers.tags;

import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
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
            .add(BlockRegistry.COBBLESTONE.get());
        this.tag(FORGE_STONE)
            .add(BlockRegistry.STONE.get());
        // FABRIC
        // idk does Fabric have additional tags like forge

        /**
         * ADDS TO VANILLA TAGS
         */
        this.tag(BlockTags.SMALL_FLOWERS)
            .add(BlockRegistry.RED_FLOWER.get(), BlockRegistry.YELLOW_FLOWER.get());

        this.tag(BlockTags.STONE_ORE_REPLACEABLES)
            .add(BlockRegistry.STONE.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(BlockRegistry.COBBLESTONE.get(), BlockRegistry.COBBLESTONE_WALL.get(), BlockRegistry.COBBLESTONE_STAIRS.get(),
                BlockRegistry.COBBLESTONE_SLAB.get(),
                BlockRegistry.STONE.get(), BlockRegistry.STONE_SLAB.get(), BlockRegistry.STONE_STAIRS.get(),
                BlockRegistry.STONE_PRESSURE_PLATE.get(), BlockRegistry.STONE_BUTTON.get(),
                BlockRegistry.COAL_ORE.get(), BlockRegistry.IRON_ORE.get(), BlockRegistry.GOLD_ORE.get(), BlockRegistry.DIAMOND_ORE.get(), BlockRegistry.REDSTONE_ORE.get(),
                BlockRegistry.OBSIDIAN.get(), BlockRegistry.BRICKS.get(), BlockRegistry.BRICK_STAIRS.get(), BlockRegistry.BRICK_SLAB.get(), BlockRegistry.BRICK_WALL.get(),
                BlockRegistry.IRON_DOOR.get(),
                BlockRegistry.MOSSY_COBBLESTONE.get(), BlockRegistry.MOSSY_COBBLESTONE_STAIRS.get(), BlockRegistry.MOSSY_COBBLESTONE_SLAB.get(), BlockRegistry.MOSSY_COBBLESTONE_WALL.get(),
                BlockRegistry.GLASS.get(), BlockRegistry.GLASS_PANE.get(),
                BlockRegistry.WOODEN_SLAB.get(), // intentional, see: old slabs
                BlockRegistry.IRON_BLOCK.get(),
                BlockRegistry.GOLD_BLOCK.get(),
                BlockRegistry.DIAMOND_BLOCK.get()
            );

        this.tag(BlockTags.NEEDS_STONE_TOOL)
            .add(BlockRegistry.IRON_ORE.get(),
                BlockRegistry.IRON_BLOCK.get()
            );

        this.tag(BlockTags.NEEDS_IRON_TOOL)
            .add(BlockRegistry.GOLD_ORE.get(),
                BlockRegistry.DIAMOND_ORE.get(),
                BlockRegistry.REDSTONE_ORE.get(),
                BlockRegistry.GOLD_BLOCK.get(),
                BlockRegistry.DIAMOND_BLOCK.get()
            );

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(BlockRegistry.OBSIDIAN.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
            .add(BlockRegistry.WOODEN_PLANKS.get(), BlockRegistry.WOOD.get(), BlockRegistry.WOOD_6_SIDED.get(),
                BlockRegistry.WOODEN_STAIRS.get(), BlockRegistry.WOODEN_SLAB.get(),
                BlockRegistry.FENCE.get(), BlockRegistry.FENCE_GATE.get(),
                BlockRegistry.DOOR.get(), BlockRegistry.TRAPDOOR.get(),
                BlockRegistry.PRESSURE_PLATE.get(), BlockRegistry.BUTTON.get(),
                BlockRegistry.BOOKSHELF.get()
            );

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
            .add(BlockRegistry.GRASS_BLOCK.get(), BlockRegistry.DIRT.get(), BlockRegistry.FARMLAND.get(),
                BlockRegistry.SAND.get(), BlockRegistry.GRAVEL.get(), BlockRegistry.CLAY.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE)
            .add(BlockRegistry.LEAVES.get());

        this.tag(BlockTags.ANIMALS_SPAWNABLE_ON)
            .add(BlockRegistry.GRASS_BLOCK.get());

        this.tag(BlockTags.DIRT)
            .add(BlockRegistry.GRASS_BLOCK.get(), BlockRegistry.DIRT.get());

        this.tag(BlockTags.SAND)
            .add(BlockRegistry.SAND.get());

        this.tag(BlockTags.LEAVES)
            .add(BlockRegistry.LEAVES.get());

        this.tag(BlockTags.LOGS)
            .add(BlockRegistry.WOOD.get(), BlockRegistry.WOOD_6_SIDED.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
            .add(BlockRegistry.WOOD.get(), BlockRegistry.WOOD_6_SIDED.get());

        this.tag(BlockTags.PLANKS)
            .add(BlockRegistry.WOODEN_PLANKS.get());

        this.tag(BlockTags.WOODEN_STAIRS)
            .add(BlockRegistry.WOODEN_STAIRS.get());

        this.tag(BlockTags.WOODEN_SLABS)
            .add(BlockRegistry.WOODEN_SLAB.get());

        this.tag(BlockTags.FENCES)
            .add(BlockRegistry.FENCE.get());

        this.tag(BlockTags.WOODEN_FENCES)
            .add(BlockRegistry.FENCE.get());

        this.tag(BlockTags.FENCE_GATES)
            .add(BlockRegistry.FENCE_GATE.get());

        this.tag(BlockTags.DOORS)
            .add(BlockRegistry.DOOR.get(), BlockRegistry.IRON_DOOR.get());

        this.tag(BlockTags.WOODEN_DOORS)
            .add(BlockRegistry.DOOR.get());

        this.tag(BlockTags.TRAPDOORS)
            .add(BlockRegistry.TRAPDOOR.get());

        this.tag(BlockTags.WOODEN_TRAPDOORS)
            .add(BlockRegistry.TRAPDOOR.get());

        this.tag(BlockTags.PRESSURE_PLATES)
            .add(BlockRegistry.PRESSURE_PLATE.get(), BlockRegistry.STONE_PRESSURE_PLATE.get());

        this.tag(BlockTags.WOODEN_PRESSURE_PLATES)
            .add(BlockRegistry.PRESSURE_PLATE.get());

        this.tag(BlockTags.BUTTONS)
            .add(BlockRegistry.BUTTON.get(), BlockRegistry.STONE_BUTTON.get());

        this.tag(BlockTags.WOODEN_BUTTONS)
            .add(BlockRegistry.BUTTON.get());

        this.tag(BlockTags.WALLS)
            .add(BlockRegistry.COBBLESTONE_WALL.get(), BlockRegistry.MOSSY_COBBLESTONE_WALL.get(), BlockRegistry.BRICK_WALL.get());

        this.tag(BlockTags.STAIRS)
            .add(BlockRegistry.WOODEN_STAIRS.get(), BlockRegistry.STONE_STAIRS.get(), BlockRegistry.COBBLESTONE_STAIRS.get(),
                BlockRegistry.MOSSY_COBBLESTONE_STAIRS.get(), BlockRegistry.BRICK_STAIRS.get());

        this.tag(BlockTags.SLABS)
            .add(BlockRegistry.WOODEN_SLAB.get(), BlockRegistry.STONE_SLAB.get(), BlockRegistry.COBBLESTONE_SLAB.get(),
                BlockRegistry.MOSSY_COBBLESTONE_SLAB.get(), BlockRegistry.BRICK_SLAB.get());

        this.tag(BlockTags.COAL_ORES)
            .add(BlockRegistry.COAL_ORE.get());

        this.tag(BlockTags.IRON_ORES)
            .add(BlockRegistry.IRON_ORE.get());

        this.tag(BlockTags.GOLD_ORES)
            .add(BlockRegistry.GOLD_ORE.get());

        this.tag(BlockTags.REDSTONE_ORES)
            .add(BlockRegistry.REDSTONE_ORE.get());

        this.tag(BlockTags.DIAMOND_ORES)
            .add(BlockRegistry.DIAMOND_ORE.get());

        this.tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(BlockRegistry.STONE.get());

        this.tag(BlockTags.SAPLINGS)
            .add(BlockRegistry.SAPLING.get());

        this.tag(BlockTags.FLOWERS)
            .add(BlockRegistry.RED_FLOWER.get(), BlockRegistry.YELLOW_FLOWER.get());

        this.tag(BlockTags.MOSS_REPLACEABLE)
            .add(BlockRegistry.STONE.get(), BlockRegistry.DIRT.get(), BlockRegistry.GRASS_BLOCK.get(),
                BlockRegistry.GRAVEL.get(), BlockRegistry.SAND.get());

        this.tag(BlockTags.IMPERMEABLE)
            .add(BlockRegistry.GLASS.get(), BlockRegistry.GLASS_PANE.get());

        this.tag(BlockTags.SWORD_EFFICIENT)
            .add(BlockRegistry.LEAVES.get());

        this.tag(BlockTags.WALL_POST_OVERRIDE)
            .add(BlockRegistry.PRESSURE_PLATE.get(), BlockRegistry.STONE_PRESSURE_PLATE.get(),
                BlockRegistry.BUTTON.get(), BlockRegistry.STONE_BUTTON.get());

        this.tag(BlockTags.VALID_SPAWN)
            .add(BlockRegistry.GRASS_BLOCK.get());

        this.tag(BlockTags.STONE_BUTTONS)
            .add(BlockRegistry.STONE_BUTTON.get());

        this.tag(BlockTags.STONE_PRESSURE_PLATES)
            .add(BlockRegistry.STONE_PRESSURE_PLATE.get());

        this.tag(BlockTags.MUSHROOM_GROW_BLOCK)
            .add(BlockRegistry.DIRT.get(), BlockRegistry.GRASS_BLOCK.get());

        this.tag(BlockTags.DEAD_BUSH_MAY_PLACE_ON)
            .add(BlockRegistry.SAND.get(), BlockRegistry.GRAVEL.get(), BlockRegistry.DIRT.get());

        this.tag(BlockTags.REPLACEABLE_BY_TREES)
            .add(BlockRegistry.GRASS_BLOCK.get());

        this.tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)
            .add(BlockRegistry.WOOD.get(), BlockRegistry.WOOD_6_SIDED.get(), BlockRegistry.WOODEN_PLANKS.get());
    }
}