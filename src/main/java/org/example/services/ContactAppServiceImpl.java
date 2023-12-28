package org.example.services;

import org.example.Exception.InvalidDetailsFormat;
import org.example.Exception.InvalidLoginDetailsException;
import org.example.Exception.UserExistException;
import org.example.data.model.Contact;
import org.example.data.model.ContactApp;
import org.example.data.repository.ContactAppRepository;
import org.example.dto.AddContactRequest;
import org.example.dto.EditContactRequest;
import org.example.dto.LoginRequest;
import org.example.dto.RegisterRequest;
import org.example.util.EncryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.example.util.Verification.*;

@Service
public class ContactAppServiceImpl implements ContactAppService{
    @Autowired
    ContactAppRepository userRepository;
    @Autowired
    ContactService contactService;

    @Override
    public Long register(RegisterRequest registerRequest) {
        if(!userExist(registerRequest.getEmail()))throw new UserExistException("user already exist");
        if(!(verifyEmail(registerRequest.getEmail())))throw new InvalidDetailsFormat("Wrong Email Format");
        if(!(verifyPassword(registerRequest.getPassword())))throw new InvalidDetailsFormat("Invalid Password");
        if(!(verifyPhoneNumber(registerRequest.getPhoneNumber())))throw new InvalidDetailsFormat("Invalid Phone Number");
        ContactApp user = new ContactApp();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(EncryptPassword.hashPassword(registerRequest.getPassword()));
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Optional<ContactApp> user = userRepository.findById(loginRequest.getId());
        if(user.isEmpty())throw new InvalidLoginDetailsException("Invalid details");
        verifyLogin(loginRequest.getPassword(),user.get().getPassword());
        user.get().setLocked(false);
        userRepository.save(user.get());

    }

    @Override
    public void addContact(AddContactRequest contact) {
        Optional<ContactApp> user = userRepository.findById(contact.getId());
        if(user.isEmpty())throw new InvalidLoginDetailsException("Invalid details");
        isLocked(contact.getId());
        phoneNumberException(contact.getPhoneNumber());
        contactService.addContact(contact);
    }

    @Override
    public void editContact(EditContactRequest editContactRequest) {
        Optional<ContactApp> contactApp = userRepository.findById(editContactRequest.getUserId());
        if(contactApp.isEmpty())throw new InvalidLoginDetailsException("Invalid details");
        isLocked(editContactRequest.getUserId());
        if(editContactRequest.getNewPhoneNumber() != null)phoneNumberException(editContactRequest.getNewPhoneNumber());
        contactService.editContact(editContactRequest);


    }
    private void phoneNumberException(String phoneNumber){
        if(!verifyPhoneNumber(phoneNumber)) throw new InvalidDetailsFormat("Wrong phone number format");
    }

    @Override
    public Contact findContact(long id, String name) {
        Optional<ContactApp> contactApp = userRepository.findById(id);
        if(contactApp.isEmpty())throw new InvalidLoginDetailsException("Invalid details");
        return contactService.findContacts(id,name);

    }

    @Override
    public List<Contact> findAllContact(Long id) {
        Optional<ContactApp> user = userRepository.findById(id);
        if(user.isEmpty())throw new InvalidLoginDetailsException("Invalid details");
        isLocked(id);
        return contactService.findAllContactBelongingToUser(id);
    }


    private void isLocked(Long id){
        Optional <ContactApp> user = userRepository.findById(id);
        if(user.get().isLocked())throw new InvalidLoginDetailsException("Kindly Login!!");
    }

    public boolean userExist(String email){
        ContactApp user = userRepository.findByEmail(email);
        return user == null;
    }
    private void verifyLogin(String password,String oldPassword){
        String salt = getExitedPasswordSaltValue(oldPassword);
        String securePassword = clearSaltValueInPassword(oldPassword) ;
        if(!securePassword.equals(EncryptPassword.securePassword(password,salt)))throw new InvalidLoginDetailsException("Invalid Details");


    }
}
