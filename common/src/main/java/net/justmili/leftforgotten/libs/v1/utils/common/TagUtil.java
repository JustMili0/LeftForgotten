package net.justmili.leftforgotten.libs.v1.utils.common;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class TagUtil {

    public static <T> TagKey<T> create(ResourceKey<? extends Registry<T>> registries, ResourceLocation id) {
        return TagKey.create(registries, id);
    }

    public static TagKey<Block> block(ResourceLocation id) {
        return create(Registries.BLOCK, id);
    }

    public static TagKey<Item> item(ResourceLocation id) {
        return create(Registries.ITEM, id);
    }

    public static TagKey<Biome> biome(ResourceLocation id) {
        return create(Registries.BIOME, id);
    }

    public static TagKey<EntityType<?>> entityType(ResourceLocation id) {
        return create(Registries.ENTITY_TYPE, id);
    }

    public static TagKey<Enchantment> enchant(ResourceLocation id) {
        return create(Registries.ENCHANTMENT, id);
    }
}
