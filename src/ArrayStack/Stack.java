package ArrayStack;

import java.util.List;

/**
 * @param <E>
 */
public interface Stack<E> {

    boolean isEmpty();

    void push(E e);

    void pop();

    E top();

    E popTop();

    boolean isEqualTo(AbstractStack<E> s);

    List<E> toList();

    void multiPop(int k);

    List<E> multiPopTop(int k);

    List<E> popTopAll();

}



