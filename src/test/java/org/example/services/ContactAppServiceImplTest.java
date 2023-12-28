package org.example.services;


import org.example.Exception.InvalidDetailsFormat;
import org.example.Exception.InvalidLoginDetailsException;
import org.example.Exception.UserExistException;
import org.example.data.repository.ContactAppRepository;
import org.example.data.repository.ContactRepository;
import org.example.dto.AddContactRequest;
import org.example.dto.EditContactRequest;
import org.example.dto.LoginRequest;
import org.example.dto.RegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//@TestPropertySource(locations = "classpath:test.properties")
//@Transactional
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
    registerRequest.setPassword("Opemip@143");
    registerRequest.setPhoneNumber("+2347066221008");
    registerRequest.setEmail("opeoluwaagnes@gmail.com");

}
@BeforeEach
public void setUpLoginDetails(){
    loginRequest = new LoginRequest();
    loginRequest.setId(1L);
    loginRequest.setPassword("Opemip@143");
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
       Long register = userService.register(registerRequest);
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
    userService.register(registerRequest);
        loginRequest.setPassword("opemmuujg123@");
    assertThrows(InvalidLoginDetailsException.class,()->userService.login(loginRequest));
}
@Test
    public void testThatWhenUserTryToLogInWithWrongIdThrowsAndException(){
    Long registerId = userService.register(registerRequest);
        loginRequest.setId(registerId+1);
    assertThrows(InvalidLoginDetailsException.class,()->userService.login(loginRequest));
}
@Test
    public void testThatWhenUserAddContact_ContactRepositoryIncreaseBy1(){
   Long register = userService.register(registerRequest);
    loginRequest.setId(register);
    userService.login(loginRequest);
    AddContactRequest contact =  new AddContactRequest();
    contact.setId(register);
    contact.setName("tolu shola");
    contact.setPhoneNumber("+34567812345");
    userService.addContact(contact);
    assertEquals(1,contactRepository.count());
}
@Test
    public void testThatWhenANamesSavedTwiceOnContactItThrowsException(){
    Long registerId = userService.register(registerRequest);
    loginRequest.setId(registerId);
    userService.login(loginRequest);
    AddContactRequest contact =  new AddContactRequest();
    contact.setId(registerId);
    contact.setName("tolu shola");
    contact.setPhoneNumber("08066789056");
    userService.addContact(contact);
    assertThrows(InvalidDetailsFormat.class,()->userService.addContact(contact));
}
@Test
@DisplayName("test that when a user edit phone number that is saved, it reset the old phone number")
    public void testEdit(){
    Long registerId = userService.register(registerRequest);
    userService.login(loginRequest);
    AddContactRequest contact =  new AddContactRequest();
    contact.setId(registerId);
    contact.setName("tolu shola");
    contact.setPhoneNumber("08066789056");
    userService.addContact(contact);
    EditContactRequest editContactRequest = new EditContactRequest();
    editContactRequest.setName("tolu shola");
    editContactRequest.setUserId(registerId);
    editContactRequest.setNewPhoneNumber("+2347066221008");
    userService.editContact(editContactRequest);
    assertEquals(editContactRequest.getNewPhoneNumber(),userService.findContact(registerId,"tolu shola").getPhoneNumber());
}
@Test
    public void testThatWhenTwoUserSaveContactsEachAndTheyFindAllContactRelatingThemItsTheSizeOfWhatTheySaved(){
    AddContactRequest addContactRequest = new AddContactRequest();
  Long register =  userService.register(registerRequest);
    userService.login(loginRequest);
    addContactRequest.setId(register);
    addContactRequest.setPhoneNumber("+234567899034");
    addContactRequest.setName("tobi shayo");
    userService.addContact(addContactRequest);

    registerRequest.setFirstName("Tayo");
    registerRequest.setLastName("Adegbebom");
    registerRequest.setPhoneNumber("08099344567");
    registerRequest.setEmail("opeoluwashola@gmail.com");
    registerRequest.setPassword("Opemi@1233");
    Long registerId = userService.register(registerRequest);

    loginRequest.setId(registerId);
    loginRequest.setPassword("Opemi@1233");
    userService.login(loginRequest);

    addContactRequest.setId(registerId);
    addContactRequest.setPhoneNumber("+2345678990");
    addContactRequest.setName("tobi small");
    userService.addContact(addContactRequest);

    addContactRequest.setId(registerId);
    addContactRequest.setPhoneNumber("+3458900021345");
    addContactRequest.setName("tolu");
    userService.addContact(addContactRequest);
    assertEquals(2,userService.findAllContact(registerId).size());
    assertEquals(1,userService.findAllContact(register).size());
}
@Test
    public void testThatUserCanEditTheirProfile(){
    userService.register(registerRequest);
    userService.login(loginRequest);
}



}