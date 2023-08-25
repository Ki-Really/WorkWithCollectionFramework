package org.example;

import java.util.*;

public class CarHashMap<K,V> implements CarMap<K,V>{
    private final static int INITIAL_CAPACITY = 16;
    private final static double LOAD_FACTOR = 0.75;
    private Entry<K,V>[] array = new Entry[INITIAL_CAPACITY];
    private int size = 0;

    @Override
    public void put(K key, V value) {
        if(size>=array.length*LOAD_FACTOR){
            increaseArray();
        }
       boolean put = put(key,value,array);
       if(put){
           size++;
       }
    }

    private boolean put(K key,V value,Entry<K,V>[] dst){
        int position = getElementPosition(key,dst.length);
        Entry<K,V> existedElement = dst[position];
        if(existedElement==null){
            Entry<K,V> entry = new Entry<K,V>(key,value,null);
            dst[position]=entry;
            return true;
        }else{
            while(true){
                if(existedElement.key.equals(key)){
                    existedElement.value = value;
                    return false;
                }
                if(existedElement.next == null){
                    existedElement.next = new Entry<K,V>(key,value,null);
                    return true;
                }
                existedElement = existedElement.next;
            }
        }
    }

    @Override
    public V get(K key) {
        int position = getElementPosition(key,array.length);
        Entry<K,V> existedElement = array[position];
        while(existedElement!=null){
            if(existedElement.key.equals(key)){
                return (V)existedElement.value;
            }
            existedElement=existedElement.next;
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();
        for (Entry<K,V> entry : array){
            Entry<K,V> existedElement = entry;
            while(existedElement!=null){
                result.add((K)existedElement.key);
                existedElement = existedElement.next;
            }
        }
        return result;
    }

    @Override
    public List<V> values() {
        List<V> result = new ArrayList<>();
        for (Entry<K,V> entry : array){
            Entry<K,V> existedElement = entry;
            while(existedElement!=null){
                result.add((V)existedElement.value);
                existedElement = existedElement.next;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int position = getElementPosition(key,array.length);
        Entry<K,V> existedElement = array[position];
        if(existedElement!=null && existedElement.key.equals(key)){
            array[position] = existedElement.next;
            size--;
            return true;
        }
        else{
            while(existedElement!=null){
                Entry<K,V> nextElement = existedElement.next;
                if(nextElement == null){
                    return false;
                }
                if(nextElement.key.equals(key)){
                    existedElement.next = nextElement.next;
                    size--;
                    return true;
                }
                existedElement = existedElement.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        array = new Entry[INITIAL_CAPACITY];
    }
    private int getElementPosition(K carOwner, int arrayLength){
        return Math.abs(carOwner.hashCode() % arrayLength);
    }
    private void increaseArray(){
        Entry<K,V>[] newArray = new Entry[array.length*2];
        for (Entry<K,V> entry : array){
            Entry<K,V> existedElement = entry;
            while(existedElement!=null){
                put((K)existedElement.key,(V)existedElement.value, newArray);
                existedElement = existedElement.next;
            }
        }
        array = newArray;
    }
    private static class Entry<K,V>{
        private K key;
        private V value;
        private Entry<K,V> next;

        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
