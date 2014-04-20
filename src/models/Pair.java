package models;

// This class was added during development to provide a utility class
public class Pair<K,V> {

    private K key;
    private V value;

    public Pair(K key, V val) {
        this.key = key;
        this.value = val;
    }

    public K getKey() {
        return key;
    }

    public V getValue(){
        return value;
    }
}
