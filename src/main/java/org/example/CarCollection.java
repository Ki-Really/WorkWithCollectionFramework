package org.example;

public interface CarCollection<T> extends Iterable<T>{
    boolean remove(T car);
    boolean add(T car);
    int size();
    void clear();
    boolean contains(T car);
}
