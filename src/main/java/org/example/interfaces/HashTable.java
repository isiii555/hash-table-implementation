package org.example.interfaces;

import org.example.implementations.HashTableEntry;

public interface HashTable<K, V> {

    void insert(HashTableEntry<K, V> data);

    int getThreshold();
    Bucket<HashTableEntry<K,V>>[] getBuckets();

    void put(K key, V value);

    V get(K key);

    void clear();

    void print();

    int size();
}
