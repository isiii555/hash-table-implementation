package org.example.interfaces;

@FunctionalInterface
public interface Processor<T> {
    T process(T input);
}
