package ADTSet;

import static ADTSet.ListSet.set;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListSetTest {
    Set<Integer> set1 = set();
    Set<Integer> set2 = set(4, 1, 3, 2);
    Set<Integer> set3 = set(1, 3, 2);


    @org.junit.jupiter.api.Test
    void isSubsetOf() {
        assertTrue(set1.isSubsetOf(set2));//Die leere Menge ist Teilmenge jeder Menge
        assertTrue(set2.isSubsetOf(set2));//Reflexivität
        assertTrue(set1.insert(1).isSubsetOf(set3) && set3.isSubsetOf(set2) && set1.insert(1).isSubsetOf(set2));//Transivität
    }

    @org.junit.jupiter.api.Test
    void isEqualTo() {
        assertTrue(set3.insert(4).isSubsetOf(set2) && set2.isSubsetOf(set3.insert(4)));
    }

    @org.junit.jupiter.api.Test
    void disjoint() {
        assertFalse(set2.all(x -> !set3.member(x)));//Alle Elemente müssen verschieden sein.
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        assertTrue(set1.isEmpty());  //isEmpty(empty) =  true;
        assertFalse(set1.insert(1).isEmpty()); //isEmpty(insert(x,s)) =  false
    }

    @org.junit.jupiter.api.Test
    void member() {
        assertFalse(set1.member(2)); //member(x,empty) =  false
    }

    @org.junit.jupiter.api.Test
    void member_Insert() { // member(y,insert(x,s)) =  x==y ? true  : member(y,s)
        Integer y = 5;
        Integer x = 6;
        assertTrue(set1.insert(y).member(x) == (x == y ? true : set1.member(y)));
    }

    @org.junit.jupiter.api.Test
    public void member_delete() { // member(y,delete(x,s)) =  x==y ? false : member(y,s)
        Integer x = 1;
        Integer y = 4;
        assertTrue(set2.delete(x).member(y) == (x == y ? false : set2.member(y)));
    }

    @org.junit.jupiter.api.Test
    public void size() { // size(empty) =  0
        assertTrue(set1.size() == 0);
    }

    @org.junit.jupiter.api.Test
    public void size_insert() { // size(insert(x,s)) =  !member(x,s) ? size(s)+1 : size(s)
        Integer x = 1;
        set2 = set2.insert(x);
        assertTrue(set2.size() == (!set2.member(x) ? set2.size() + 1 : set2.size()));
    }

    @org.junit.jupiter.api.Test
    void insert_insert() { // insert(y,insert(x,s)) =  x==y ? insert(y,s) : insert(x,insert(y,s))
        Integer x = 7;
        Integer y = 7;
        set2 = set2.insert(x);
        set2 = set2.insert(y);
        assertTrue(set2.isEqualTo(x == y ? set2.insert(y) : set2.insert(y).insert(x)));
    }

    @org.junit.jupiter.api.Test
    void delete() { // delete(x,empty) =  empty
        assertTrue(set1.delete(1).isEqualTo(set()));
    }

    @org.junit.jupiter.api.Test
    void delete_insert() { // delete(y,insert(x,s)) =  x==y ? delete(y,s) : insert(x,delete(y,s)
        Integer x = 7;
        Integer y = 3;
        set2 = set2.insert(x);
        set2 = set2.delete(y);
        assertTrue(set2.isEqualTo(x == y ? set2.delete(y) : set2.delete(y).insert(x)));
    }

    @org.junit.jupiter.api.Test
    void get() { // get(y,insert(x,s)) =  x==y ? x : get(y,s)
        Integer x = 7;
        Integer y = 3;
        set2 = set2.insert(x);
        assertTrue(set2.get(y) == (x == y ? x : set2.get(y)));
    }

    @org.junit.jupiter.api.Test
    void get_delete() {// get(y,delete(x,s)) =  x==y ? null : get(y,s)
        Integer x = 4;
        Integer y = 3;
        set2 = set2.delete(x);
        assertTrue(set2.get(y) == (x == y ? null : set2.get(y)));
    }

    @org.junit.jupiter.api.Test
    void union_assoziativ() { //Assoziativgesetz A ∪ (B ∪ C) = (A ∪ B) ∪ C
        assertTrue((set2.union(set3).union(set1.insert(34))).isEqualTo(set1.insert(34).union(set2).union(set3)));
    }

    @org.junit.jupiter.api.Test
    void intersection_distibutiv() {// Distributivgesetz: A ∪ (B ∩ C) = (A ∪ B) ∩ (A ∪ C)
        assertTrue((set2.intersection(set3).union(set1.insert(34))).isEqualTo
                (set1.insert(34).union(set2).intersection(set1.insert(34).union(set3))));
    }

    @org.junit.jupiter.api.Test
    void intersection_absorption() { // Absorptionsgesetz: A ∪ (A ∩ B) = A
        assertTrue(set2.intersection(set3).union(set2).isEqualTo(set2));
    }
}