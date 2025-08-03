package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("java:S1192")
public final class Difference {

    private Difference() {
        throw new IllegalStateException("Utility class Difference");
    }

    public static List<Map<String, Object>> diff(Map<String, Object> mappedFile1, Map<String, Object> mappedFile2) {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> keysSet = new TreeSet<>(mappedFile1.keySet());
        keysSet.addAll(mappedFile2.keySet());
        for (String key : keysSet) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (mappedFile1.containsKey(key) && !mappedFile2.containsKey(key)) {
                map.put("key", key);
                map.put("oldValue", mappedFile1.get(key));
                map.put("status", "removed");
            } else if (!mappedFile1.containsKey(key) && mappedFile2.containsKey(key)) {
                map.put("key", key);
                map.put("newValue", mappedFile2.get(key));
                map.put("status", "added");
            } else if (!Objects.equals(mappedFile1.get(key), mappedFile2.get(key))) {
                map.put("key", key);
                map.put("oldValue", mappedFile1.get(key));
                map.put("newValue", mappedFile2.get(key));
                map.put("status", "updated");
            } else {
                map.put("key", key);
                map.put("oldValue", mappedFile1.get(key));
                map.put("status", "unchanged");
            }
            result.add(map);
        }
        return result;
    }
}
