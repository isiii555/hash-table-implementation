package org.example.implementations;

import java.util.Objects;

public class HashTableEntry<K, V> {
    K key;
    V value;

    public HashTableEntry(K key, V value) {
        this.key = key;
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