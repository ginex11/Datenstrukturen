package Tree;

import ADTListe.List;
import TreeSet.Tree;

import static ADTListe.List.list;
import static TreeSet.Tree.empty;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeTest {

    @org.junit.jupiter.api.Test
    // (1)  isEmpty(empty) = true
    public void isempty() {
        assertTrue(empty().isEmpty());
    }

    //	(2)  isEmpty(insert(s,x)) = false
    @org.junit.Test
    public void isempty_insert() {
        Tree<Integer> s = empty();
        assertFalse(s.insert(3).isEmpty());
    }

    //	(3)  member(empty,x) = false
    @org.junit.Test
    public void member() {
        int x = 0;
        Tree<Integer> s = empty();
        assertFalse(s.member(0));
    }

    //(4)  member(insert(s,x),y) = x==y ? true : member(s,y)
    @org.junit.Test
    public void member_insert() {
        int x = 2;
        int y = 8;
        Tree<Integer> s = empty();
        assertEquals(s.insert(x).member(y), x == y ? true : s.member(y));
    }

    //(5)  member(delete(s,x),y)    = x==y ? false : member(s,y)
    @org.junit.Test
    public void member_remove() {
        Tree<Integer> s = empty();
        int x = 2;
        int y = 2;
        assertEquals(s.delete(y).member(x), x == y ? false : s.member(y));
    }

    //		(6)  size(empty) = 0
    @org.junit.Test
    public void size_empty() {
        assertEquals(empty().size(), 0);
    }
//	(7)  size(insert(s,x))	= !member(s,x) ? size(s)+1 : size(s)

    @org.junit.Test
    public void size_insert() {
        Tree<Integer> s = empty();
        int x = 3;
        int y = 4;
        assertEquals(s.insert(x).size(), !s.member(x) ? s.size() + 1 : s.size());

    }
    //	(8)  insert(insert(s,x),y) = = x==y ? insert(s,y) : insert(insert(s,y),x)
    public void insert_insert() {
        Tree<Integer> s = empty();
        int x = 2;
        int y = 3;
        assertEquals(s.insert(x).insert(y), x == y ? s.insert(y) : s.insert(x).insert(y));
    }

    //	(9)  delete(empty,x) = empty
    @org.junit.Test
    public void delete() {
        Tree<Integer> s = empty();
        assertTrue(s.delete(3).isEmpty());
    }

    //	(10) delete(insert(s,x),y) = delete(s,y) : insert(delete(s,y),x)
    @org.junit.Test
    public void remove_insert() {
        Tree<Integer> s = empty();
        int x = 2;
        int y = 2;
        assertEquals(s.insert(x).delete(y).toString(),
                x == y ? s.toString() : s.delete(y).insert(x).toString());
    }


    @org.junit.Test
    public void height() {
        int x = 3;
        assertEquals(Tree.tree(x).height() + 1, 1);
    }

    @org.junit.Test
    public void height_insert() {
        Tree<Integer> s = empty();
        int x = 3;
        int y = 3;
        assertEquals(s.insert(x).insert(y).height(),x == y ?s.insert(x).height() : s.insert(x).height() + 1);
    }

    @org.junit.Test
    public void height_remove() {
        Tree<Integer> s = empty();
        int x = 3;
        int y = 5;
        assertEquals(s.insert(x).delete(y).height(),x == y ? s.height() : s.height() + 1);
    }

    @org.junit.Test
    public void get() {
        Tree<Integer> s = empty();
        int x = 3;
        assertEquals(s.insert(x).get(x).toString(), "3");
    }

    @org.junit.Test
    public void testToListLevelOrder() {
        List<Integer> list = list(3, 2, 1, 10, 4, 5, 6, 7, 9, 8, 11);
        assertEquals(list(3, 2, 10, 1, 4, 11, 5, 6, 7, 9, 8).toString(), Tree.tree(list).toListLevelOrder().toString());
    }

    @org.junit.Test
    public void testToListInOrder() {
        List<Integer> list = list(3, 2, 1, 10, 4, 5, 6, 7, 9, 8, 11);
        assertEquals(list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11).toString(), Tree.tree(list).toListInOrder().toString());
    }

    @org.junit.Test
    public void testToListPreOrder() {
        List<Integer> list = list(3, 2, 1, 10, 4, 5, 6, 7, 9, 8, 11);
        assertEquals(list(3, 2, 1, 10, 4, 5, 6, 7, 9, 8, 11).toString(), Tree.tree(list).toListPreOrder().toString());
    }

    @org.junit.Test
    public void testToListPostOrder() {
        List<Integer> list = list(3, 2, 1, 10, 4, 5, 6, 7, 9, 8, 11);
        assertEquals(list(1, 2, 8, 9, 7, 6, 5, 4, 11, 10, 3).toString(), Tree.tree(list).toListPostOrder().toString());
    }


    @org.junit.Test
    public void testAnzBlaetter() {
        List<Integer> list = list(3, 2, 1, 10, 4, 5, 6, 7, 9, 8, 11);
        assertEquals(Tree.tree(list).anzBlaetter(), 3);
    }

    @org.junit.Test
    public void testAnzInnereKnoten() {
        List<Integer> list = list(3, 2, 1, 10, 4, 5, 6, 7, 9, 8, 11);
        assertEquals(Tree.tree(list).anzInnereKnoten(), 5);
    }

    @org.junit.Test
    public void testAnzKnotenMitEinemKind() {
        List<Integer> list = list(3, 2, 1, 10, 4, 5, 6, 7, 9, 8, 11);
        assertEquals(Tree.tree(list).anzKnotenMitEinemKind(), 6);
    }
}