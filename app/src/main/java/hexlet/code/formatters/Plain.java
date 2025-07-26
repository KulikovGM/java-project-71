package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> diffs : differences) {
            switch (diffs.get("status").toString()) {
                case "removed" -> result.append("Property '").append(diffs.get("key")).append("' was removed")
                        .append("\n");
                case "added" -> result.append("Property '").append(diffs.get("key"))
                        .append("' was added with value: '").append(diffs.get("oldValue")).append("'\n");
                case "unchanged" -> {continue;
                }
                default -> {
                    result.append("Property '").append(diffs.get("key")).append("' was updated. From ")
                            .append(diffs.get("oldValue")).append(" to ").append(diffs.get("newValue")).append("\n");
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
