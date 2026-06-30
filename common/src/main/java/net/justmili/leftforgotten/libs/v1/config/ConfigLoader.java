package net.justmili.leftforgotten.libs.v1.config;

import net.justmili.leftforgotten.libs.CoreLibs;
import net.justmili.leftforgotten.libs.v1.config.entry.ConfigEntry;
import net.justmili.leftforgotten.libs.v1.config.entry.ListConfigEntry;
import net.justmili.leftforgotten.libs.v1.config.items.CategoryItem;
import net.justmili.leftforgotten.libs.v1.config.type.FileType;
import net.justmili.leftforgotten.libs.v1.config.type.FormatWriter;
import net.justmili.leftforgotten.libs.v1.config.type.json.Json5Writer;
import net.justmili.leftforgotten.libs.v1.config.type.json.JsonWriter;
import net.justmili.leftforgotten.libs.v1.config.type.properties.PropertiesWriter;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ConfigLoader {
    public final String modId;
    public final String name;
    private final Path path;
    private final FormatWriter writer;
    public final Map<String, ConfigEntry<?>> entries = new HashMap<>();
    public final Map<String, ListConfigEntry> listEntries = new HashMap<>();
    public CategoryItem root;

    public ConfigLoader(String modId, String name, FileType fileType, boolean createSubDirectory) {
        Path configDirectory = Path.of("config");
        this.modId = modId;
        this.name = name;
        this.writer = resolveWriter(fileType);

        String extension = extension(fileType);
        String fileName = (name == null || name.isBlank())
            ? modId : (createSubDirectory ? name : modId+"-"+name);

        path = createSubDirectory
            ? configDirectory.resolve(modId).resolve(fileName+extension)
            : configDirectory.resolve(fileName+extension);
    }

    public void register(ConfigEntry<?> entry) {
        entries.put(entry.key(), entry);
    }

    public void registerList(ListConfigEntry entry) {
        listEntries.put(entry.key(), entry);
    }

    public void loadOrCreate(CategoryItem root) {
        this.root = root;
        File file = path.toFile();
        if (!file.exists()) {
            CoreLibs.LOGGER.info("No config found, creating defaults.");
            writer.write(path, root);
            return;
        }
        writer.load(path, entries, listEntries);
        writer.write(path, root);
    }

    public void save() {
        writer.write(path, root);
    }

    private static FormatWriter resolveWriter(FileType fileType) {
        return switch (fileType) {
            case JSON -> new JsonWriter();
            case JSON5 -> new Json5Writer();
            case YAML, YML -> throw new UnsupportedOperationException("YAML/YML support is not yet implemented.");
            case TOML -> throw new UnsupportedOperationException("TOML support is not yet implemented.");
            default -> new PropertiesWriter();
        };
    }

    private static String extension(FileType fileType) {
        return switch (fileType) {
            case JSON -> ".json";
            case JSON5 -> ".json5";
            case YAML -> ".yaml";
            case YML -> ".yml";
            case TOML -> ".toml";
            default -> ".properties";
        };
    }
}