package org.example.interfaces;

import org.example.implementations.HashTableEntry;

import java.util.Map;

public interface HashTable<K, V> {

    void insert(HashTableEntry<K, V> data);

    void put(K key, V value);

    V get(K key);

    void clear();

    void print();

    int size();
}
