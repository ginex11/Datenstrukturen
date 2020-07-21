package ArrayQueue;

public class Test {
    public static <E> void main(String[] args) {
        ArrayQueue<E> arrayQueue = new ArrayQueue<E>();
        String str = "Laufen";
        for (String s : str.split("")) {    // Die Laufzeit ist O(n) 1;
            System.out.println(s);
            arrayQueue.enQueue((E) s);
        }
        System.out.println("----------------------");
            while (!arrayQueue.isEmpty()) {
                System.out.println(arrayQueue.deQueueFront());
            }
        System.out.println(arrayQueue.isEmpty());


    }
}
