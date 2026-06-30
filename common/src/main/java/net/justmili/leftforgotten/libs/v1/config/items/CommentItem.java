package net.justmili.leftforgotten.libs.v1.config.items;

public final class CommentItem implements ConfigItem {
    private final String comment;

    public CommentItem(String comment) {
        this.comment = comment;
    }

    public String comment() { return comment; }
}