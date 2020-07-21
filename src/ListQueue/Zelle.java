package ListQueue;

public class Zelle<E> {
    E inhalt;
    Zelle<E> next = null;

    Zelle(E e) {
        inhalt = e;
    }
}
