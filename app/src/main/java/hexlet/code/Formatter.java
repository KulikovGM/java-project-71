package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("java:S106")
public final class Formatter {
    private Formatter() {
        throw new IllegalStateException("Utility class formatter");
    }

    public static String formatter(List<Map<String, Object>> differences, String format) throws IOException {
        switch (format) {
            case "stylish":
                return Stylish.format(differences);
            case "plain":
                return Plain.format(differences);
            case "json":
                return Json.format(differences);
            default:
                System.out.println("Format '" + format + "' is not correct!");
        }
        return Stylish.format(differences);
    }
}
