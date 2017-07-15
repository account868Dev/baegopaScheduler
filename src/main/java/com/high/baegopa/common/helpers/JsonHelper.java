package com.high.baegopa.common.helpers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by high on 2017. 7. 4..
 */
@Slf4j
public class JsonHelper {
    public static JsonNode jsonToJsonNode(String json) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JsonFactory factory = mapper.getFactory();

        try {
            JsonParser jp = factory.createParser(json);
            return mapper.readTree(jp);
        } catch (IOException e) {
            log.error("jsonToJsonNode error : " + e.fillInStackTrace());
        }
        return null;
    }

    public static String jsonNodeToJson(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            log.error("jsonNodeToJson error : " + e.fillInStackTrace());
        }
        return null;
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("jsonToObject error : " + e.fillInStackTrace());
        }
        return null;
    }

    public static <T> T jsonNodeToObject(JsonNode node, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.convertValue(node, clazz);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonNodeToMap(JsonNode json) {
        return jsonNodeToObject(json, Map.class);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonToMap(String json) {
        return jsonToObject(json, Map.class);
    }

    public static <T> JsonNode objectToJsonNode(T obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.valueToTree(obj);
    }

    public static <T> List<T> jsonNodeToList(JsonNode nodes, Class<T> clazz) {
        List<T> list = Lists.newArrayList();
        nodes.forEach(node -> list.add(jsonNodeToObject(node, clazz)));
        return list;
    }

    public static <T> Set<T> jsonNodeToSet(JsonNode nodes, Class<T> clazz) {
        Set<T> set = Sets.newHashSet();
        nodes.forEach(node -> set.add(jsonNodeToObject(node, clazz)));
        return set;
    }

    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        List<T> list = Lists.newArrayList();
        jsonToJsonNode(json).forEach(node -> list.add(jsonNodeToObject(node, clazz)));
        return list;
    }

    public static <T> String objectToJson(T object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error("objectToJson error : " + e.fillInStackTrace());
        }
        return null;
    }

    public static <T> T getObjFromFile(String filePath, Class<T> clazz) {
        return jsonNodeToObject(getFromFile(filePath), clazz);
    }

    public static JsonNode getFromFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        JsonFactory factory = mapper.getFactory();
        try {
            @Cleanup InputStream stream = getStreamForLocation(filePath);
            JsonParser jp = factory.createParser(stream);
            return mapper.readTree(jp);
        } catch (IOException e) {
            log.error("getFromFile error : " + e.fillInStackTrace());
        }
        return null;
    }

    private static InputStream getStreamForLocation(String filePath) throws FileNotFoundException {
        try {
            @Cleanup InputStream stream = null;
            if (filePath.startsWith("file:///")) {
                stream = new FileInputStream(filePath.replace("file:///", ""));
            } else if (filePath.startsWith("file:") || filePath.startsWith("/")) {
                stream = new FileInputStream(filePath.replace("file:", ""));
            } else {
                stream = JsonHelper.class.getClassLoader().getResourceAsStream(filePath.replace("classpath:", ""));
            }
            return stream;
        } catch (IOException e) {
            log.error("getStreamForLocation error : " + e.fillInStackTrace());
        }
        return null;
    }

    public static void addField(JsonNode node, String fieldName, JsonNode value) {
        if (node.get(fieldName) != null)
            removeField(node, fieldName);
        if (node instanceof ObjectNode) {
            ObjectNode obj = (ObjectNode) node;
            obj.set(fieldName, value);
        }
    }

    public static void removeField(JsonNode node, String fieldName) {
        if (node instanceof ObjectNode) {
            ObjectNode obj = (ObjectNode) node;
            obj.remove(fieldName);
        }
    }
}
