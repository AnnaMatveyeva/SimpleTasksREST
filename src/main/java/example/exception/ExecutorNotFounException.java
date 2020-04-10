package example.exception;

public class ExecutorNotFounException extends RuntimeException {

    public ExecutorNotFounException(Long id) {
        super("Executor with id " + id + " not found");
    }
}
