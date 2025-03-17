package org.example.implementations;

import org.example.annotations.JsonElement;
import org.example.annotations.JsonSerializable;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonConverter {

    public String convertToJson(Object object) throws Exception {
        checkIfSerializable(object);
        return getJsonString(object);
    }

    private void checkIfSerializable(Object object) throws Exception {
        if (Objects.isNull(object)) {
            throw new Exception("The object to serialize is null");
        }

       var clazz = object.getClass();
        if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
            throw new Exception("The class "
                    + clazz.getSimpleName()
                    + " is not annotated with JsonSerializable");
        }
    }

    private String getJsonString(Object object) throws Exception {
        if (object == null) {
            return "null";
        }

        var clazz = object.getClass();

        if (Number.class.isAssignableFrom(clazz) ||
                Character.class.isAssignableFrom(clazz) ||
                clazz.equals(String.class)) {
            return "\"" + object.toString() + "\"";
        }

        if (clazz.isArray()) {
            return arrayToJson(object);
        }

        Map<String, String> jsonElementsMap = new LinkedHashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                Object fieldValue = field.get(object);
                String jsonValue = getJsonString(fieldValue);
                jsonElementsMap.put(getKey(field), jsonValue);
            }
        }

        String jsonString = jsonElementsMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":" + entry.getValue())
                .collect(Collectors.joining(","));
        return "{" + jsonString + "}";
    }


    private String arrayToJson(Object array) throws Exception {
        int length = Array.getLength(array);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);
            sb.append(getJsonString(element));
            if (i < length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private String getKey(Field field) {
        JsonElement jsonElement = field.getAnnotation(JsonElement.class);
        if (jsonElement != null && !jsonElement.key().isEmpty()) {
            return jsonElement.key();
        }
        return field.getName();
    }
}