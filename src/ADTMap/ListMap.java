package ADTMap;

import ADTListe.List;
import ADTSet.ListSet;
import ADTSet.Set;

import java.util.function.Function;

public class ListMap<K extends Comparable<K>, V> implements Map<K, V> {

    public final Set<Entry<K, V>> set;


    private ListMap(Set<Entry<K, V>> set) {
        this.set = set;

    }

    public static <K extends Comparable<K>, V> ListMap<K, V> empty() {
        return new ListMap<>(ListSet.empty());
    }

    static <K extends Comparable<K>, V> Map<K, V> fromList(List<Entry<K, V>> xs) {
        return new ListMap<>(xs.toSet());
    }
    //Laufzeit: O(n)
    @Override
    public Map<K, V> insert(K key, V value) {
        return this.member(key) ? new ListMap<K, V>(this.delete(key).toList().toSet().insert(new Entry<K, V>(key, value))) :
                new ListMap<K, V>(this.set.insert(new Entry<K, V>(key, value)));
    }
    //Laufzeit: O(n)
    @Override
    public V lookup(K key) {
        return this.member(key) ? ListSet.filter(x -> key.equals(x.getKey()), this.set).toList().head().getValue() : null;
    }
    //Laufzeit: O(n)
    @Override
    public boolean member(K key) {
        return  this.set.any(kvEntry -> kvEntry.getKey().equals(key));
    }
    //Laufzeit: O(n)
    @Override
    public Map<K, V> delete(K key) {
        return this.member(key) ? new ListMap<>(this.toList().toSet().delete(ListSet.filter(x -> key.equals(x.getKey()), this.set).toList().head())) : this;
    }

    @Override
    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    @Override
    public int size() {
        return this.set.size();
    }


    public List<Entry<K, V>> toList() {
        return set.toList();
    }
    //Laufzeit: O(n)
    @Override
    public boolean all(Function<Entry<K, V>, Boolean> p) {
        return this.set.all(p);
    }
    //Laufzeit: O(n)
    @Override
    public boolean allKeys(Function<K, Boolean> p) {
        return this.set.all(e -> p.apply(e.getKey()));
    }
    //Laufzeit: O(n)


    //Laufzeit: O(n)
    @Override
    public boolean isEqualTo(Map<K, V> map) {
        return this.toList().toSet().isEqualTo(map.toList().toSet());
    }


    public String toString() {
        return this.isEmpty() ? "Map{}" : "Map" + this.set.toString();

    }

}
