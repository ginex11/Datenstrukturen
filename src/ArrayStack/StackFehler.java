package ArrayStack;

public class StackFehler extends RuntimeException {
    String message;
    public StackFehler(String m) {
        super(m);
       }
}
