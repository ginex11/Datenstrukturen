package ADTList;

import ADTListe.List;

import static ADTListe.List.list;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListTest {
    List<Integer> test = list();

    @org.junit.jupiter.api.Test
    public void isEmpty_empty() {
        assertTrue(test.isEmpty());
    }


    @org.junit.jupiter.api.Test
    public void isEmpty_cons() {
        assertFalse(test.cons(5).isEmpty());
    }

    @org.junit.jupiter.api.Test
    public void elem_Empty() {

        System.out.println(test.elem(5));
        assertFalse(test.elem(5));
    }

    @org.junit.jupiter.api.Test
    public void elem_cons() {
        System.out.println(test.elem(5));
        assertTrue(test.cons(5).elem(5));
    }

    @org.junit.jupiter.api.Test
    public void head() {
        assertEquals(test.cons(5).head(), (Integer) 5);
    }

    @org.junit.jupiter.api.Test
    public void tail() {
        List<Integer> test1 = list();
        test.cons(5);
        test1.cons(5);
        assertEquals(test.cons(5).tail(), test1.cons(5).tail());
    }

    @org.junit.jupiter.api.Test
    public void length() {
        assertTrue(test.lengthL() == 0);
    }

    @org.junit.jupiter.api.Test
    public void length_cons() {
        assertTrue(test.cons(5).lengthL() == test.lengthL() + 1);
    }

    @org.junit.jupiter.api.Test
    public void length_tail() {
        List<Integer> test = list(5, 9);
        assertTrue(test.lengthR() - 1 == test.tail().lengthR());
    }

}