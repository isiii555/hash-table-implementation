package org.example.interfaces;

public interface Bucket<T> {
    void insert(T entry);
    T search(T entry);
    Iterable<T> getElements();
    default int size() {
       int size = 0;
       for (var element : getElements()) {
           size++;
       }
       return size;
    }

    void print();
}
