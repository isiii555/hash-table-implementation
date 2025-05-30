package com.hashtable.hash_table_implementation.interfaces;

import java.util.List;

public interface Bucket<T> {
    void insert(T entry);

    T search(T entry);

    int getSize();

    List<T> getElements();

    default int size() {
        return getSize();
    }

    void print();
}
