package ADTSet;

import ADTListe.List;

import java.util.function.Function;

import static ADTListe.List.list;

public class ListSet<A> implements Set<A> {
    private final List<A> list;

    private ListSet(List<A> list) {
        this.list = list;
    }

    public static <A> Set<A> empty() {
        return new ListSet<>(list());
    }

    public static <A> Set<A> fromList(List<A> xs) {
        return new ListSet<>(xs);
    }

    public static <A> Set<A> set(A... es) {
        return fromList(list(es));
    }

    //Laufzeit: O(n)
    @Override
    public Set<A> insert(A e) {
        return list.elem(e) ? this.delete(e).insert(e) : new ListSet<A>(list.cons(e));
    }

    //Laufzeit: O(n)
    @Override
    public Set<A> delete(A e) {
        return list.elem(e) ? new ListSet<A>(List.filter(p -> !p.equals(e), list)) : this;
    }

    //Laufzeit: O(n)
    @Override
    public boolean member(A e) {
        return list.elem(e);
    }

    //Laufzeit: O(n)
    @Override
    public int size() {
        return list.lengthR();
    }

    //Laufzeit: O(1)
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    //Laufzeit: O(n)
    @Override
    public A get(A e) {
        return list.elem(e) ? e : null;
    }

    //Laufzeit: O(1)
    @Override
    public List<A> toList() {
        return list;
    }

    //Laufzeit: O(n)
    @Override
    public String toString() {
        return this.list.isEmpty() ? "{}" : "{" + list.toString().substring(1, list.toString().length() - 6) + "}";
    }

    //Laufzeit: O(n)
    @Override
    public boolean any(Function<A, Boolean> p) {
        return list.anyL(p);
    }

    //Laufzeit: O(n)
    @Override
    public boolean all(Function<A, Boolean> p) {
        return list.all2(p);
    }

    //Laufzeit: O(n)
    @Override
    public boolean isSubsetOf(Set<A> s) {
        return this.isEmpty() ? true : this.all(x -> s.member(x));
    }

    //Laufzeit: O(n)
    @Override
    public boolean isEqualTo(Set<A> s) {
        return s.isSubsetOf(this) && this.isSubsetOf(s);
    }

    //Laufzeit: O(n)
    @Override
    public boolean disjoint(Set<A> s) {
        return this.isEmpty() ? true : this.all(x -> !s.member(x));
    }

    //Laufzeit: O(n)
    public static <A, B> B foldr(Function<A, Function<B, B>> f, B s, Set<A> xs) {
        return List.foldRight(f, s, xs.toList());
    }

    //Laufzeit: O(n)
    public static <A, B> B foldl(Function<B, Function<A, B>> f, B s, Set<A> xs) {
        return List.foldLeft(f, s, xs.toList());
    }

    //Laufzeit: O(n)
    public static <A> Set<A> filter(Function<A, Boolean> f, Set<A> xs) {
        return fromList(List.filter(f, xs.toList()));
    }

    //Laufzeit: O(n)
    @Override
    public Set<A> union(Set<A> s) {
        return foldr(x -> y -> new ListSet<>(y.insert(x).toList()), this, s);
    }

    //Laufzeit: O(n)
    @Override
    public Set<A> intersection(Set<A> s) {
        return filter(y -> s.member(y), this);
    }

    //Laufzeit: O(n)
    public static <A> Set<A> unions(List<Set<A>> xss) {
        return List.foldRight(x -> y -> y.union(x), empty(), xss);
    }

//    public static <A> Set<A> powerSet(Set<A> xs) {
//        return ListSet.foldr(x -> y -> y.isSubsetOf(xs) ?  :powerSet(xs.toList().tail().toSet()));
//    }
//    public static <A> Set<A> union(Set<A> s1, Set<A> s2) {
//        return foldr(x -> y -> fromList(y.toList()).insert(x), s1, s2);
//    }
}
