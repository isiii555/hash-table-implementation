package com.hashtable.hash_table_implementation.implementations;

import com.hashtable.hash_table_implementation.annotations.JsonElement;
import com.hashtable.hash_table_implementation.annotations.JsonSerializable;
import com.hashtable.hash_table_implementation.interfaces.LinkedList;
import com.hashtable.hash_table_implementation.interfaces.Processor;


import java.util.ArrayList;
import java.util.List;

@JsonSerializable
public class LinkedListImpl<T> implements LinkedList<T> {
    @JsonElement(key = "head")
    private LinkedListNode<T> head;
    private int size;

    public LinkedListImpl() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addFirst(T data) {
        var newNode = new LinkedListNode<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public boolean remove(T data) {
        if (head == null) return false;

        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }

        var current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }

        if (current.next == null) return false;
        current.next = current.next.next;
        size--;
        return true;
    }

    @Override
    public T search(T entry) {
        var current = head;

        if (head == null) {
            return null;
        }

        while (current.next != null) {
            if (current.data.equals(entry)) {
                return current.data;
            }
            current = current.next;
        }

        if (current.data.equals(entry)) {
            return current.data;
        }

        return null;
    }


    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        var current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    @Override
    public void insert(T entry) {
        var newNode = new LinkedListNode<>(entry);
        if (head == null) {
            this.head = newNode;
        } else {
            var current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }


    @Override
    public void print() {
        if (head != null) {
            var current = head;
            while (current.next != null) {
                var data = current.data;
                System.out.print(data);
                current = current.next;
            }
            System.out.println(current.data);
        } else {
            System.out.println("List is empty");
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public List<T> getElements() {
        List<T> elements = new ArrayList<>();
        var current = head;
        while (current != null) {
            elements.add(current.data);
            current = current.next;
        }
        return elements;
    }


    @Override
    public LinkedList<T> processElements(Processor<T> processor) {
        var current = head;
        LinkedList<T> newLinkedList = new LinkedListImpl<>();
        while (current != null) {
            T result = processor.process(current.data);
            newLinkedList.insert(result);
            current = current.next;
        }
        return newLinkedList;
    }
}
