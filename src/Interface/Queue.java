package Interface;

public interface Queue<E> {
    boolean isEmpty();
    void enQueue(E e);
    void deQueue();
    E deQueueFront();
    E front();
}
