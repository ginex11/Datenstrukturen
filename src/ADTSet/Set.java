package ADTSet;

import ADTListe.List;

import java.util.function.Function;

import static ADTSet.ListSet.fromList;

public interface Set<A> {
    Set<A> insert(A e);

    Set<A> delete(A e);

    boolean member(A e);

    int size();

    boolean isEmpty();

    A get(A e);

    List<A> toList();

    boolean any(Function<A, Boolean> p);

    boolean all(Function<A, Boolean> p);

    boolean isSubsetOf(Set<A> s);

    boolean isEqualTo(Set<A> s);

    boolean disjoint(Set<A> s);

    Set<A> union(Set<A> s);

    Set<A> intersection(Set<A> s);

    static <A, B> B foldr(Function<A, Function<B, B>> f, B s, Set<A> xs){
        return List.foldRight(f, s, xs.toList());
    };

    static <A, B> B foldl(Function<B, Function<A, B>> f, B s, Set<A> xs){
        return List.foldLeft(f, s, xs.toList());
    };

    static <A> Set<A> filter(Function<A, Boolean> f, Set<A> xs){
        return fromList(List.filter(f, xs.toList()));
    };

}
