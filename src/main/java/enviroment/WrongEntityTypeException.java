package enviroment;

public class WrongEntityTypeException extends RuntimeException {
    public WrongEntityTypeException(String message) {
        super(message);
    }
}
