package org.example.Exception;

public class InvalidContactDetails extends ContactAppException{
    public InvalidContactDetails(String message) {
        super(message);
    }
}
