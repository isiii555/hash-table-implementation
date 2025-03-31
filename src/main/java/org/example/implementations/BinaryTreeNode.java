package org.example.implementations;

import org.example.annotations.JsonElement;
import org.example.annotations.JsonSerializable;

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