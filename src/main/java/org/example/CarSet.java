package org.example;

public interface CarSet<T> extends CarCollection<T> {
    boolean add(T car);
    boolean remove(T car);
    void clear();
    int size();
    boolean contains(T car);
}
