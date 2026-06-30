package net.justmili.leftforgotten.libs.v1.config.entry;

import net.justmili.leftforgotten.libs.CoreLibs;
import net.justmili.leftforgotten.libs.v1.config.ConfigLoader;
import net.justmili.leftforgotten.libs.v1.config.items.ConfigItem;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class ListConfigEntry<T> implements ConfigItem {
    private final String key;
    private final List<T> defaultValue;
    private List<T> value;
    private final ConfigLoader config;

    public ListConfigEntry(String key, List<T> defaultValue, ConfigLoader config) {
        this.key = key;
        this.defaultValue = new ArrayList<>(defaultValue);
        this.value = new ArrayList<>(defaultValue);
        this.config = config;
    }

    public List<T> defaultValue() {
        return defaultValue;
    }

    public List<T> get() {
        return value;
    }
    public void set(List<T> newValue) {
        if (!validate(newValue)) return;
        this.value = new ArrayList<>(newValue);
        config.save();
    }
    public boolean is(List<T> list) {
        return value.equals(list);
    }
    public boolean contains(T value) {
        return this.value.contains(value);
    }

    public String key() {
        return key;
    }
    public Class<?> type() {
        if (defaultValue.isEmpty()) return String.class;
        return defaultValue.get(0).getClass();
    }

    public void load(String raw) {
        if (raw == null || raw.isBlank()) return;
        List<T> parsed = new ArrayList<>();

        for (String element : raw.split(",")) {
            String trimmed = element.trim();
            try {
                parsed.add(parse(trimmed));
            } catch (Exception e) {
                CoreLibs.LOGGER.warn("Failed to parse list element '{}' for key '{}', skipping.", trimmed, key);
            }
        }

        value = parsed.isEmpty() ? new ArrayList<>(defaultValue) : parsed;
    }

    private boolean validate(List<T> list) {
        Class<?> type = type();
        for (T element : list) {
            if (!type.isInstance(element)) {
                CoreLibs.LOGGER.warn("List '{}' contains invalid type '{}', expected '{}', ignoring.",
                    key, element.getClass().getSimpleName(), type.getSimpleName());
                return false;
            }
        }
        return true;
    }
    public String serialize() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < value.size(); i++) {
            stringBuilder.append(value.get(i));
            if (i < value.size()-1) stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }
    public String serializeJson(String indent) {
        StringBuilder stringBuilder = new StringBuilder("[\n");
        for (int i = 0; i < value.size(); i++) {
            T element = value.get(i);
            String formatted = element instanceof String ? "\""+element+"\"" : String.valueOf(element);
            stringBuilder.append(indent+"  ").append(formatted);
            if (i < value.size()-1) stringBuilder.append(",");
            stringBuilder.append("\n");
        }
        stringBuilder.append(indent+"]");
        return stringBuilder.toString();
    }

    private T parse(String raw) {
        Class<?> type = type();
        if (type == Integer.class) return (T) Integer.valueOf(raw);
        if (type == Long.class) return (T) Long.valueOf(raw);
        if (type == Double.class) return (T) Double.valueOf(raw);
        if (type == Float.class) return (T) Float.valueOf(raw);
        if (type == Boolean.class) return (T) Boolean.valueOf(raw);
        return (T) raw;
    }
}