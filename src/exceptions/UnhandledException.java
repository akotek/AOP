package exceptions;

public class UnhandledException extends RuntimeException {
    public UnhandledException(String msg) {
        super(msg);
    }
}
