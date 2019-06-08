package exceptions;

public class EndGameException extends RuntimeException {

    public EndGameException(KeyboardException e) {
        System.out.println(e.getMessage());
    }
}
