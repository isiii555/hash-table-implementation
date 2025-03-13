package org.example.interfaces;

public interface Bucket<T> {
    void insert(T entry);

    T search(T entry);

    int getSize();

    default int size() {
        return getSize();
    }

    void print();
}
