package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

@SuppressWarnings("java:S1192")
public final class Stylish {
    private Stylish() {
        throw new IllegalStateException("Utility class");
    }
    public static String format(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<String, Object> diffs : differences) {
            switch (diffs.get("status").toString()) {
                case "removed" -> result.append("  - ").append(diffs.get("key")).append(": ")
                        .append(diffs.get("oldValue")).append("\n");
                case "added" -> result.append("  + ").append(diffs.get("key")).append(": ")
                        .append(diffs.get("newValue")).append("\n");
                case "unchanged" -> result.append("    ").append(diffs.get("key")).append(": ")
                        .append(diffs.get("oldValue")).append("\n");
                default -> {
                    result.append("  - ").append(diffs.get("key")).append(": ")
                            .append(diffs.get("oldValue")).append("\n");
                    result.append("  + ").append(diffs.get("key")).append(": ")
                            .append(diffs.get("newValue")).append("\n");
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
