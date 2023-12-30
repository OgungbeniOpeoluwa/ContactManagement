package org.example.dto.response;

import org.example.Exception.ContactAppException;

public class ActionDoneException extends ContactAppException {

    public ActionDoneException(String message) {
        super(message);
    }
}
