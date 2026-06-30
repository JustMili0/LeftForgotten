package net.justmili.leftforgotten.libs.v1.config.type.json;

import net.justmili.leftforgotten.libs.CoreLibs;
import net.justmili.leftforgotten.libs.v1.config.entry.ConfigEntry;
import net.justmili.leftforgotten.libs.v1.config.entry.ListConfigEntry;
import net.justmili.leftforgotten.libs.v1.config.items.CategoryItem;
import net.justmili.leftforgotten.libs.v1.config.items.CommentItem;
import net.justmili.leftforgotten.libs.v1.config.items.ConfigItem;
import net.justmili.leftforgotten.libs.v1.config.type.FormatWriter;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class JsonWriter implements FormatWriter {

    @Override
    public void write(Path path, CategoryItem root) {
        File file = path.toFile();
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("{\n");
            writeItems(writer, root.children(), "  ", new int[]{0});
            writer.write("}\n");
        } catch (IOException e) {
            CoreLibs.LOGGER.error("Failed to write config: {}", e.getMessage());
        }
    }

    private void writeItems(BufferedWriter writer, List<ConfigItem> items, String indent, int[] hintCounter) throws IOException {
        int lastReal = SharedJson.lastRealIndex(items, false);
        for (int i = 0; i < items.size(); i++) {
            ConfigItem item = items.get(i);
            boolean last = i == lastReal;

            if (item instanceof CommentItem) {
                // JSON doesn't support comments, skip silently

            } else if (item instanceof ListConfigEntry listEntry) {
                writer.write(indent+"\"_h"+hintCounter[0]+++"\": \""+listHint(listEntry, CommentStyle.NONE)+"\",\n");
                writer.write(indent+"\""+listEntry.key()+"\": "+listEntry.serializeJson(indent)+(last ? "\n" : ",\n"));
                if (!last) writer.write("\n");

            } else if (item instanceof ConfigEntry<?> entry) {
                writer.write(indent+"\"_h"+hintCounter[0]+++"\": \""+hint(entry, CommentStyle.NONE)+"\",\n");
                writer.write(indent+"\""+entry.key()+"\": "+SharedJson.jsonValue(entry)+(last ? "\n" : ",\n"));
                if (!last) writer.write("\n");

            } else if (item instanceof CategoryItem category) {
                writer.write(indent+"\""+category.name()+"\": {\n");
                writeItems(writer, category.children(), indent+"  ", hintCounter);
                writer.write(indent+"}"+(last ? "\n" : ",\n"));
                if (!last) writer.write("\n");
            }
        }
    }

    @Override
    public void load(Path path, Map<String, ConfigEntry<?>> entries, Map<String, ListConfigEntry> listEntries) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) stringBuilder.append(line).append("\n");

            String json = stringBuilder.toString();
            for (ConfigEntry<?> entry : entries.values()) {
                String value = SharedJson.extractValue(json, entry.key());
                if (value != null) entry.load(value);
            }
            for (ListConfigEntry listEntry : listEntries.values()) {
                String value = SharedJson.extractList(json, listEntry.key());
                if (value != null) listEntry.load(value);
            }
        } catch (IOException e) {
            CoreLibs.LOGGER.error("Failed to load config: {}", e.getMessage());
        }
    }
}