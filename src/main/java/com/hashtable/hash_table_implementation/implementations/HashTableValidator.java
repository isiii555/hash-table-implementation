package com.hashtable.hash_table_implementation.implementations;

import com.hashtable.hash_table_implementation.annotations.HashTableData;

public class HashTableValidator {
    public static <K, V> void validateData(K key, V value, Class<?> clazz, String methodName) {

        try {
            var method = clazz.getDeclaredMethod(methodName, HashTableEntry.class);

            var parameters = method.getParameters();

            if (!parameters[0].isAnnotationPresent(HashTableData.class)) {
                return;
            }

            HashTableData annotation = parameters[0].getAnnotation(HashTableData.class);

            if (annotation.keyNotNull() && key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            if (annotation.valueNotNull() && (value == null || value.toString().isEmpty())) {
                throw new IllegalArgumentException("Value cannot be null or empty");
            }

            if (annotation.keyMustBePositiveInteger()) {
                if (!(key instanceof Integer)) {
                    throw new IllegalArgumentException("Key must be an Integer");
                }
                if ((Integer) key <= 0) {
                    throw new IllegalArgumentException("Key must be a positive integer");
                }
            }
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Method " + methodName + " not found", e);
        }
    }
}
