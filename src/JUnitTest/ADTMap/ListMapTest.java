package ADTMap;

import org.junit.jupiter.api.Test;

import static ADTListe.List.list;
import static ADTMap.ListMap.fromList;
import static org.junit.jupiter.api.Assertions.*;

class ListMapTest {
    Map<Integer, String> map1 = ListMap.empty();
    Map<Integer, String> map2 = fromList(list(new Entry<>(1, "erde")));

    @Test
    void empty() {
        assertTrue(map1.isEmpty());
    }

    @Test
    void insertEmpty() {
        assertFalse(map1.insert(1, "erde").isEmpty());
    }

    @Test
    void member() {
        assertFalse(map1.member(2));
    }

    @Test
    void insertMember() {// member(y,insert(x,s)) =  x==y ? true  : member(y,s)
        Integer y = 5;
        Integer x = 6;
        assertTrue(map1.insert(y, "ede").member(x) == (x == y ? true : map1.member(y)));
    }

    @org.junit.jupiter.api.Test
    public void member_delete() { // member(y,delete(x,s)) =  x==y ? false : member(y,s)
        Integer x = 1;
        Integer y = 4;
        assertTrue(map2.delete(x).member(y) == (x == y ? false : map2.member(y)));
    }

    @org.junit.jupiter.api.Test
    public void size() { // size(empty) =  0
        assertTrue(map1.size() == 0);
    }

        @org.junit.jupiter.api.Test
    void insert_insert() { // insert(y,insert(x,s)) =  x==y ? insert(y,s) : insert(x,insert(y,s))
        Integer x = 7;//ReplaceSemantik
        Integer y = 7;
        map2 = map2.insert(x, "ede");
        map2 = map2.insert(y, "ede");
        assertTrue(map2.isEqualTo(x == y ? map2.insert(y, "ede") : map2.insert(y, "ede").insert(x, "ede")));
    }
    @org.junit.jupiter.api.Test
    void delete() { // delete(x,empty) =  empty
        assertTrue(map2.delete(1).isEqualTo(map2.delete(1)));
    }
    @org.junit.jupiter.api.Test
    void delete_insert() { // delete(y,insert(x,s)) =  x==y ? delete(y,s) : insert(x,delete(y,s)
        Integer x = 7;
        Integer y = 3;
        map2 = map2.insert(x,null);
        map2 = map2.delete(y);
        assertTrue(map2.isEqualTo(x == y ? map2.delete(y) : map2.delete(y).insert(x,null)));
    }
    @org.junit.jupiter.api.Test
    void lookup() { // lookup(y,insert(x,s)) =  x==y ? x : lookup(y,s)
        Integer x = 7;
        Integer y = 3;
        map2 = map2.insert(x,"ede");
        assertTrue(map2.lookup(y) == (x == y ? x : map2.lookup(y)));
    }
    @org.junit.jupiter.api.Test
    void get_delete() {// lookup(y,delete(x,s)) =  x==y ? null : lookup(y,s)
        Integer x = 4;
        Integer y = 3;
        map2 = map2.delete(x);
        assertTrue(map2.lookup(y) == (x == y ? null : map2.lookup(y)));
    }
}