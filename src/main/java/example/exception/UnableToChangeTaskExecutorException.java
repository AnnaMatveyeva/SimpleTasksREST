package example.exception;

import example.entity.Status;

public class UnableToChangeTaskExecutorException extends RuntimeException {

    public UnableToChangeTaskExecutorException(Status status) {
        super("Unable to change task executor, because task has status " + status + ".");
    }
}
