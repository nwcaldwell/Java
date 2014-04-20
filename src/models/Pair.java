package models;

/**
 * Created by jorgep on 4/19/14.
 */
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
