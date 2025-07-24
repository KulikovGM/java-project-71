package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Parser {
    public static Map<String, Object> parsing(String path) throws IOException {
        ObjectMapper mapper;
        if (path.toLowerCase().endsWith(".json")) {
            mapper = new ObjectMapper();
        } else if (path.toLowerCase().endsWith(".yml")) {
            mapper = new YAMLMapper();
        } else {
            throw new IOException("Unknown file format");
        }

        Map<String, Object> mappedFile1 = new HashMap<>();
        mappedFile1 = mapper.readValue(new File(path), new TypeReference<>() {
        });
        return mappedFile1;
    }

}
