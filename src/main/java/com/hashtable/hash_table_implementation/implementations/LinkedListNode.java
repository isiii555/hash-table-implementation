package com.hashtable.hash_table_implementation.implementations;

import com.hashtable.hash_table_implementation.annotations.JsonElement;
import com.hashtable.hash_table_implementation.annotations.JsonSerializable;

@JsonSerializable
public class LinkedListNode<T> {
    @JsonElement(key = "head")
    T data;

    @JsonElement(key = "next")
    LinkedListNode<T> next;

    public T getData() {
        return data;
    }
    public LinkedListNode<T> getNext() {
        return next;
    }

    public LinkedListNode(T data) {
        this.data = data;
        this.next = null;
    }
}
