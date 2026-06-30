package net.justmili.leftforgotten.libs.v1.config.type;

import net.justmili.leftforgotten.libs.v1.config.entry.ConfigEntry;
import net.justmili.leftforgotten.libs.v1.config.entry.ListConfigEntry;
import net.justmili.leftforgotten.libs.v1.config.items.CategoryItem;

import java.nio.file.Path;
import java.util.Map;

public interface FormatWriter {
    enum CommentStyle {TAG, SLASH, NONE}

    void write(Path path, CategoryItem root);
    void load(Path path, Map<String, ConfigEntry<?>> entries, Map<String, ListConfigEntry> listEntries);

    default String hint(ConfigEntry<?> entry, CommentStyle commentStyle) {
        String prefix = switch (commentStyle) {
            case TAG -> "# ";
            case SLASH -> "// ";
            case NONE -> "";
        };

        Object defaultValue = entry.defaultValue();
        if (entry.hasRange()) return prefix+"Allowed range: "+entry.min()+"-"+entry.max()+" - Default: "+defaultValue;
        if (defaultValue instanceof Boolean) return prefix+"Allowed values: true, false - Default: "+defaultValue;

        return prefix+"Default: "+defaultValue;
    }

    default String listHint(ListConfigEntry entry, CommentStyle commentStyle) {
        String prefix = switch (commentStyle) {
            case TAG -> "# ";
            case SLASH -> "// ";
            case NONE -> "";
        };

        return prefix+"Allowed types: "+entry.type().getSimpleName()+" - Default: "+entry.defaultValue();
    }
}