package org.example.services;


import jakarta.transaction.Transactional;
import org.example.Exception.InvalidContactDetails;
import org.example.Exception.InvalidDetailsFormat;
import org.example.Exception.InvalidLoginDetailsException;
import org.example.Exception.UserExistException;
import org.example.data.repository.ContactAppRepository;
import org.example.data.repository.ContactRepository;
import org.example.dto.request.*;
import org.example.dto.response.RegisterResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class ContactAppServiceImplTest {
@Autowired
   private ContactAppService userService;
@Autowired
ContactAppRepository userRepository;
@Autowired
    ContactRepository contactRepository;

@AfterEach
public  void doThisAfter(){
    userRepository.deleteAll();
    contactRepository.deleteAll();
}
private RegisterRequest registerRequest;
private LoginRequest loginRequest;
@BeforeEach
public void setUp(){
    registerRequest = new RegisterRequest();
    registerRequest.setFirstName("opeoluwa");
    registerRequest.setLastName("ogungbeni");
    registerRequest.setPassword("Opemip@14");
    registerRequest.setPhoneNumber("+2347066221008");
    registerRequest.setEmail("opeoluwaagnes@gmail.com");

}
@BeforeEach
public void setUpLoginDetails(){
    loginRequest = new LoginRequest();
    loginRequest.setId(1L);
    loginRequest.setPassword("Opemip@14");
}

    @Test
    public void testThatWhenUserRegisterWithWrongEmailFormatThrowsException(){
        registerRequest.setEmail("opemnifine");
        assertThrows(InvalidDetailsFormat.class,()->userService.register(registerRequest));
    }
    @Test
    public void testThatWhenUserRegisterWithWrongFormatOfPhoneNumberItThrowsError(){
        registerRequest.setPhoneNumber("+09078654362");
        assertThrows(InvalidDetailsFormat.class,()->userService.register(registerRequest));
    }
    @Test
    public void testThatPasswordInValidPasswordThrowsException(){
        registerRequest.setPassword("123");
        assertThrows(InvalidDetailsFormat.class,()->userService.register(registerRequest));
    }
    @Test
    public void testThatWhenUserRegisterUserIdIs1(){
       RegisterResponse register = userService.register(registerRequest);
        assertNotNull(register);
        assertEquals(1,userRepository.count());
    }
    @Test
    public void testWhenThatUserRegisterTwiceItThrowsExceptionThatTheyExist(){
    userService.register(registerRequest);
    assertThrows(UserExistException.class,()->userService.register(registerRequest));
}
@Test
    public void testThatUserCantLoginWithWrongPassword(){
    RegisterResponse id = userService.register(registerRequest);
    loginRequest.setId(id.getId());
        loginRequest.setPassword("opemmuujg123@");
    assertThrows(InvalidDetailsFormat.class,()->userService.login(loginRequest));
}
@Test
    public void testThatWhenUserTryToLogInWithWrongIdThrowsAndException(){
    RegisterResponse registerId = userService.register(registerRequest);
        loginRequest.setId(registerId.getId()+1);
    assertThrows(InvalidLoginDetailsException.class,()->userService.login(loginRequest));
}
@Test
    public void testThatWhenUserAddContact_ContactRepositoryIncreaseBy1(){
   RegisterResponse register = userService.register(registerRequest);
    loginRequest.setId(register.getId());
    userService.login(loginRequest);
    AddContactRequest contact =  new AddContactRequest();
    contact.setId(register.getId());
    contact.setName("tolu shola");
    contact.setPhoneNumber("+34567812345");
    userService.addContact(contact);
    assertEquals(1,contactRepository.count());
}
@Test
    public void testThatWhenANamesSavedTwiceOnContactItThrowsException(){
    RegisterResponse registerId = userService.register(registerRequest);
    loginRequest.setId(registerId.getId());
    userService.login(loginRequest);
    AddContactRequest contact =  new AddContactRequest();
    contact.setId(registerId.getId());
    contact.setName("tolu shola");
    contact.setPhoneNumber("08066789056");
    userService.addContact(contact);
    assertThrows(InvalidDetailsFormat.class,()->userService.addContact(contact));
}
@Test
@DisplayName("test that when a user edit phone number that is saved, it reset the old phone number")
    public void testEdit(){
    RegisterResponse registerId = userService.register(registerRequest);
    loginRequest.setId(registerId.getId());
    userService.login(loginRequest);
    AddContactRequest contact =  new AddContactRequest();
    contact.setId(registerId.getId());
    contact.setName("tolu shola");
    contact.setPhoneNumber("08066789056");
    userService.addContact(contact);
    EditContactRequest editContactRequest = new EditContactRequest();
    editContactRequest.setName("tolu shola");
    editContactRequest.setUserId(registerId.getId());
    editContactRequest.setNewPhoneNumber("+2347066221008");
    userService.editContact(editContactRequest);
    assertEquals(editContactRequest.getNewPhoneNumber(),userService.findContact(registerId.getId(),"tolu shola").getPhoneNumber());
}
@Test
    public void testThatWhenTwoUserSaveContactsEachAndTheyFindAllContactRelatingThemItsTheSizeOfWhatTheySaved(){
    AddContactRequest addContactRequest = new AddContactRequest();
  RegisterResponse register =  userService.register(registerRequest);
  loginRequest.setId(register.getId());
    userService.login(loginRequest);
    addContactRequest.setId(register.getId());
    addContactRequest.setPhoneNumber("+234567899034");
    addContactRequest.setName("tobi shayo");
    userService.addContact(addContactRequest);

    registerRequest.setFirstName("Tayo");
    registerRequest.setLastName("Adegbebom");
    registerRequest.setPhoneNumber("08099344567");
    registerRequest.setEmail("opeoluwashola@gmail.com");
    registerRequest.setPassword("Opemi@12");
    RegisterResponse registerId = userService.register(registerRequest);

    loginRequest.setId(registerId.getId());
    loginRequest.setPassword("Opemi@12");
    userService.login(loginRequest);

    addContactRequest.setId(registerId.getId());
    addContactRequest.setPhoneNumber("+2345678990");
    addContactRequest.setName("tobi small");
    userService.addContact(addContactRequest);

    addContactRequest.setId(registerId.getId());
    addContactRequest.setPhoneNumber("+3458900021345");
    addContactRequest.setName("tolu");
    userService.addContact(addContactRequest);
    assertEquals(2,userService.findAllContact(registerId.getId()).size());
    assertEquals(1,userService.findAllContact(register.getId()).size());
}
@Test
    public void testThatUserCanEditTheirProfile(){
    RegisterResponse id = userService.register(registerRequest);
    loginRequest.setId(id.getId());
    userService.login(loginRequest);
    EditProfileRequest profile = new EditProfileRequest();
    profile.setId(id.getId());
    profile.setPhoneNumber("08152865402");
    userService.editProfile(profile);
    assertEquals("08152865402",userService.viewProfile(id.getId()).getPhoneNumber());
}
@Test
    public void testThatUserSaveTwoContactDeletedOneWhenWeFindAllContactSizeIsOne(){
    RegisterResponse id = userService.register(registerRequest);
    loginRequest.setId(id.getId());
    userService.login(loginRequest);
    AddContactRequest addContactRequest = new AddContactRequest();
    addContactRequest.setId(id.getId());
    addContactRequest.setPhoneNumber("+2345678990");
    addContactRequest.setName("tobi small");
    userService.addContact(addContactRequest);

    addContactRequest.setId(id.getId());
    addContactRequest.setPhoneNumber("+3458900021345");
    addContactRequest.setName("tolu");
    userService.addContact(addContactRequest);
    userService.deleteContact(id.getId(),"tobi small");
    assertEquals(1,userService.findAllContact(id.getId()).size());
    assertThrows(InvalidContactDetails.class,()->userService.deleteContact(id.getId(),"tobi small"));

}
@Test
    public void testThatWhenAUserDeleteAllContactSizeIs0(){
    RegisterResponse id = userService.register(registerRequest);
    loginRequest.setId(id.getId());
    userService.login(loginRequest);
    AddContactRequest addContactRequest = new AddContactRequest();
    addContactRequest.setId(id.getId());
    addContactRequest.setPhoneNumber("+2345678990");
    addContactRequest.setName("tobi small");
    userService.addContact(addContactRequest);
    userService.deleteAllContact(id.getId());
    assertThrows(InvalidContactDetails.class,()->userService.deleteAllContact(id.getId()));
}
@Test
    public void testThatAUserCanDeleteAccountFromContactApp(){
    RegisterResponse id = userService.register(registerRequest);
    loginRequest.setId(id.getId());
    userService.login(loginRequest);
    userService.deleteAccount(id.getId());
    assertThrows(UserExistException.class,()->userService.deleteAccount(id.getId()));
}
@Test
    public void testThatWhenUserEnterWrongOldPasswordToResetPasswordThrowsAnException(){
    RegisterResponse id = userService.register(registerRequest);
    loginRequest.setId(id.getId());
    userService.login(loginRequest);
    ResetPasswordRequest passwordRequest = new ResetPasswordRequest();
    passwordRequest.setOldPassword("wrongpassword");
    passwordRequest.setNewPassword("newPassword");
    assertThrows(InvalidDetailsFormat.class,()->userService.resetPassword(id.getId(),passwordRequest));
}
@Test
 public void testThatWhenUserProvideWrongEmailToResetItThrowsAnException(){
    RegisterResponse id = userService.register(registerRequest);
    loginRequest.setId(id.getId());
    userService.login(loginRequest);
    ResetEmailRequest emailRequest = new ResetEmailRequest();
    emailRequest.setOldEmail("opemip@gmail.com");
    emailRequest.setNewEmail("Opemipo@gmail.com");
    assertThrows(InvalidDetailsFormat.class,()->userService.resetEmail(id.getId(),emailRequest));
}





}