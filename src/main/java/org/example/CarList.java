package org.example;

public interface CarList<T> extends CarCollection<T> {
    T get(int index);
    boolean add(T car);
    boolean remove(T car);
    boolean removeAt(int index);
    int size();
    void clear();
    boolean add(T car, int index);
    boolean contains(T car);
}
