package ArrayQueue;


import Interface.AbstractQueue;
import Interface.QueueFehler;

public class ArrayQueue<E> extends AbstractQueue<E> {
    private E[] array;
    private int size, anfang, ende;
    private static final int CAPACITY = 10;

    public ArrayQueue() {
        array = (E[]) new Object[CAPACITY];
        anfang = 0;
        ende = -1;
        size = 0;
    }

    /**
     * prüft ob die Queue leer ist
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Position des Elementes
     *
     * @param i
     * @return
     */
    private int inc(int i) {
        if (++i == array.length) i = 0;
        return i;
    }

    /**
     * fügt ein Element hinzu
     *
     * @param e
     */
    public void enQueue(E e) {
        ende = inc(ende);
        array[ende] = e;
        size++;

    }

    /**
     * entfernt ein Element O(1)
     */
    public void deQueue() {
        if (isEmpty()) {
            throw new QueueFehler("Queue ist leer!");
        }
        anfang = inc(anfang);
        size--;
    }

    /**
     * zeigt das erste Element
     *
     * @return
     */
    public E front() {
        if (isEmpty()) {
            throw new QueueFehler("Queue ist leer!");
        }
        return array[anfang];
    }
}
