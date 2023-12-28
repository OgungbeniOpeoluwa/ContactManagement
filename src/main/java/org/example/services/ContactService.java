package org.example.services;

import org.example.data.model.Contact;
import org.example.dto.AddContactRequest;
import org.example.dto.EditContactRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {
    void addContact(AddContactRequest contact);
    List<Contact> findAllContactBelongingToUser(Long id);

    void editContact(EditContactRequest editContactRequest);
    Contact findContacts(Long id, String name);
}
