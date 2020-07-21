package TreeMap;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeMapTest {
    //	 (1) isEmpty(empty) == true
    @Test
    public void isEmpty() {
        TreeMap<Integer, String> m = new TreeMap<Integer, String>();
        assertTrue(m.isEmpty());
    }

    //	  (2)  isEmpty(insert(m,k,v)) == false
    @Test
    public void isEmpty_put() {
        TreeMap<Integer, String> m = new TreeMap<Integer, String>();
        Integer k = 2;
        String v = "dd";
        assertFalse(m.insert(k, v).isEmpty());
    }

    //	  (3)  member(empty,k) == false
    @Test
    public void member() {
        TreeMap<Integer, String> m = new TreeMap<Integer, String>();
        Integer k = 2;
        assertFalse(m.member(k));
    }

    //	  (4)  member(insert(m,k1,v),k2) =  k1==k2 ? true  : member(m,k2)
    @Test
    public void contains_put() {
        TreeMap<Integer, String> m = new TreeMap<Integer, String>();
        Integer k1 = 2;
        Integer k2 = 3;
        String v = "dd";
        assertEquals(m.insert(k1, v).member(k2), k1 == k2 ? true : m.member(k2));
    }

    //	  (5)  member(delete(m,k1),k2)=  k1==k2 ? false : member(m,k2)
    @Test
    public void contains_remove() {
        TreeMap<Integer, String> m = new TreeMap<Integer, String>();
        Integer k1 = 2;
        Integer k2 = 2;
        String v = "dd";
        assertEquals(m.delete(k1).member(k2), k1 == k2 ? false : m.member(k2));
    }

    //	(6)  insert(insert(m,k1,v),k2,v)    =  insert(insert(m,k2,v),k1,v)
    @Test
    public void put_put() {
        TreeMap<Integer, String> m = new TreeMap<Integer, String>();
        Integer k1 = 2;
        Integer k2 = 2;
        String v = "dd";
        assertEquals(m.insert(k1, v).insert(k2, v).toString(), m.insert(k2, v).insert(k1, v).toString());
    }

    //	  (7)  insert(insert(m,k,v1),k,v2)    =  insert(m,k,v2)
    @Test
    public void put_put_test2() {
        TreeMap<Integer, String> m = new TreeMap<Integer, String>();
        Integer k = 2;
        String v1 = "dd";
        String v2 = "dds";
        assertEquals(m.insert(k, v1).insert(k, v2).toString(), m.insert(k, v2).toString());
    }

    //	  (8)  delete(insert(m,k1,v),k2)  =  k1==k2 ? delete(m,k2) : insert(delete(m,k2),k1,v)
    @Test
    public void remove_put() {
        TreeMap<Integer, String> m = new TreeMap<Integer, String>();
        Integer k1 = 2;
        Integer k2 = 2;
        String v = "dd";
        assertEquals(m.insert(k1, v).delete(k2).toString(),
                k1 == k2 ? m.delete(k2).toString() : m.delete(k2).insert(k1, v).toString());

    }

    //	  (9)  lookup(insert(m,k1,v),k2)  =  k1==k2 ? v : lookup(m,k2)
    @Test
    public void get_put() {
        TreeMap<Integer, String> m = new TreeMap<Integer, String>();
        Integer k1 = 2;
        Integer k2 = 4;
        String v = "dd";
        assertEquals(m.insert(k1, v).lookup(k2), k1 == k2 ? v : m.lookup(k2));
    }

    //	  (10) lookup(delete(m,k1),k2)   =  k1==k2 ? null : lookup(m, k2)
    @Test
    public void get_remove() {
        TreeMap<Integer, String> m = new TreeMap<Integer, String>();
        Integer k1 = 2;
        Integer k2 = 2;
        String v = "dd";
        assertEquals(m.delete(k1).lookup(k2), k1 == k2 ? null : m.lookup(k2));
    }
}
