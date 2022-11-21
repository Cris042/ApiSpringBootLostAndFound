package edu.ifgoiano.example.LostAndfound.service.validation;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedException extends RuntimeException
{
    public UnsupportedException(String ex) 
    {
        super(ex);
    }

    private static final long serialVersionUID= 1L;
}
