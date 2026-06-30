package net.justmili.leftforgotten.libs.v1.config.items;

import java.util.ArrayList;
import java.util.List;

public final class CategoryItem implements ConfigItem {
    private final String name;
    private final String comment;
    private final List<ConfigItem> children = new ArrayList<>();

    public CategoryItem(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String name() { return name; }
    public String comment() { return comment; }
    public List<ConfigItem> children() { return children; }

    public void add(ConfigItem item) { children.add(item); }
}