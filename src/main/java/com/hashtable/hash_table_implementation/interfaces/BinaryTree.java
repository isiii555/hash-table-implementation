package com.hashtable.hash_table_implementation.interfaces;

public interface BinaryTree<T> extends Bucket<T> {
    void delete(T key);
    void print();
     BinaryTree<T> processElements(Processor<T> processor);
}
