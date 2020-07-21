package TreeMap;

import ADTListe.List;
import ADTMap.Entry;
import ADTMap.Map;
import ADTSet.Set;
import TreeSet.TreeSet;

import java.util.function.Function;

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Set<Entry<K, V>> set = TreeSet.empty();


    private TreeMap(Set<Entry<K, V>> set) {
        this.set = set;
    }

    TreeMap() {
        this.set = TreeSet.empty();
    }

    // 0(n)
    static <K extends Comparable<K>, V> Map<K, V> fromList(List<Entry<K, V>> l) {
        return new TreeMap<K, V>(l.toSet());
    }

    // 0(1)
    public static <K extends Comparable<K>, V> Map<K, V> empty() {
        return new TreeMap<K, V>();
    }

    // 0(1)
    @Override
    public Map<K, V> insert(K key, V value) {
        return new TreeMap<K, V>(this.set.insert(new Entry<K, V>(key, value)));
    }

    // O(log(n))
    @Override
    public boolean member(K key) {
        return this.set.member(new Entry<K, V>(key, null));
    }

    // O(log(n))
    @Override
    public Map<K, V> delete(K key) {
        return new TreeMap<K, V>(this.set.delete(new Entry<K, V>(key, null)));
    }

    // O(log(n))
    @Override
    public V lookup(K key) {
        return this.member(key) ? TreeSet.filter(x -> x.equals(Entry.mapEntry(key)), this.set).toList().head().getValue() : null;
    }

    // 0(1)
    @Override
    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    // 0(n)
    @Override
    public int size() {
        return this.set.size();
    }

    // 0(n)
    @Override
    public List<Entry<K, V>> toList() {
        return this.toList();
    }

    // 0(n)
    @Override
    public boolean all(Function<Entry<K, V>, Boolean> p) {
        return this.set.all(p);
    }

    // 0(n)
    @Override
    public boolean allKeys(Function<K, Boolean> p) {
        return this.set.all(x -> p.apply(x.getKey()));
    }

    // 0(n)
    @Override
    public boolean isEqualTo(Map<K, V> o) {
        return this.set.equals(o.toList().toSet());
    }

    // 0(n)
    @Override
    public String toString() {
        return this.set.isEmpty() ? "Map[]" : "Map" + this.set.toString().substring(4);
    }
}
