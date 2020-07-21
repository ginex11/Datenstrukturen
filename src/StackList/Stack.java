package StackList;

import ADTListe.List;

public class Stack<A> {
    private List<A> top;

    public Stack(List<A> top) {
        this.top = top;
    }

    public boolean isEmpty() {
        return top.tail().isEmpty();
    }

    public void push(A e) {
        top = top.cons(e);
    }

    public String toString() {
        return top.toString();
    }

    public void pop() {
        top = top.tail();
    }

    public A top() {
        return top.head();
    }

    public A popTop() {
        A e = top();
        pop();
        return e;
    }
}
