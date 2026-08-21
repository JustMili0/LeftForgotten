package net.justmili.leftforgotten.datagen.providers.tags;

import net.justmili.leftforgotten.libs.v1.utils.common.ResourceUtil;
import net.justmili.leftforgotten.registries.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class LFItemTagProvider extends IntrinsicHolderTagsProvider<Item> {
    public static final TagKey<Item> FORGE_COBBLE = TagKey.create(Registries.ITEM, ResourceUtil.asForge("cobblestone"));
    public static final TagKey<Item> FORGE_STONE = TagKey.create(Registries.ITEM, ResourceUtil.asForge("stone"));

    public LFItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.ITEM, lookupProvider, item -> BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        /**
         * Vanilla tags
         */
        this.tag(ItemTags.BOATS).add(ItemRegistry.BOAT.get());
        this.tag(ItemTags.LOGS).add(ItemRegistry.WOOD.get());
        this.tag(ItemTags.LOGS_THAT_BURN).add(ItemRegistry.WOOD.get());
        this.tag(ItemTags.LOGS).add(ItemRegistry.WOOD_6_SIDED.get());
        this.tag(ItemTags.LOGS_THAT_BURN).add(ItemRegistry.WOOD_6_SIDED.get());
        this.tag(ItemTags.PLANKS).add(ItemRegistry.WOODEN_PLANKS.get());
        this.tag(ItemTags.WOODEN_BUTTONS).add(ItemRegistry.BUTTON.get());
        this.tag(ItemTags.WOODEN_DOORS).add(ItemRegistry.DOOR.get());
        this.tag(ItemTags.WOODEN_TRAPDOORS).add(ItemRegistry.TRAPDOOR.get());
        this.tag(ItemTags.WOODEN_FENCES).add(ItemRegistry.FENCE.get());
        this.tag(ItemTags.WOODEN_SLABS).add(ItemRegistry.WOODEN_SLAB.get());
        this.tag(ItemTags.WOODEN_STAIRS).add(ItemRegistry.WOODEN_STAIRS.get());
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(ItemRegistry.PRESSURE_PLATE.get());
        this.tag(ItemTags.STONE_TOOL_MATERIALS).add(ItemRegistry.COBBLESTONE.get());
        this.tag(ItemTags.STONE_CRAFTING_MATERIALS).add(ItemRegistry.COBBLESTONE.get());
        this.tag(ItemTags.STONE_BUTTONS).add(ItemRegistry.STONE_BUTTON.get());
        this.tag(ItemTags.LEAVES).add(ItemRegistry.LEAVES.get());

        /**
         * Forge and Fabric tags
         */
        // FORGE
        this.tag(FORGE_COBBLE).add(ItemRegistry.COBBLESTONE.get());
        this.tag(FORGE_STONE).add(ItemRegistry.STONE.get());
        // FABRIC
        // idk does Fabric have additional tags like forge
    }
}