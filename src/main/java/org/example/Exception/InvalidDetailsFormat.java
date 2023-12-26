package org.example.Exception;

public class InvalidDetailsFormart extends RuntimeException {
    public InvalidDetailsFormart(String message) {
        super(message);
    }
}
