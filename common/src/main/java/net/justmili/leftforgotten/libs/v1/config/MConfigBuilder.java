package net.justmili.leftforgotten.libs.v1.config;

import net.justmili.leftforgotten.libs.CoreLibs;
import net.justmili.leftforgotten.libs.v1.config.entry.ConfigEntry;
import net.justmili.leftforgotten.libs.v1.config.entry.ListConfigEntry;
import net.justmili.leftforgotten.libs.v1.config.items.CategoryItem;
import net.justmili.leftforgotten.libs.v1.config.items.CommentItem;
import net.justmili.leftforgotten.libs.v1.config.type.FileType;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class MConfigBuilder {
    private final ConfigLoader config;
    private final Deque<CategoryItem> stack = new ArrayDeque<>();
    private String comment = null;

    public MConfigBuilder(String modId, String name, FileType fileType, boolean createSubDirectory) {
        this.config = new ConfigLoader(modId, name, fileType, createSubDirectory);
        stack.push(new CategoryItem("root", null));
    }

    public MConfigBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void openCat(String name) {
        CategoryItem category = new CategoryItem(name, comment);
        comment = null;
        stack.peek().add(category);
        stack.push(category);
    }

    public void closeCat() {
        if (stack.size() <= 1) {
            CoreLibs.LOGGER.warn("closeCat() called without a matching openCat(), ignoring.");
            return;
        }
        stack.pop();
    }

    // List
    public <T> ListConfigEntry<T> defineList(String key, List<T> defaultValue) {
        ListConfigEntry<T> entry = new ListConfigEntry<>(key, defaultValue, config);
        registerList(entry);
        return entry;
    }

    // String
    public ConfigEntry<String> define(String key, String defaultValue) {
        return register(new ConfigEntry<>(key, defaultValue, config));
    }

    // Boolean
    public ConfigEntry<Boolean> define(String key, boolean defaultValue) {
        return register(new ConfigEntry<>(key, defaultValue, config));
    }

    // Integer
    public ConfigEntry<Integer> define(String key, int defaultValue, int min, int max) {
        return register(new ConfigEntry<>(key, defaultValue, min, max, config));
    }

    // Long
    public ConfigEntry<Long> define(String key, long defaultValue, long min, long max) {
        return register(new ConfigEntry<>(key, defaultValue, min, max, config));
    }

    // Double
    public ConfigEntry<Double> define(String key, double defaultValue, double min, double max) {
        return register(new ConfigEntry<>(key, defaultValue, min, max, config));
    }

    // Float
    public ConfigEntry<Float> define(String key, float defaultValue, float min, float max) {
        return register(new ConfigEntry<>(key, defaultValue, min, max, config));
    }

    private <T> ConfigEntry<T> register(ConfigEntry<T> entry) {
        if (comment != null) {
            stack.peek().add(new CommentItem(comment));
            comment = null;
        }
        stack.peek().add(entry);
        config.register(entry);
        return entry;
    }

    private <T> void registerList(ListConfigEntry<T> entry) {
        if (comment != null) {
            stack.peek().add(new CommentItem(comment));
            comment = null;
        }
        stack.peek().add(entry);
        config.registerList(entry);
    }

    public void build() {
        if (comment != null) {
            stack.peek().add(new CommentItem(comment));
            comment = null;
        }
        config.loadOrCreate(stack.peek());
    }

    public ConfigLoader getConfig() {
        return config;
    }
}