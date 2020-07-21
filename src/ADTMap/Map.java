package ADTMap;

import ADTListe.List;

import java.util.function.Function;

public interface Map<K extends Comparable<K>,V> {
    Map<K,V> insert(K key, V value);
    boolean member(K key);
    Map<K,V> delete(K key);
    V lookup(K key);
    boolean isEmpty();
    int size();
    boolean all(Function<Entry<K,V>, Boolean> p);
    boolean allKeys(Function<K, Boolean> p);
    boolean isEqualTo (Map<K,V> m);
    public List<Entry<K, V>> toList();

}
