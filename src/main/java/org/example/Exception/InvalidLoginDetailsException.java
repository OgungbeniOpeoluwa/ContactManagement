package org.example.Exception;

public class InvalidLoginDetailsException extends ContactAppException{
    public InvalidLoginDetailsException(String message) {
        super(message);
    }
}
