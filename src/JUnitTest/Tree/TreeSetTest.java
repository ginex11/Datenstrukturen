package Tree;

import ADTSet.Set;
import TreeSet.TreeSet;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeSetTest {
    // isEmpty(empty) = true
    @org.junit.jupiter.api.Test
    public void isempty() {
        assertTrue(TreeSet.empty().isEmpty());
    }

    // isEmpty(insert(s,x)) = false
    @Test
    public void isempty_insert() {
        assertFalse(TreeSet.set(3).insert(2).isEmpty());
    }

    // member(empty,x) = false
    @Test
    public void member() {
        int x = 2;
        assertFalse(TreeSet.set(1).member(x));
    }

    // member(insert(s,x),y) = x==y ? true : member(s,y)
    @Test
    public void member_insert() {
        int x = 1;
        int y = 2;
        Set<Integer> s = TreeSet.set(1);

        assertEquals(s.insert(x).member(y),
                x == y ?
                        true : s.member(y));


    }

    // member(delete(s,x),y) = x==y ? false : member(s,y)
    @Test
    public void member_remove() {
        int x = 1;
        int y = 2;
        Set<Integer> s = TreeSet.set(1);
        assertEquals(s.delete(x).member(y), x == y ? false : s.member(y));

    }

    // size(empty) = 0
    @Test
    public void size() {
        assertEquals(TreeSet.empty().size() ,  0);
    }

    // size(insert(s,x)) = !member(s,x) ? size(s)+1 : size(s)
    @Test
    public void size_insert() {
        int x = 1;
        int y = 2;
        Set<Integer> s = TreeSet.set(1);
        assertEquals(s.insert(x).size(), !s.member(x) ? s.size() + 1 : s.size());

    }
    //		(8)  insert(insert(s,x),y) = x==y ? insert(s,y) : insert(insert(s,y),x)
    @Test
    public void insert_insert(){
        int x = 1;
        int y = 2;
        Set<Integer> s = TreeSet.set(1);
        assertEquals(s.insert(x).insert(y), x==y ? s.insert(y) : s.insert(x).insert(y));

    }
    //		(9)  delete(empty,x) = empty
    @Test
    public void delete(){
        int x = 1;
        Set<Integer> s = TreeSet.set(1);
        assertTrue(s.delete(x).isEmpty());
    }
    //		(10) delete(insert(s,x),y) = x==y ? delete(s,y) : insert(delete(s,y),x)
    @Test
    public void remove_insert(){
        int x = 1;
        int y = 2;
        Set<Integer> s = TreeSet.set(1);
        assertEquals(s.insert(x).delete(y), x==y ? s.delete(y) : s.delete(y).insert(x));
    }

}