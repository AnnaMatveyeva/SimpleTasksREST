package example.exception;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value
        = {TaskNotFoundException.class, ExecutorNotFounException.class})
    public void handleNotFoundExceptions(RuntimeException ex, HttpServletResponse response)
        throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(value = UnableToChangeTaskExecutorException.class)
    public void handleBadRequestExceptions(RuntimeException ex, HttpServletResponse response)
        throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
    }
}