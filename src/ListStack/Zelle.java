package ListStack;

import ArrayStack.StackFehler;

public class Zelle<E> {
    protected E inhalt;
    protected Zelle<E> next;

    Zelle(E e) throws StackFehler {
        inhalt = e;
    }

}
