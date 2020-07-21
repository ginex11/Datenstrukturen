package Interface;

public class QueueFehler extends RuntimeException {
    String message;
    public QueueFehler(String m) {
        super(m);
    }
}
