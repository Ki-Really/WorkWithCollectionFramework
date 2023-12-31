package org.example;

import java.util.Iterator;

public class CarLinkedList<T> implements CarList<T>,CarQueue<T>{
    private Node first;
    private Node last;
    private int size = 0;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node node;
            @Override
            public boolean hasNext() {
                return node !=null;
            }

            @Override
            public T next() {
                T car = (T)node.value;
                node = node.next;
                return car;
            }
        };
    }

    private static class Node<T>{
    private Node<T> previous;
    private T value;
    private Node<T> next;

    public Node(Node<T> previous, T value, Node<T> next) {
        this.previous = previous;
        this.value = value;
        this.next = next;
    }
    }
    @Override
    public T get(int index) {
        checkIndex(index);
        return getNode(index).value;
    }
    private Node<T> getNode(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node=first;
        for (int i=0;i<index;i++){
            node = node.next;
        }
        return node;
    }

    @Override
    public boolean add(T car) {
        if(size == 0){
            first = new Node(null,car,null);
            last = first;
        }
        else {
            Node secondLast = last;
            last = new Node(secondLast, car, null);
            secondLast.next = last;
        }
        size++;
        return true;
    }

    @Override
    public T peek() {
        return size>0 ? get(0) : null;
    }

    @Override
    public T poll() {
        T car = get(0);
        removeAt(0);
        return car;
    }

    @Override
    public boolean remove(T car) {
    Node node = first;
        for(int i=0;i<size;i++){
            if(node.value.equals(car)){
                return removeAt(i);
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        Node node = getNode(index);
        Node nodeNext = node.next;
        Node nodePrevious = node.previous;
        if (nodeNext != null) {
            nodeNext.previous = nodePrevious;
        } else {
            last = nodePrevious;
        }
        if (nodePrevious != null) {
            nodePrevious.next = nodeNext;
        } else {
            first = nodeNext;
        }
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        last = null;
        first = null;
        size = 0;
    }

    @Override
    public boolean add(T car, int index) {
    checkIndex(index);
        if (index == size) {
            add(car);
            return true;
        }
        Node nodeNext = getNode(index);
        Node nodePrev = nodeNext.previous;
        Node node = new Node(nodePrev,car,nodeNext);
        nodeNext.previous = node;
        if (nodePrev != null) {
            nodePrev.next = node;
        } else {
            first = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean contains(T car) {
        return findElement(car) != -1;
    }
    private int findElement(T car) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(car)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }
    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
