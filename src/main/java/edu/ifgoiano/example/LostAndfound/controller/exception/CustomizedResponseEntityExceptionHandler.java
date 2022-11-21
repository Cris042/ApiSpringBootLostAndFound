package edu.ifgoiano.example.LostAndfound.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.Services.validation.ExceptionResponse;
import edu.ifgoiano.example.LostAndfound.service.validation.UnsupportedException;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions( Exception ex, WebRequest request) 
    {
        return null;
    }

    @ExceptionHandler(UnsupportedException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions( Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse= new ExceptionResponse( 
            null,
            ex.getMessage(),
            request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
    