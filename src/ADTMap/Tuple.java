package ADTMap;

public class Tuple<K, V> {
    public final K fst;
    public final V snd;

    public Tuple(K fst, V snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public boolean equals(Tuple obj) {
        return this.fst.equals(obj.fst) && this.snd.equals(obj.snd);
    }

    @Override
    public String toString() {
        return "( " + fst + "," + snd + " )";
    }
}

