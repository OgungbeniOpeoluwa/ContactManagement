package org.example.services;

import lombok.extern.slf4j.Slf4j;
import org.example.Exception.InvalidDetailsFormat;
import org.example.Exception.InvalidLoginDetailsException;
import org.example.Exception.UserExistException;
import org.example.data.model.Contact;
import org.example.data.model.ContactApp;
import org.example.data.repository.ContactAppRepository;
import org.example.dto.request.*;
import org.example.Exception.ActionDoneException;
import org.example.util.EncryptPassword;
import org.example.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.example.util.Verification.*;

@Service
@Slf4j
public class ContactAppServiceImpl implements ContactAppService{
    @Autowired
    ContactAppRepository userRepository;
    @Autowired
    ContactService contactService;

    @Override
    public Long register(RegisterRequest registerRequest) {
        checkUserEligibility(registerRequest);
        ContactApp user = Mapper.mapRegister(registerRequest);
        userRepository.save(user);
        return user.getId();
    }
    private void checkUserEligibility(RegisterRequest registerRequest){
        if(!userExist(registerRequest.getEmail()))throw new UserExistException("user already exist");
        if(!(verifyEmail(registerRequest.getEmail())))throw new InvalidDetailsFormat("Wrong Email Format");
        if(!(verifyPassword(registerRequest.getPassword())))throw new InvalidDetailsFormat("Invalid Password");
        if(!(verifyPhoneNumber(registerRequest.getPhoneNumber())))throw new InvalidDetailsFormat("Invalid Phone Number");
    }

    @Override
    public void login(LoginRequest loginRequest) {
        Optional<ContactApp> user = userRepository.findById(loginRequest.getId());
        if(user.isEmpty())throw new InvalidLoginDetailsException("Invalid details");
        verifyPasswordDetails(user.get().getId(),loginRequest.getPassword());
        if(!user.get().isLocked()) throw  new ActionDoneException("You have already login");
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
        checkValid(editContactRequest.getUserId());
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

    @Override
    public void editProfile(EditProfileRequest profile) {
        checkValid(profile.getId());
        ContactApp contactApp = viewProfile(profile.getId());
        if(profile.getFirstName() != null) contactApp.setFirstName(profile.getFirstName());
        if(profile.getLastName() != null) contactApp.setLastName(profile.getLastName());
        if(profile.getPhoneNumber() != null)phoneNumberException(profile.getPhoneNumber());
        contactApp.setPhoneNumber(profile.getPhoneNumber());
        userRepository.save(contactApp);
    }

    private void userExit(Long id){
        Optional<ContactApp> user = userRepository.findById(id);
        if(user.isEmpty())throw new UserExistException("Invalid details");

    }

    @Override
    public ContactApp viewProfile(Long id) {
        Optional<ContactApp> user = userRepository.findById(id);
        if(user.isEmpty())throw new InvalidLoginDetailsException("Invalid details");
        isLocked(id);
        return user.get();
    }

    @Override
    public void deleteContact(Long id, String name) {
        checkValid(id);
        contactService.deleteContact(id,name);

    }

    @Override
    public void deleteAllContact(Long id) {
        checkValid(id);
        contactService.deleteAllContact(id);

    }

    @Override
    public void deleteAccount(Long id) {
        checkValid(id);
        ContactApp contactApp = viewProfile(id);
        if(contactApp == null)throw  new UserExistException("user doesn't exist");
        userRepository.delete(contactApp);

    }

    @Override
    public void resetPassword(Long id, String oldPassword, String newPassword) {
        log.info("1");
        Optional<ContactApp> contactApp = userRepository.findById(id);
        if(contactApp.isEmpty())throw new UserExistException("Invalid details");
        log.info("2");
        verifyPasswordDetails(id,oldPassword);
        log.info("3");
        if(!verifyPassword(newPassword)) throw new InvalidDetailsFormat("wrong password format");
        log.info("4");
        contactApp.get().setPassword(newPassword);
        log.info("5");
        userRepository.save(contactApp.get());



    }

    @Override
    public void resetEmail(Long id, String oldEmail, String newEMail) {
        Optional <ContactApp> user = userRepository.findById(id);
        if(user.get().isLocked())throw new UserExistException("user doesn't exist");
        isLocked(id);
        if(!user.get().getEmail().equals(oldEmail))throw new InvalidDetailsFormat("Incorrect Email");
        if(!verifyEmail(newEMail)) throw new InvalidDetailsFormat("Invalid Email Format");
        user.get().setEmail(newEMail);
        userRepository.save(user.get());

    }

    @Override
    public void blockedContact(Long id, String contactName) {
        checkValid(id);
        contactService.blockContact(id,contactName);
    }

    @Override
    public void unBlockContact(long id, String contactName) {
        checkValid(id);
        contactService.unBlockContact(id,contactName);
    }

    private void checkValid(long id) {
        userExit(id);
        isLocked(id);
    }


    private void isLocked(Long id){
        Optional <ContactApp> user = userRepository.findById(id);
        if(user.get().isLocked())throw new InvalidLoginDetailsException("Kindly Login!!");
    }

    public boolean userExist(String email){
        ContactApp user = userRepository.findByEmail(email);
        return user == null;
    }
    private void  verifyPasswordDetails(Long id,String newPassword){
        Optional<ContactApp> user = userRepository.findById(id);
        String salt = getExitedPasswordSaltValue(user.get().getPassword());
        String oldPassword = clearSaltValueInPassword(user.get().getPassword()) ;
        if(!oldPassword.equals(EncryptPassword.securePassword(newPassword,salt)))throw new InvalidDetailsFormat("Invalid Details");
    }
}
