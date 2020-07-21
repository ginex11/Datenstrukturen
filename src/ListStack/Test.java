package ListStack;

/**
 * ListStack Test-Programm
 */
public class Test {
    public static void main(String[] args) {
        ListStack<String> listStack = new ListStack<String>();
        ListStack<String> listStack2 = new ListStack<String>();
        String testString = "Test";
        System.out.println("Die 1. Schleife schreibt die Objekte auf den Stack");
        for (String s : testString.split("")) {
            System.out.println("\t" + s);
            listStack.push(s);
            listStack2.push(s);
        }
        System.out.println("---------");
        while (!listStack.isEmpty()) {
            System.out.println("\t" + listStack.popTop());// die Laufzeit der Methode popTop() ist O(1);

        }
//        System.out.println(listStack.popTopAll());
        System.out.println(listStack2.multiPopTop(3));

    }
}
