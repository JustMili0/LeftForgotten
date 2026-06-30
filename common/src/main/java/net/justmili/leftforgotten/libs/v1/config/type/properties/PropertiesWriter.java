package net.justmili.leftforgotten.libs.v1.config.type.properties;

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
import java.util.Properties;

public class PropertiesWriter implements FormatWriter {

    @Override
    public void write(Path path, CategoryItem root) {
        File file = path.toFile();
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("# "+path.getFileName()+"\n\n");
            writeItems(writer, root.children(), false);
        } catch (IOException e) {
            CoreLibs.LOGGER.error("Failed to write config: {}", e.getMessage());
        }
    }

    private void writeItems(BufferedWriter writer, List<ConfigItem> items, boolean warnedAboutCategories) throws IOException {
        for (ConfigItem item : items) {
            if (item instanceof CategoryItem categoryItem) {
                if (!warnedAboutCategories) {
                    CoreLibs.LOGGER.warn("Categories are not supported in .properties format, flattening.");
                    warnedAboutCategories = true;
                }
                writeItems(writer, categoryItem.children(), warnedAboutCategories);

            } else if (item instanceof CommentItem commentItem) {
                for (String line : commentItem.comment().split("\n")) writer.write("# "+line+"\n");

            } else if (item instanceof ListConfigEntry listEntry) {
                writer.write(listHint(listEntry, CommentStyle.TAG)+"\n");
                writer.write(listEntry.key()+"="+listEntry.serialize()+"\n\n");

            } else if (item instanceof ConfigEntry<?> entry) {
                writer.write(hint(entry, CommentStyle.TAG)+"\n");
                writer.write(entry.key()+"="+entry.serialize()+"\n\n");
            }
        }
    }

    @Override
    public void load(Path path, Map<String, ConfigEntry<?>> entries, Map<String, ListConfigEntry> listEntries) {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(path.toFile())) {
            properties.load(inputStream);
        } catch (IOException e) {
            CoreLibs.LOGGER.error("Failed to load config: {}", e.getMessage());
            return;
        }
        for (ConfigEntry<?> entry : entries.values()) entry.load(properties.getProperty(entry.key()));
        for (ListConfigEntry listEntry : listEntries.values()) listEntry.load(properties.getProperty(listEntry.key()));
    }
}