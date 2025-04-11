package com.hashtable.hash_table_implementation.implementations;

import com.hashtable.hash_table_implementation.annotations.JsonElement;
import com.hashtable.hash_table_implementation.annotations.JsonSerializable;

import java.util.Objects;

@JsonSerializable
public class HashTableEntry<K, V> {
    @JsonElement(key = "key")
    private K key;
    @JsonElement(key = "value")
    private V value;

    public HashTableEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "key=" + key.hashCode() + " value=" + value + ",";
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTableEntry<?, ?> that = (HashTableEntry<?, ?>) o;
        return Objects.equals(key, that.key);
    }
}