package net.justmili.leftforgotten.core.datagen;

import net.justmili.leftforgotten.libs.v1.utils.ResourceUtil;
import net.justmili.leftforgotten.registries.LFItems;
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
    public static final TagKey<Item> FORGE_STONE = TagKey.create(Registries.ITEM, ResourceUtil.asForge("stone"));;

    public LFItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.ITEM, lookupProvider, item -> BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        /**
         * Vanilla tags
         */
        this.tag(ItemTags.BOATS).add(LFItems.BOAT.get());
        this.tag(ItemTags.LOGS).add(LFItems.WOOD.get());
        this.tag(ItemTags.LOGS_THAT_BURN).add(LFItems.WOOD.get());
        this.tag(ItemTags.LOGS).add(LFItems.WOOD_6_SIDED.get());
        this.tag(ItemTags.LOGS_THAT_BURN).add(LFItems.WOOD_6_SIDED.get());
        this.tag(ItemTags.PLANKS).add(LFItems.WOODEN_PLANKS.get());
        this.tag(ItemTags.WOODEN_BUTTONS).add(LFItems.BUTTON.get());
        this.tag(ItemTags.WOODEN_DOORS).add(LFItems.DOOR.get());
        this.tag(ItemTags.WOODEN_TRAPDOORS).add(LFItems.TRAPDOOR.get());
        this.tag(ItemTags.WOODEN_FENCES).add(LFItems.FENCE.get());
        this.tag(ItemTags.WOODEN_SLABS).add(LFItems.WOODEN_SLAB.get());
        this.tag(ItemTags.WOODEN_STAIRS).add(LFItems.WOODEN_STAIRS.get());
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(LFItems.PRESSURE_PLATE.get());
        this.tag(ItemTags.STONE_TOOL_MATERIALS).add(LFItems.COBBLESTONE.get());
        this.tag(ItemTags.STONE_CRAFTING_MATERIALS).add(LFItems.COBBLESTONE.get());
        this.tag(ItemTags.STONE_BUTTONS).add(LFItems.STONE_BUTTON.get());
        this.tag(ItemTags.LEAVES).add(LFItems.LEAVES.get());

        /**
         * Forge and Fabric tags
         */
        // FORGE
        this.tag(FORGE_COBBLE).add(LFItems.COBBLESTONE.get());
        this.tag(FORGE_STONE).add(LFItems.STONE.get());
        // FABRIC
        // idk does Fabric have additional tags like forge
    }
}