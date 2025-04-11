package com.hashtable.hash_table_implementation.interfaces;

public interface LinkedList<T> extends Bucket<T> {
    void addFirst(T data);

    boolean remove(T data);

    boolean isEmpty();

    T get(int index);

    LinkedList<T> processElements(Processor<T> processor);

}
