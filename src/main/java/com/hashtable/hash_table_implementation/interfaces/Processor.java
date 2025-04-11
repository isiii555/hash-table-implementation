package com.hashtable.hash_table_implementation.interfaces;

@FunctionalInterface
public interface Processor<T> {
    T process(T input);
}
