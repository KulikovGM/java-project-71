package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder("");
        for (Map<String, Object> diffs : differences) {
            switch (diffs.get("status").toString()) {
                case "removed" -> result.append("Property '").append(diffs.get("key")).append("' was removed")
                        .append("\n");
                case "added" -> result.append("Property '").append(diffs.get("key"))
                        .append("' was added with value: ")
                        .append(getComplexValue(diffs.get("newValue"))).append("\n");
                case "updated" -> result.append("Property '").append(diffs.get("key")).append("' was updated. From ")
                        .append(getComplexValue(diffs.get("oldValue"))).append(" to ")
                        .append(getComplexValue(diffs.get("newValue"))).append("");
                default -> {
                }
            }
        }
        return result.toString();
    }

    public static String getComplexValue(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Object[] || value instanceof Collection<?> || value instanceof Map<?,?>) {
            return "[complex value]";
        } else if (value instanceof String) {
            return ("'" + value + "'");
        } else return value.toString();
    }
}
