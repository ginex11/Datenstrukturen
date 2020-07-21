package ListQueue;

public class Test {
    public static void main(java.lang.String[] args) {
        ListQueue<String> queue = new ListQueue<String>();
        String str = "Rennen";

        for (String s : str.split("")) {    // Die Laufzeit ist O(n) 1;
            queue.enQueue(s);
            System.out.println(s);
        }
        System.out.println("---------------");
        while (!queue.isEmpty()) {
            System.out.println(queue.deQueueFront());
        }

        System.out.println(queue.isEmpty());
    }

}
