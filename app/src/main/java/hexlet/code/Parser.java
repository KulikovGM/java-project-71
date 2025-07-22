package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    public static Map<String, Object> parsing(Path path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> mappedFile1 = new HashMap<>();
        mappedFile1 = mapper.readValue(new File(String.valueOf(path)), new TypeReference<>() {});
        return mappedFile1;
    }

}
