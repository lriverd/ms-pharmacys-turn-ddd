package cl.duamit.pharmacy.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Json {

    public static final String EMPTY_JSON = "{}";
    private Json() {
    }
    public static String toJson(Object o) {
        if (o != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            try {
                return mapper.writeValueAsString(o);
            } catch (JsonProcessingException e) {
                return EMPTY_JSON;
            }
        }
        return EMPTY_JSON;
    }
    public static <T> T toObject(String json, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            return null;
        }
    }
}
