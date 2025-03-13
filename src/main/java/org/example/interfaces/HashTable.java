package org.example.interfaces;

public interface HashTable<K, V> {
    void put(K key, V value);

    V get(K key);

    void clear();

    void print();

    int size();
}
