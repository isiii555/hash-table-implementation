package org.example.implementations;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.annotations.JsonElement;
import org.example.annotations.JsonSerializable;

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
