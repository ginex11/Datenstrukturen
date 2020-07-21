package ArrayStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Klasse zum Erstellen von ArrayStack Objekten.
 *
 * @param <E> Generischer Typ
 * @author Miller
 */
public class ArrayStack<E> extends AbstractStack<E> {
    /**
     * Implementierung des Stacks erfolgt mit einem Array
     */
    private E[] array;
    /**
     * Zeigt auf das oberste Element.
     */
    private int top;
    /**
     * Die Kapazität des Stacks
     **/
    private static final int CAPACITY = 10;

    /**
     * Konstruktor zum Erzeugen von Objekten der Klasse ArrayStack.
     */
    public ArrayStack() {
        array = (E[]) new Object[CAPACITY];
        top = -1;
    }

    /**
     * Prüft ob der Stack Leer ist
     *
     * @return true
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Inkrementiert top und speichert das Element an der Stelle top
     *
     * @param e Das neue Element
     */
    @Override
    public void push(E e) {
        if (isFull()) {
            /**Wenn push auf einen vollen ArrayStack ausgeführt wird, soll das Array verdoppelt
             werden.**/
            E[] neu = (E[]) new Object[0];
            for (int i = 0; i < array.length - 1; i++) {
                neu = Arrays.copyOf(array, array.length * 2);
            }
            array = neu;
//            throw new StackFehler("Stack ist Voll!!!");
        }

        array[++top] = e;
    }

    /**
     * Entfernt das oberste Element
     */
    @Override
    public void pop() {
        if (isEmpty()) {
            throw new StackFehler("Sie greifen auf einen leeren Stack zu!!!");
        }
        array[top--] = null;
    }

    /**
     * Liefert das Element an der Stelle top
     *
     * @return Das oberste Element
     */
    @Override
    public E top() {
        if (isEmpty()) {
            throw new StackFehler("Sie greifen auf einen leeren Stack zu!!!");
        }
        return array[top];
    }

    /**
     * Prüft ob der Stack Voll ist
     *
     * @return true
     */
    public boolean isFull() {
        return top + 1 == array.length;
    }

    /**
     * Gibt alle Elemente des Stacks als Liste zurück
     *
     * @return Liste mit allen Stack-Elementen
     */
    @Override
    public List<E> toList() {
        List<E> list = new ArrayList<E>();
        for (int i = top; i >= 0; i--) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * Gibt alle Elemente des Stacks als Array zurück
     *
     * @return Array mit allen Stack-Elementen
     */
    public Object[] toArray() {
        return Arrays.copyOf(array, array.length);
    }



}
