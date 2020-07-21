package ADTMap;

public class Entry<K extends Comparable<K>,V> implements Comparable<Entry<K,V>>{
    final K key;
    final V value;

    public Entry(K key, V value) {
        if (key == null) {
            throw new RuntimeException("Key must not be null!");
        }
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o){
        if (o == null) {
            return false;
        }

        return this.key.equals(((Entry<K, V>)o).getKey());
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }


    @Override
    public String toString() {
        return "("+this.key+"|"+this.value+")";
    }


    public int compareTo(Entry<K, V> e) {
        return this.key.compareTo(e.getKey());
    }
    public static <K extends Comparable<K>, V> Entry<K, V> mapEntry(K key, V value) {
        return new Entry<>(key, value);
    }

    public static <K extends Comparable<K>, V> Entry<K, V> mapEntry(K key) {
        return new Entry<>(key, null);
    }
}
