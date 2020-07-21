package ADTListe;


import static ADTListe.List.list;


public class ListTest {
    public static void main(String[] args) {

        List<Integer> ex1 = list(2, 3, 12);
        List<Integer> ex2 = list(1, 61, 19, 5);
        List<Double> ex3 = list(2.0, 1.0, 1.0, 1.0);
        System.out.println(ex1.elem(12));
        System.out.println(List.filter(p -> p == 2, ex1));

    }
}

