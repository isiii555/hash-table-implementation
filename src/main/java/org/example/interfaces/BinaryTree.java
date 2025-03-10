package org.example.interfaces;

public interface BinaryTree<T> extends Bucket<T> {
    void delete(T key);
    void print();
     BinaryTree<T> processElements(Processor<T> processor);
}
