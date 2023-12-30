package org.example.services;

import org.example.Exception.InvalidContactDetails;
import org.example.Exception.InvalidDetailsFormat;
import org.example.data.model.Contact;
import org.example.data.repository.ContactRepository;
import org.example.dto.request.AddContactRequest;
import org.example.dto.request.EditContactRequest;
import org.example.dto.response.ActionDoneException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ContactServiceImpl implements ContactService{
    @Autowired
    ContactRepository contactRepository;
    @Override
    public void addContact(AddContactRequest contact) {
        Contact contacts = new Contact();
        if(checkIfNameExitInContact(contact.getId(), contact.getName()))throw new InvalidDetailsFormat("Name already exist");
        contacts.setName(contact.getName());
        contacts.setPhoneNumber(contact.getPhoneNumber());
        contacts.setContactAppId(contact.getId());
        contactRepository.save(contacts);
    }
    private boolean checkIfNameExitInContact(Long id,String name){
        List<Contact> contacts = findAllContactBelongingToUser(id);
        for(Contact contact: contacts){
            if(contact.getName() .equals(name))return true;
        }
        return false;

    }
    @Override
    public List<Contact> findAllContactBelongingToUser(Long id){
        List<Contact> contacts = new ArrayList<>();
        for(Contact contact: contactRepository.findAll()){
            if(contact.getContactAppId().equals(id))contacts.add(contact);
        }
        return contacts;

    }

    @Override
    public void editContact(EditContactRequest editContactRequest) {
        Contact contact = findContacts(editContactRequest.getUserId(),editContactRequest.getName());
        if(contact == null)throw new InvalidDetailsFormat("Contact doesn't exist");
        if(editContactRequest.getNewContactName() != null)contact.setName(editContactRequest.getNewContactName());
        if(editContactRequest.getNewPhoneNumber() != null)contact.setPhoneNumber(editContactRequest.getNewPhoneNumber());
        contactRepository.save(contact);
    }
    @Override
    public Contact findContacts(Long id, String name){
        List <Contact> userContact = findAllContactBelongingToUser(id);
        for(Contact contact: userContact){
            if(contact.getName().equals(name))return contact;
        }
        throw new InvalidContactDetails("contact doesn't exit");
    }

    @Override
    public void deleteContact(Long id, String name) {
        Contact contact = findContacts(id, name);
        contactRepository.delete(contact);
    }

    @Override
    public void deleteAllContact(Long id) {
        List<Contact> contacts = findAllContactBelongingToUser(id);
        if(contacts.isEmpty())throw new InvalidContactDetails("No contact saved under this id");
        contactRepository.deleteAll(contacts);
    }

    @Override
    public void blockContact(Long id, String contactName) {
        Contact contact = findContacts(id,contactName);
        if(contact.isBlocked())throw new ActionDoneException("Contact has been blocked already");
        contact.setBlocked(true);
        contactRepository.save(contact);
    }

    @Override
    public void unBlockContact(long id, String contactName) {
        Contact contact = findContacts(id,contactName);
        if(!contact.isBlocked())throw new ActionDoneException("Contact is not blocked");
        contact.setBlocked(false);
        contactRepository.save(contact);
    }
}
