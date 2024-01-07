package org.example.util;

import org.example.data.model.Contact;
import org.example.data.model.ContactApp;
import org.example.dto.request.AddContactRequest;
import org.example.dto.request.RegisterRequest;

public class Mapper {
    public static Contact map(AddContactRequest contact) {
        Contact contacts = new Contact();
        contacts.setName(contact.getName());
        contacts.setPhoneNumber(contact.getPhoneNumber());
        contacts.setContactAppId(contact.getId());
        return contacts;
    }
    public static ContactApp mapRegister(RegisterRequest registerRequest){
        ContactApp user = new ContactApp();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(EncryptPassword.hashPassword(registerRequest.getPassword()));
        return user;

    }


}
