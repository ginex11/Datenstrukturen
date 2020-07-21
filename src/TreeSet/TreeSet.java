package TreeSet;


import ADTListe.List;
import ADTSet.Set;

import java.util.function.Function;

public class TreeSet<A extends Comparable<A>> implements Set<A> {

    private final Tree<A> root;

    private TreeSet() {
        this.root = Tree.empty();
    }

    private TreeSet(Tree<A> t) {
        this.root = t;
    }


    @SuppressWarnings("unchecked")
    private TreeSet(Tree<A> t, A... as) {
        for (A a : as) {
            t = t.insert(a);
        }
        this.root = t;
    }

    // 0(n)
    @Override
    public Set<A> insert(A e) {
        return new TreeSet<>(this.root.insert(e));
    }
    // O(log(n))
    @Override
    public Set<A> delete(A e) {
        return new TreeSet<>(this.root.delete(e));
    }
    // O(log(n))
    @Override
    public boolean member(A e) {
        return this.root.member(e);
    }
    // O(n)
    @Override
    public A get(A e) {
        return this.root.get(e);
    }
    @Override
    public List<A> toList() {
        return this.root.toListInOrder();
    }
    @Override
    public boolean any(Function<A, Boolean> p) {
        return this.toList().toSet().any(p);
    }
    // O(n)
    @Override
    public int size() {
        return this.root.size();
    }
    // O(1)
    @Override
    public boolean isEmpty() {
        return this.root.isEmpty();
    }
    // O(n)
    @Override
    public boolean all(Function<A, Boolean> p) {
        return this.root.toListInOrder().toSet().all(p);
    }
    // O(n)
    @Override
    public boolean isSubsetOf(Set<A> s) {
        return all(x -> s.member(x));
    }

    // O(n)
    @Override
    public boolean isEqualTo(Set<A> s) {
        if (this.size() != s.size())
            return false;
        return this.isSubsetOf(s) && s.isSubsetOf(this);
    }
    @Override
    public boolean disjoint(Set<A> s) {
        return this.toList().toSet().disjoint(s);
    }

    @Override
    public Set<A> union(Set<A> s) {
        return this.toList().toSet().union(s);
    }

    @Override
    public Set<A> intersection(Set<A> s) {
        return this.toList().toSet().intersection(s);
    }


    static <A, B> B foldr(Function<A, Function<B, B>> f, B s, Set<A> xs) {
        return Set.foldr(f, s, xs);
    }
    static <A, B> B foldl(Function<B, Function<A, B>> f, B s, Set<A> xs) {
        return Set.foldl(f, s, xs);
    }
   public static <A> Set<A> filter(Function<A, Boolean> f, Set<A> xs) {
        return Set.filter(f, xs);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        return isEqualTo((Set<A>) o);
    }

    public static <A extends Comparable<A>> Set<A> empty() {
        return new TreeSet<A>();
    }

    @SuppressWarnings("unchecked")
    public static <A extends Comparable<A>> TreeSet<A> set(A... as) {
        return as.length == 0 ? new TreeSet<>() : new TreeSet<>(Tree.empty(), as);
    }

    @Override
    public String toString() {
        return this.isEmpty() ? "Set[]" : "Set[" + this.root.toListInOrder().toString() + "]";
    }

}
