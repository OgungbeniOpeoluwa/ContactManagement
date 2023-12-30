package org.example.services;

import org.example.data.model.Contact;
import org.example.data.model.ContactApp;
import org.example.dto.request.*;

import java.util.List;


public interface ContactAppService {
    Long register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);

    void addContact(AddContactRequest contact);

    void editContact(EditContactRequest editContactRequest);

    Contact findContact(long id, String name);

    List<Contact> findAllContact(Long id);

    void editProfile(EditProfileRequest profile);

    ContactApp viewProfile(Long id);

    void deleteContact(Long id, String name);

    void deleteAllContact(Long id);

    void deleteAccount(Long id);

    void resetPassword(Long id, String oldPassword, String newPassword);

    void resetEmail(Long id, String oldEmail, String newMail1);
    void blockedContact(Long id,String contactName);

    void unBlockContact(long id,String contactName);
}
