package com.hashtable.hash_table_implementation.implementations;

import com.hashtable.hash_table_implementation.annotations.JsonSerializable;
import com.hashtable.hash_table_implementation.annotations.JsonElement;

@JsonSerializable
public class BinaryTreeNode<T> {
    @JsonElement(key = "key")
    T key;

    @JsonElement(key = "left")
    BinaryTreeNode<T> left;

    @JsonElement(key = "right")
    BinaryTreeNode<T> right;

    public BinaryTreeNode(T key) {
        this.key = key;
        left = null;
        right = null;
    }
}