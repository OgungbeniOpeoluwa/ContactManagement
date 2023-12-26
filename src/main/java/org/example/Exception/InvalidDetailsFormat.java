package org.example.Exception;

public class InvalidDetailsFormat extends RuntimeException {
    public InvalidDetailsFormat(String message) {
        super(message);
    }
}
