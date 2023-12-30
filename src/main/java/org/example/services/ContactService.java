package org.example.services;

import org.example.data.model.Contact;
import org.example.dto.request.AddContactRequest;
import org.example.dto.request.EditContactRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {
    void addContact(AddContactRequest contact);
    List<Contact> findAllContactBelongingToUser(Long id);

    void editContact(EditContactRequest editContactRequest);
    Contact findContacts(Long id, String name);

    void deleteContact(Long id, String name);

    void deleteAllContact(Long id);

    void blockContact(Long id, String contactName);

    void unBlockContact(long id, String contactName);
}
