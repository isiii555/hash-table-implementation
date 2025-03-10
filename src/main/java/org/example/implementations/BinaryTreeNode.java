package org.example.implementations;

public class BinaryTreeNode<T> {
    T key;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;

    public BinaryTreeNode(T key) {
        this.key = key;
        left = null;
        right = null;
    }
}