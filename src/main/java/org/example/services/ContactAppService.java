package org.example.services;

import org.example.data.model.Contact;
import org.example.dto.AddContactRequest;
import org.example.dto.EditContactRequest;
import org.example.dto.LoginRequest;
import org.example.dto.RegisterRequest;

import java.util.Collection;
import java.util.List;


public interface ContactAppService {
    Long register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);

    void addContact(AddContactRequest contact);

    void editContact(EditContactRequest editContactRequest);

    Contact findContact(long id, String name);

    List<Contact> findAllContact(Long id);
}
