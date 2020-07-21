package ArrayStack;


import java.util.ArrayList;
import java.util.List;

/**
 * Oberklasse für die Klassen ArrayStack und ListStack.
 *
 * @param <E>
 */
public abstract class AbstractStack<E> implements Stack<E> {
    /**
     * Gemeinsame Methode
     *
     * @return Gibt das Obererste Element zurück
     */
    public E popTop() {
        E e = top();
        pop();
        return e;
    }

    /**
     * Vergleicht die Stacks
     *
     * @param s Stack zum Vergleich
     * @return true wenn die gleich sind
     */
    public boolean isEqualTo(AbstractStack<E> s) {
        if (this.toList().size() == s.toList().size()) {
            for (int i = 0; i < this.toList().size(); i++) {
                if (this.toList().get(i) != s.toList().get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * @param k
     */
    public void multiPop(int k) {
        for (int i = 0; i < k; i++) {
            if (this.toList().size() == 0) {
                break;
            }
            this.pop();
        }
    }

    /**
     * @param k
     * @return
     */
    public List<E> multiPopTop(int k) {
        List<E> list = new ArrayList<E>();
        for (int i = 0; i < k; i++) {
            if (this.toList().size() == 0) {
                break;
            }
            list.add(this.popTop());
        }
        return list;

    }

    public List<E> popTopAll() {
        List<E> list = new ArrayList<E>();
        for (E e : this.toList()) {
            list.add(e);
        }
        return list;
    }

    public void pushAll(List<E> es) {
        for (int i = 0; i < es.size(); i++) {
            push(es.remove(0));
        }
    }
}

