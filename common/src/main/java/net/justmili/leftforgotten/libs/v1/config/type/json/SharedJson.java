package net.justmili.leftforgotten.libs.v1.config.type.json;

import net.justmili.leftforgotten.libs.v1.config.entry.ConfigEntry;
import net.justmili.leftforgotten.libs.v1.config.items.CommentItem;
import net.justmili.leftforgotten.libs.v1.config.items.ConfigItem;

import java.util.List;

public class SharedJson {
    public static String extractValue(String json, String key) {
        String search = "\""+key+"\"";

        int index = json.indexOf(search), colon = json.indexOf(':', index+search.length());
        if (index == -1 || colon == -1) return null;

        int start = colon+1;
        while (start < json.length() && json.charAt(start) == ' ') start++;

        if (start >= json.length()) return null;
        char first = json.charAt(start);
        if (first == '"') {
            int end = json.indexOf('"', start+1);
            return end == -1 ? null : json.substring(start+1, end);
        }

        int end = start;
        while (end < json.length() && json.charAt(end) != ',' && json.charAt(end) != '\n' && json.charAt(end) != '}') end++;

        return json.substring(start, end).trim();
    }

    public static String extractList(String json, String key) {
        String search = "\""+key+"\"";

        int index = json.indexOf(search);
        if (index == -1) return null;

        int colon = json.indexOf(':', index+search.length());
        if (colon == -1) return null;

        int start = colon+1;
        while (start < json.length() && (json.charAt(start) == ' ' || json.charAt(start) == '\n')) start++;

        if (start >= json.length() || json.charAt(start) != '[') return null;

        int end = json.indexOf(']', start);
        if (end == -1) return null;

        String arrayContents = json.substring(start+1, end);
        StringBuilder result = new StringBuilder();
        for (String element : arrayContents.split(",")) {
            String trimmed = element.trim().replaceAll("^\"|\"$", ""); // strip quotes from strings
            if (!trimmed.isBlank()) {
                if (result.length() > 0) result.append(",");
                result.append(trimmed);
            }
        }

        return result.toString();
    }

    public static String jsonValue(ConfigEntry<?> entry) {
        Object value = entry.get();
        if (value instanceof String) return "\""+value+"\"";

        return String.valueOf(value);
    }

    public static int lastRealIndex(List<ConfigItem> items, boolean hasComments) {
        for (int i = items.size()-1; i >= 0; i--) {
            if (hasComments || !(items.get(i) instanceof CommentItem)) return i;
        }
        return -1;
    }
}