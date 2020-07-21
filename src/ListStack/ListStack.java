package ListStack;

import ArrayStack.AbstractStack;
import ArrayStack.StackFehler;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse zum Erstellen von ArrayStack Objekten.
 *
 * @param <E> Generischer Typ
 * @author Miller
 */
public class ListStack<E> extends AbstractStack<E> {
    private Zelle<E> top; // Anker

    /**
     * Prüft ob der Stack Leer ist. Laufzeit: O(1)
     *
     * @return true
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * fügt ein Listenelement am Anfang ein. Laufzeit: O(1)
     *
     * @param e
     */
    @Override
    public void push(E e) {
        Zelle<E> neueZelle = new Zelle<E>(e);
        neueZelle.next = top;
        top = neueZelle;
    }

    /**
     * entfernt das erste Listenelement. Laufzeit: O(1)
     */
    @Override
    public void pop() {
        if (isEmpty()) {
            throw new StackFehler("Sie greifen auf einen leeren Stack zu!!!");
        }
        top = top.next;
    }

    /**
     * Gibt alle Elemente des Stacks als Liste zurück. Laufzeit: O(n)
     *
     * @return Ruft die Überladene Methode auf
     */
    @Override
    public List<E> toList() {
        List<E> list = new ArrayList<E>();
        list.add(top.inhalt);
        return toList(list, top.next);
    }

    /**
     * Überladene Methode
     *
     * @param list Liste mit Elmenten
     * @param e    Die Zelle mit dem Inhalt
     * @return rekursiver Aufruf der Methode bis die Liste leer ist
     */
    public List<E> toList(List<E> list, Zelle<E> e) {
        if (e == null) {
            return list;
        }
        list.add(e.inhalt);
        return toList(list, e.next);
    }

    /**
     * Liefert das erste Element der Liste. Laufzeit: O(1)
     *
     * @return das erste Element der Liste
     */
    @Override
    public E top() {
        if (isEmpty()) {
            throw new StackFehler("Sie greifen auf einen leeren Stack zu!!!");
        }
        return top.inhalt;
    }
}
