package TreeSet;

import ADTListe.List;

import java.util.function.Function;


//Test
public abstract class Tree<A extends Comparable<A>> {

    @SuppressWarnings("rawtypes")
    private static Tree Empty = new Empty();

    public abstract A value();

    abstract Tree<A> left();

    abstract Tree<A> right();

    public abstract Tree<A> insert(A a);

    public abstract boolean member(A a);

    public abstract int size();

    public abstract int height();

    public abstract Tree<A> delete(A a);

    protected abstract Tree<A> removeMerge(Tree<A> t);

    public abstract boolean isEmpty();

    protected abstract <B> B foldInOrder(B identity, Function<B, Function<A, Function<B, B>>> f);

    // O(log(n)) bei nicht entarteten Baum
    public abstract A get(A e);

    public abstract List<A> toListLevelOrder();

    // findMin() + findNachfolger()
    public abstract List<A> toListInOrder();

    public abstract List<A> toListPreOrder();

    public abstract List<A> toListPostOrder();

    public abstract int anzBlaetter();

    public abstract int anzInnereKnoten();

    public abstract int anzKnotenMitEinemKind();

    private static class Empty<A extends Comparable<A>> extends Tree<A> {

        @Override
        public A value() {
            throw new IllegalStateException("value() called on empty");
        }

        @Override
        Tree<A> left() {
            throw new IllegalStateException("left() called on empty");
        }

        @Override
        Tree<A> right() {
            throw new IllegalStateException("right() called on empty");
        }

        @Override
        public String toString() {
            return "E";
        }

        @Override
        public Tree<A> insert(A value) {
            return new T<>(empty(), value, empty());
        }

        @Override
        public boolean member(A a) {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public int height() {
            return -1;
        }

        @Override
        public Tree<A> delete(A a) {
            return this;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        protected Tree<A> removeMerge(Tree<A> t) {
            return t;
        }

        @Override
        public A get(A e) {
            return null;
        }

        @Override
        public List<A> toListLevelOrder() {
            return List.list();
        }

        @Override
        public List<A> toListInOrder() {
            return List.list();
        }

        @Override
        public List<A> toListPreOrder() {
            return List.list();
        }

        @Override
        public List<A> toListPostOrder() {
            return List.list();
        }

        @Override
        protected <B> B foldInOrder(B identity, Function<B, Function<A, Function<B, B>>> f) {
            return identity;
        }

        @Override
        public int anzBlaetter() {
            return 0;
        }

        @Override
        public int anzInnereKnoten() {
            return 0;
        }

        @Override
        public int anzKnotenMitEinemKind() {
            return 0;
        }

    }

    private static class T<A extends Comparable<A>> extends Tree<A> {
        private final Tree<A> left;
        private final A value;
        private final Tree<A> right;

        private T(Tree<A> left, A value, Tree<A> right) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        @Override
        public A value() {
            return value;
        }

        @Override
        Tree<A> left() {
            return left;
        }

        @Override
        Tree<A> right() {
            return right;
        }

        @Override
        public String toString() {
            return String.format("T %s %s %s", left, value, right);
        }

        @Override
        public Tree<A> insert(A value) {
            return value.compareTo(this.value) < 0 ? new T<>(left.insert(value), this.value, right)
                    : value.compareTo(this.value) > 0 ? new T<>(left, this.value, right.insert(value))
                    : new T<>(this.left, value, this.right);
        }

        @Override
        public boolean member(A value) {
            return value.compareTo(this.value) < 0
                    ? left.member(value)
                    : value.compareTo(this.value) == 0 || right.member(value);
        }

        @Override
        public int size() {
            return  1 + left.size() + right.size();
        }

        @Override
        public int height() {
            return  1 + Math.max(left.height(), right.height());
        }

        @Override
        public Tree<A> delete(A a) {
            if (a.compareTo(this.value) < 0) {
                return new T<>(left.delete(a), value, right);
            } else if (a.compareTo(this.value) > 0) {
                return new T<>(left, value, right.delete(a));
            } else {
                return left.removeMerge(right);
            }
        }

        @Override
        protected Tree<A> removeMerge(Tree<A> t) {
            if (t.isEmpty()) {
                return this;
            }
            if (t.value().compareTo(value) < 0) {
                return new T<>(left.removeMerge(t), value, right);
            } else if (t.value().compareTo(value) > 0) {
                return new T<>(left, value, right.removeMerge(t));
            }
            throw new IllegalStateException("State Exception");
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public A get(A e) {
            if (e.compareTo(this.value) == 0)
                return e;
            if (e.compareTo(this.value) < 0)
                return left.get(e);
            if (e.compareTo(this.value) > 0)
                return right.get(e);
            return null;
        }

        @Override
        public List<A> toListLevelOrder() {
            List<A> ret = List.list();
            for (int i = 0; i < this.size(); i++) {
                ret = toListLevelOrder(ret, this, 0, i);
            }
            return ret.reverse();
        }

        private List<A> toListLevelOrder(List<A> ret, Tree<A> t, int curr, int level) {
            if (t == empty()) {
                return ret;
            } else if (curr == level) {
                return ret.cons(t.value());
            } else if (curr < level) {
                ret = toListLevelOrder(ret, t.left(), ++curr, level);
                ret = toListLevelOrder(ret, t.right(), curr, level);
            }
            return ret;
        }


        @Override
        public List<A> toListInOrder() {
            return toListInOrder(List.list(), this).reverse();
        }

        private List<A> toListInOrder(List<A> l, Tree<A> t) {
            if (!t.left().isEmpty())
                l = toListInOrder(l, t.left());
            l = l.cons(t.value());
            if (!t.right().isEmpty())
                l = toListInOrder(l, t.right());
            return l;
        }

        @Override
        protected <B> B foldInOrder(B identity, Function<B, Function<A, Function<B, B>>> f) {
            return f.apply(left.foldInOrder(identity, f)).apply(value).apply(right.foldInOrder(identity, f));
        }

        @Override
        public List<A> toListPostOrder() {
            return toListPostOrder(List.list(), this).reverse();
        }

        private List<A> toListPostOrder(List<A> l, Tree<A> t) {
            if (!t.left().isEmpty())
                l = toListPostOrder(l, t.left());
            if (!t.right().isEmpty())
                l = toListPostOrder(l, t.right());
            l = l.cons(t.value());
            return l;
        }

        @Override
        public List<A> toListPreOrder() {
            return toListPreOrder(List.list(), this).reverse();
        }

        private List<A> toListPreOrder(List<A> l, Tree<A> t) {
            l = l.cons(t.value());
            if (!t.left().isEmpty())
                l = toListPreOrder(l, t.left());
            if (!t.right().isEmpty())
                l = toListPreOrder(l, t.right());
            return l;
        }



        @Override
//        public int anzBlaetter() {
//            int ret1 = 0;
//            if (!this.left.isEmpty() && !this.right.isEmpty())
//                ret1 += 1;
//            if (!this.left().isEmpty())
//                ret1 += 1;
//            if (!this.right().isEmpty())
//                ret1 += 1;
//            return ret1;
//        }

        public int anzBlaetter() {
            if (this.isEmpty())
                return 0;
            else if (this.left.isEmpty() && this.right.isEmpty())
                return 1;
            return this.left.anzBlaetter() + this.right.anzBlaetter();
        }

        @Override
        public int anzInnereKnoten() {
            return this.size() - this.anzBlaetter();
        }
        @Override
        public int anzKnotenMitEinemKind() {
            return this.size() - 2 * this.anzBlaetter() + 1;
        }

        public static <A extends Comparable<A>> Tree<A> tree(A... values) {
            Tree<A> tree = empty();
            for (A a : values) {
                tree = tree.insert(a);
            }
            return tree;
        }
    }

    @SuppressWarnings("unchecked")
    public static <A extends Comparable<A>> Tree<A> empty() {
        return Empty;
    }

    public static <A extends Comparable<A>> Tree<A> tree(List<A> list) {
        return list.foldLeftb(empty(), t -> e -> t.insert(e));
    }

    @SafeVarargs
    public static <A extends Comparable<A>> Tree<A> tree(A... as) {
        return tree(List.list(as));
    }
}