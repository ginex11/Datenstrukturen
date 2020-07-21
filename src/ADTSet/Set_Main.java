package ADTSet;

import ADTListe.List;

import static ADTListe.List.list;
import static ADTSet.ListSet.fromList;
import static ADTSet.ListSet.set;

public class Set_Main {
    public static void main(String[] args) {
        List<Integer> liste = list(1, 2, 3, 4, 5,5,5,5,21);
        System.out.println(liste.toSet());
        Set<Integer> set1 = set(22,34);
        Set<Integer> set2 = set(1, 7, 2, 3);
        Set<Integer> set3 = fromList(list(7, 3, 2, 6, 8));
        Set<Set> set11 =set(set1,set2);
        List<Set<Integer>>xss=list(set1,set2,set3);
        System.out.println(set11);
        System.out.println(Set.foldr(x->y->x+y,0,set1));
//        System.out.println(ListSet.powerSet(set1));

    }
}
