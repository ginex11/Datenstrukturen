package JunitTest;

import ArrayQueue.ArrayQueue;
import Interface.QueueFehler;
import ListQueue.ListQueue;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    ListQueue<String> listQueue = new ListQueue<String>();
    ArrayQueue<String> arrayQueue = new ArrayQueue<String>();

    @org.junit.jupiter.api.Test
    public void isEmpty() {
        assertTrue(listQueue.isEmpty() && arrayQueue.isEmpty());//isEmpty(empty)=true
    }

    @org.junit.jupiter.api.Test
    public void isEmpty2() {
        String str = "rennnen";
        for (String s : str.split("")) {
            listQueue.enQueue((String) s);
            arrayQueue.enQueue((String) s);
        }
        assertFalse(listQueue.isEmpty() && arrayQueue.isEmpty());//isEmpty(enQueue(x,q))=false
    }

    @org.junit.jupiter.api.Test
    public void deQueue() {
        String str = "laufen";
        arrayQueue.enQueue("x");
        arrayQueue.deQueue();
        listQueue.enQueue("x");
        listQueue.deQueue();
        assertTrue(listQueue.isEmpty() && arrayQueue.isEmpty());//deQueue(enQueue(x,empty))=empty

    }

    @org.junit.jupiter.api.Test
    public void front() {
        listQueue.enQueue("x");
        arrayQueue.enQueue("x");
        assertSame(arrayQueue.front(), listQueue.front());//front(enQueue(x,empty))=x
    }

    @org.junit.jupiter.api.Test
    public void enQueue() {
        String str = "fl";
        for (String s : str.split("")) {
            listQueue.enQueue((String) s);
            arrayQueue.enQueue((String) s);
        }
        assertEquals(listQueue.front(), "f");
        assertEquals(arrayQueue.front(), "f");//front(enQueue(y, enQueue(x,q)))=front(enQueue(x, q))
    }

    @org.junit.jupiter.api.Test
    public void deQueuefront() {
        String str = "be";
        for (String s : str.split("")) {
            listQueue.enQueue((String) s);
            arrayQueue.enQueue((String) s);
        }
        assertEquals(listQueue.front(), listQueue.deQueueFront());
        assertEquals(arrayQueue.front(), arrayQueue.deQueueFront());//deQueueFront(q)=front(q)
    }
}