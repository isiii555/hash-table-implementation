package org.example.implementations;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.annotations.JsonElement;
import org.example.annotations.JsonSerializable;

import java.util.Objects;

@JsonSerializable
public class HashTableEntry<K, V> {
    @JsonElement(key = "key")
    K key;
    @JsonElement(key = "value")
    V value;

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

    public void setValue(V value) {
        this.value = value;
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