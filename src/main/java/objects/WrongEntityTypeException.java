package objects;

public class WrongEntityTypeException extends RuntimeException {
    public WrongEntityTypeException(String message) {
        super(message);
    }
}
