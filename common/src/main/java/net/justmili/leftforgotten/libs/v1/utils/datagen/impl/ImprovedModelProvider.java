package net.justmili.leftforgotten.libs.v1.utils.datagen.impl;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.model.DelegatedModel;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

// this is just Minecraft code but with the Fabric API patches on top of it, because Forge doesn't have a similar system unfortunately.
public abstract class ImprovedModelProvider implements DataProvider {
    private final PackOutput.PathProvider blockStatePathProvider;
    private final PackOutput.PathProvider modelPathProvider;
    private final String modId;

    public ImprovedModelProvider(PackOutput output, String modId) {
        this.blockStatePathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        this.modelPathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
        this.modId = modId;
    }

    public abstract void generateBlockStateModels(BlockModelGenerators generator);
    public abstract void generateItemModels(ItemModelGenerators generator);

    public CompletableFuture<?> run(CachedOutput output) {
        Map<Block, BlockStateGenerator> blockStateGenerators = Maps.newHashMap();
        Consumer<BlockStateGenerator> blockStateOutput = (generator) -> {
            Block block = generator.getBlock();
            BlockStateGenerator prev = blockStateGenerators.put(block, generator);
            if (prev != null) {
                throw new IllegalStateException("Duplicate blockstate definition for "+block);
            }
        };

        Map<ResourceLocation, Supplier<JsonElement>> models = Maps.newHashMap();
        Set<Item> simpleModels = Sets.newHashSet();
        BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput = (resourceLocation, supplier) -> {
            Supplier<JsonElement> prev = models.put(resourceLocation, supplier);
            if (prev != null) {
                throw new IllegalStateException("Duplicate model definition for "+resourceLocation);
            }
        };
        Objects.requireNonNull(simpleModels);
        Consumer<Item> simpleModelOutput = simpleModels::add;

        // this is what we're adding in, by the way. Forge doesn't support mixins in datagen, for some reason.
        this.generateBlockStateModels(new BlockModelGenerators(blockStateOutput, modelOutput, simpleModelOutput));
        this.generateItemModels(new ItemModelGenerators(modelOutput));

        List<Block> list = BuiltInRegistries.BLOCK.stream().filter((block) -> !blockStateGenerators.containsKey(block)).toList();
        if (/*!list.isEmpty()*/ false) { // We don't need this
            throw new IllegalStateException("Missing blockstate definitions for: "+list);
        } else {
            BuiltInRegistries.BLOCK.forEach((block) -> {
                ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
                if (!blockId.getNamespace().equals(modId)) {
                    return;
                }

                Item item = Item.BY_BLOCK.get(block);
                if (item != null) {
                    if (simpleModels.contains(item)) {
                        return;
                    }
                    ResourceLocation modelId = ModelLocationUtils.getModelLocation(item);
                    if (!models.containsKey(modelId)) {
                        models.put(modelId, new DelegatedModel(ModelLocationUtils.getModelLocation(block)));
                    }
                }
            });

            return CompletableFuture.allOf(
                this.saveCollection(output, blockStateGenerators, (block) -> this.blockStatePathProvider.json(block.builtInRegistryHolder().key().location())),
                this.saveCollection(output, models, this.modelPathProvider::json)
            );
        }
    }

    private <T> CompletableFuture<?> saveCollection(CachedOutput output, Map<T, ? extends Supplier<JsonElement>> objectToJsonMap, Function<T, Path> resolveObjectPath) {
        return CompletableFuture.allOf(objectToJsonMap.entrySet().stream().map((entry) -> {
            Path path = resolveObjectPath.apply(entry.getKey());
            JsonElement modelData = entry.getValue().get();
            return DataProvider.saveStable(output, modelData, path);
        }).toArray(CompletableFuture[]::new));
    }

    public final String getName() {
        return "Model Definitions";
    }
}
