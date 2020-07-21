package Interface;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractQueue<E> implements Queue<E> {
    @Override
    public E deQueueFront() {
        E e = front();
        deQueue();
        return e;
    }

    /**
     * Stellt mehrere Elemente in die Queue
     *
     * @param li
     */
    public void enQueueAll(List<E> li) {
        if (li == null) {
        }
        for (E e : li) {
            enQueue(e);
        }
    }

    /**
     * entfernt mehrere Elemente aus der Queue
     *
     * @param k
     */
    public void multiDeQueue(int k) {
        while (!isEmpty() && --k > 0) {
            deQueue();
        }
    }

    /**
     * kann k Elemente auf einmal
     * entfernen
     *
     * @return neue Liste
     */
    public List<E> deQueueFrontAll() {
        List<E> liste = new LinkedList<>();
        while (!isEmpty()) {
            liste.add(deQueueFront());
        }
        return liste;
    }
}
