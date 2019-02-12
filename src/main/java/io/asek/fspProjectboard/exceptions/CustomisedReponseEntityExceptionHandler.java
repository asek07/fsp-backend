package io.asek.fspProjectboard.exceptions;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

@ControllerAdvice
@RestController
public class CustomisedReponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

/*    This is a generic exception handler. If we wanted, we could create custom responses for
    each exception we want to throw.*/
//    @ExceptionHandler(NotFoundException.class)
//    public final ResponseEntity<ErrorDetails> handleAllExceptions(NotFoundException ex, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
//                request.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }

    /* Specific error handling for ProjectTaskNotFoundExceptions */
    @ExceptionHandler(ProjectTaskNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleNotFoundException(ProjectTaskNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
