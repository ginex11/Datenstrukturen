package ListQueue;

import Interface.AbstractQueue;
import Interface.QueueFehler;

import java.util.List;

public class ListQueue<E> extends AbstractQueue<E> {
    private Zelle<E> ende;
    int i;

    /**
     * prüft ob die Queue leer ist O(1)
     *
     * @return
     */
    public boolean isEmpty() {
        return ende == null;
    }

    /**
     * entfernt das erste Elemt aus der Schlange O(1)
     */
    public void deQueue() {
        if (isEmpty()) {
            throw new QueueFehler("Queue ist leer!");
        }
        if (ende.equals(ende.next)) {
            ende = null;

        } else {
            ende.next = ende.next.next;
        }
    }

    @Override
/**
 * fügt ein Element hinzu O(1)
 */
    public void enQueue(E e) {
        Zelle<E> neueZelle = new Zelle<E>(e);
        if (isEmpty()) {
            ende = neueZelle;
            ende.next = ende;
        } else {
            neueZelle.next = ende.next;
            ende.next = neueZelle;
            ende = ende.next;

        }
    }

    @Override
/**
 * zeigt das erste Element an O(1)
 */
    public E front() throws QueueFehler {
        if (isEmpty()) {
            throw new QueueFehler("Queue ist leer!");
        }
        return ende.next.inhalt;
    }


}
