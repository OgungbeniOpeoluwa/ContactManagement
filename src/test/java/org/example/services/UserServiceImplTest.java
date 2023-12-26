package org.example.services;

import org.example.Exception.InvalidDetailsFormat;
import org.example.data.repository.UserRepository;
import org.example.dto.RegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//@TestPropertySource(locations = "classpath:test.properties")
//@Transactional
class UserServiceImplTest {
@Autowired
   private UserService userService;
@Autowired
    UserRepository userRepository;

@AfterEach
public  void doThisAfter(){
    userRepository.deleteAll();
}
private RegisterRequest registerRequest;
@BeforeEach
public void setUp(){
    registerRequest = new RegisterRequest();
    registerRequest.setFirstName("opeoluwa");
    registerRequest.setLastName("ogungbeni");
    registerRequest.setPassword("Opemip@143");
    registerRequest.setPhoneNumber("+2347066221008");
    registerRequest.setEmail("opeoluwaagnes@gmail.com");

}

    @Test
    public void testThatWhenUserRegisterWithWrongEmailFormatThrowsException(){
        registerRequest.setEmail("opemnifine");
        assertThrows(InvalidDetailsFormat.class,()->userService.register(registerRequest));
    }
    @Test
    public void testThatWhenUserRegisterWithWrongFormatOfPhoneNumberItThrowsError(){
        registerRequest.setPhoneNumber("09078654362");
        assertThrows(InvalidDetailsFormat.class,()->userService.register(registerRequest));
    }
    @Test
    public void testThatPasswordInValidPasswordThrowsException(){
        registerRequest.setPassword("123");
        assertThrows(InvalidDetailsFormat.class,()->userService.register(registerRequest));
    }
    @Test
    public void testThatWhenUserRegisterUserIdIs1(){
        assertEquals(1,userService.register(registerRequest));
        assertEquals(1,userRepository.count());
    }

}