package org.example.implementations;

import org.example.annotations.HashTableData;
import org.example.annotations.JsonElement;
import org.example.annotations.JsonSerializable;
import org.example.interfaces.BinaryTree;
import org.example.interfaces.Bucket;
import org.example.interfaces.HashTable;
import org.example.interfaces.LinkedList;

@JsonSerializable
public class HashTableImpl<K, V> implements HashTable<K, V> {
    private static final int DEFAULT_SIZE = 10;
    private final int DEFAULT_THRESHOLD = 8;
    @JsonElement(key = "buckets")
    private Bucket<HashTableEntry<K, V>>[] buckets;
    private final int capacity;
    private final int threshold;

    public HashTableImpl() {
        this(DEFAULT_SIZE);
    }

    public HashTableImpl(int capacity) {
        this.capacity = capacity;
        this.threshold = setThreshold();
        this.buckets = new Bucket[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedListImpl<>();
        }
    }
    
    private int getIndex(K key) {
        return key.hashCode() % buckets.length;
    }

    @Override
    public void insert(@HashTableData() HashTableEntry<K, V> data) {
        HashTableValidator.validateData(data.getKey(), data.getValue(), this.getClass(), "insert");
        var index = getIndex(data.getKey());
        if (buckets[index].size() <= threshold) {
            var bucket = (LinkedList<HashTableEntry<K, V>>) buckets[index];
            var bucketSize = bucket.size();

            if (bucketSize + 1 <= threshold) {
                for (int i = 0; i < bucketSize; i++) {
                    var entry = bucket.search(new HashTableEntry<>(data.getKey(), null));
                    if (entry != null) return;
                }
                bucket.insert(new HashTableEntry<>(data.getKey(), data.getValue()));
            } else {
                BinaryTree<HashTableEntry<K, V>> tree = new BinaryTreeImpl<>();
                for (int i = 0; i < bucketSize; i++) {
                    var entry = bucket.get(i);
                    tree.insert(entry);
                }
                tree.insert(new HashTableEntry<>(data.getKey(), data.getValue()));
                buckets[index] = tree;
            }
        } else {
            var bucket = (BinaryTree<HashTableEntry<K, V>>) buckets[index];
            var newEntry = new HashTableEntry<>(data.getKey(), data.getValue());
            bucket.insert(newEntry);
        }
    }

    @Override
    public void put(K key, V value) {
        var index = getIndex(key);
        if (buckets[index].size() <= threshold) {
            var bucket = (LinkedList<HashTableEntry<K, V>>) buckets[index];
            var bucketSize = bucket.size();

            if (bucketSize + 1 <= threshold) {
                for (int i = 0; i < bucketSize; i++) {
                    var entry = bucket.search(new HashTableEntry<>(key, null));
                    if (entry != null) return;
                }
                bucket.insert(new HashTableEntry<>(key, value));
            } else {
                BinaryTree<HashTableEntry<K, V>> tree = new BinaryTreeImpl<>();
                for (int i = 0; i < bucketSize; i++) {
                    var entry = bucket.get(i);
                    tree.insert(entry);
                }
                tree.insert(new HashTableEntry<>(key, value));
                buckets[index] = tree;
            }
        } else {
            var bucket = (BinaryTree<HashTableEntry<K, V>>) buckets[index];
            var newEntry = new HashTableEntry<>(key, value);
            bucket.insert(newEntry);
        }
    }

    @Override
    public V get(K key) {
        var index = getIndex(key);

        if (buckets[index] == null) {
            return null;
        }

        var entry = buckets[index].search(new HashTableEntry<>(key, null));
        if (entry != null) {
            return entry.getValue();
        }

        return null;
    }

    public void clear() {
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedListImpl<>();
        }
    }

    private int setThreshold() {
        var thresholdValue = System.getenv("threshold");
        try {
            if (thresholdValue != null) {
                if (Integer.parseInt(thresholdValue) <= 6) {
                    System.out.println("Threshold value must be greater than 6, using default(8)");
                    return DEFAULT_THRESHOLD;
                }
                return Integer.parseInt(thresholdValue);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid threshold value, using default (8).");
        }
        return DEFAULT_THRESHOLD;
    }

    @Override
    public int getThreshold() {
        return threshold;
    }

    @Override
    public void print() {
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + (i + 1) + ": ");
            var bucket = buckets[i];
            if (bucket.size() == 0) {
                System.out.println("Empty");
            } else {
                bucket.print();
            }
        }
    }

    @Override
    public int size() {
        int totalSize = 0;
        for (Bucket<HashTableEntry<K, V>> bucket : buckets) {
            totalSize += bucket.size();
        }
        return totalSize;
    }
    public Bucket<HashTableEntry<K,V>>[] getBuckets() {
        return buckets;
    }
}
