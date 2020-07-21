package ArrayStack;

import java.util.ArrayList;

public class Test {
    /**
     * ArrayStack Test-Programm
     */
    public static void main(String[] args) throws StackFehler {
        ArrayStack<String> arrayStack = new ArrayStack<String>();
        String testString = "String";
        System.out.println("Die 1. Schleife schreibt die Objekte auf den Stack");
        for (String s : testString.split("")) {
            System.out.println("\t" + s);
            arrayStack.push(s);
        }
        System.out.println("---------");
        while (!arrayStack.isEmpty()) {
            System.out.println("\t" + arrayStack.popTop());// die Laufzeit der Methode popTop() ist O(1);

        }
//        ArrayList<String>liste=new ArrayList<String>();
//        liste.add("S");
//        liste.add("v");
//        liste.add("a");
//        arrayStack.pushAll(liste);


    }
}
