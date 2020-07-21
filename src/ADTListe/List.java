package ADTListe;

import ADTMap.Map;
import ADTSet.ListSet;
import ADTSet.Set;
import TailCall.TailCall;

import java.util.function.Function;

import static ADTMap.ListMap.empty;
import static ADTSet.ListSet.fromList;
import static ADTSet.ListSet.set;
import static TailCall.TailCall.ret;
import static TailCall.TailCall.sus;

public abstract class List<A> {
    public abstract A head();

    public abstract List<A> tail();

    public abstract boolean isEmpty();

    public abstract List<A> setHead(A h);


    public abstract String toString();


    @SuppressWarnings("rawtypes")
    public static final List NIL = new Nil();

    private List() {
    }

    private static class Nil<A> extends List<A> {

        private Nil() {
        }

        public A head() {
            throw new IllegalStateException("head called on an empty list");
        }

        public List<A> tail() {
            throw new IllegalStateException("tail called on an empty list");
        }

        public boolean isEmpty() {
            return true;
        }

        @Override
        public String toString() {
            return "[NIL]";
        }

        @Override
        public List<A> setHead(A h) {
            throw new IllegalStateException("setHead called on an empty list");
        }


    }

    private static class Cons<A> extends List<A> {
        private final A head;
        private final List<A> tail;

        private Cons(A head, List<A> tail) {
            this.head = head;
            this.tail = tail;
        }

        public A head() {
            return head;
        }

        public List<A> tail() {
            return tail;
        }

        public boolean isEmpty() {
            return false;
        }

        @Override
        public List<A> setHead(A h) {
            return new Cons<>(h, tail());
        }


        @Override
        public String toString() {
            return "[" + foldRight(x -> s -> x + ", " + s, "", this) + "NIL]";
        }
    }

    public List<A> cons(A a) {
        return new Cons<>(a, this);
    }


    /**
     * Geht eine Liste von Rechts nach Links durch und macht mit jedem Element der Liste etwas, was in einer Function definiert wird.
     */
    //Laufzeit: O(n)
    public static <A, B> B foldRight(Function<A, Function<B, B>> f, B s, List<A> list) {
        return list.isEmpty() ? s : f.apply(list.head()).apply(foldRight(f, s, list.tail()));
    }

    public static <A, B> B foldRight_buch(List<A> list, B n,
                                          Function<A, Function<B, B>> f) {
        return list.isEmpty() ? n : f.apply(list.head()).apply(foldRight_buch(list.tail(), n, f));
    }

    //
//        public <B > Map < B, List < A >> groupBy(Function < A, B > f) {
//            return foldRight_buch(empty(), this, t -> mt -> {
//                final B k = f.apply(t);
//                return mt.insert(k, mt.get(k).getOrElse(list()).cons(t));
//            });
//        }
//
    public <B extends Comparable<B>> Map<B, List<A>> groupBy(Function<A, B> f) {
        List<A> workList = this;
        Map<B, List<A>> m = empty();
        while (!workList.isEmpty()) {
            final B k = f.apply(workList.head());
            List<A> rt = m.lookup(k);
            m = m.insert(k, rt);
            workList = workList.tail();
        }
        return m;
    }

    //Laufzeit: O(n)
    static Integer sum(List<Integer> list) {
        return foldRight(x -> y -> x + y, 0, list);
    }

    //Laufzeit: O(n)
    public <A> Integer lengthR() {
        return foldRight(x -> n -> n + 1, 0, this);
    }

    //Laufzeit: O(n)
    static <A, B> List<B> map(Function<A, B> f, List<A> list) {
        return foldRight(x -> xs -> xs.cons(f.apply(x)), list(), list);
    }

//    //Laufzeit: O(n)
//    public boolean any(Function<A, Boolean> p) {
//        return this.isEmpty() ? false : p.apply(head()) || tail().any(p);
//    }

    //Laufzeit: O(n)
    public boolean any2(Function<A, Boolean> p) {
        return foldRight(x -> xs -> p.apply(head()) || tail().any2(p), false, this.tail());
    }

    //Laufzeit: O(n)
    String toString(List<A> list) {
        return foldRight(x -> s -> x + ", " + s, "", list);
    }

    /**
     * Filtert die Liste nach einer Bedinung, die in der Function definiert wird, erf�llen. Sie werden entfernt, wenn Sie diese nicht erf�llen.
     */
    //Laufzeit: O(n)
    public static <A> List<A> filter(Function<A, Boolean> p, List<A> list) {
        return foldRight(x -> xs -> p.apply(x) ? xs.cons(x) : xs, list(), list);
    }

    /**
     * Verbindet zwei Listen.
     */
    //Laufzeit: O(n)
    static <A> List<A> append(List<A> xs, List<A> ys) {
        return foldRight(x -> l -> new Cons<>(x, l), ys, xs);
    }

    //Laufzeit: O(n)
    static Double prod(List<Double> xs) {
        return foldRight(x -> y -> x * y, 1.0, xs);
    }

    //########################################################
//Laufzeit: O(n)
    public static <A, B> B foldLeft(Function<B, Function<A, B>> f, B s, List<A> xs) {
        return xs.isEmpty() ? s : foldLeft(f, f.apply(s).apply(xs.head()), xs.tail());
    }

    //Laufzeit: O(n)
    static Integer sumWithLeft(List<Integer> list) {
        return foldLeft(x -> y -> x + y, 0, list);
    }

    //Laufzeit: O(n)
    static Double prodWithLeft(List<Double> xs) {
        return foldLeft(x -> y -> x * y, 1.0, xs);
    }

    //Laufzeit: O(n)
    public <A> Integer lengthL() {
        return foldLeft(n -> x -> n + 1, 0, this);
    }

    //Laufzeit: O(n)
    public boolean anyL(Function<A, Boolean> p) {
        return foldLeft(x -> xs -> p.apply(head()) || tail().anyL(p), false, this);
    }

//    //Laufzeit: O(n)
//    public boolean all(Function<A, Boolean> p) {
//        if (p.apply(head()) == false) {
//            return false;
//        }
//        return tail().isEmpty() ? p.apply(head()) : tail().all(p);
//    }

    //Laufzeit: O(n)
    public boolean all2(Function<A, Boolean> p) {
        return p.apply(head()) == false ? false : tail().isEmpty() ? p.apply(head()) :
                foldLeft(x -> xs -> tail().anyL(p) ? tail().all2(p) : false, false, this.tail());
    }

    //Laufzeit: O(n)
//    public boolean elem(A a) {
//        return this.isEmpty() ? false : a == this.head() ? true : this.tail().elem(a);
//    }

    //Laufzeit: O(n)
    public boolean elem(A a) {
        return this.isEmpty() ? false : this.anyL(x -> x == a) ? true : this.tail().elem(a);
    }

    public Set<A> toSet() {
        Set<A> set = fromList(this);
        return ListSet.foldr(x -> y -> fromList(y.insert(x).toList()), set(), set);
    }


    public List<A> nub() {
        return this.toSet().toList();
    }

    public <B> B foldLeftb(B identity, Function<B, Function<A, B>> f) {
        return foldLeft_(identity, this, f).eval();
    }

    private <B> TailCall<B> foldLeft_(B acc, List<A> list, Function<B, Function<A, B>> f) {
        return list.isEmpty()
                ? ret(acc)
                : sus(() -> foldLeft_(f.apply(acc).apply(list.head()), list.tail(), f));
    }
    public List<A> reverse() {
        return reverse_(list(), this).eval();
    }

    private TailCall<List<A>> reverse_(List<A> acc, List<A> list) {
        return list.isEmpty()
                ? ret(acc)
                : sus(() -> reverse_(new Cons<>(list.head(), acc), list.tail()));
    }
    public static <A, B> B foldRightb(List<A> list, B n, Function<A, Function<B, B>> f ) {
        return list.isEmpty()
                ? n
                : f.apply(list.head()).apply(foldRightb(list.tail(), n, f));
    }

    @SuppressWarnings("unchecked")
    public static <A> List<A> list() {
        return NIL;
    }

    @SafeVarargs
    public static <A> List<A> list(A... a) {
        List<A> n = list();
        for (int i = a.length - 1; i >= 0; i--) {
            n = new Cons<>(a[i], n);
        }
        return n;
    }


}
