package org.example.controller;

import org.apache.coyote.Response;
import org.example.Exception.ContactAppException;
import org.example.dto.request.*;
import org.example.dto.response.*;
import org.example.services.ContactAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")

public class ContactAppController {
    @Autowired
    ContactAppService contactAppService;
    @PostMapping("/users")
    public ResponseEntity<?>register(@RequestBody RegisterRequest registerRequest){
        RegisterResponse registerResponse = new RegisterResponse();
        try{
            registerResponse.setId(contactAppService.register(registerRequest));
            registerResponse.setMessage("Account has been created");
           return new ResponseEntity<>(new ApiResponse(registerResponse,true), HttpStatus.ACCEPTED);
        }
        catch(ContactAppException exception){
            return new ResponseEntity<>(new ApiResponse(exception.getMessage(),false),HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/users/{id}")
    public ResponseEntity<?>login(@PathVariable("id") Long id,@RequestParam(name="password") String password){
        LoginRequest loginRequest = new LoginRequest(id,password);
        LoginResponse response = new LoginResponse();
        try{
            contactAppService.login(loginRequest);
            response.setMessage("you have successfully login into your account");
            return new ResponseEntity<>(new ApiResponse(response,true),HttpStatus.ACCEPTED);
        }catch (ContactAppException exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(response,false),HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/contact")
    public ResponseEntity<?> addContact(@RequestBody AddContactRequest addContactRequest){
        AddContactResponse addContactResponse = new AddContactResponse();
        try{
            contactAppService.addContact(addContactRequest);
            addContactResponse.setMessage("Contact added");
            return new ResponseEntity<>(new ApiResponse(addContactResponse,true),HttpStatus.CREATED);
        }
        catch (ContactAppException exception){
            addContactResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(addContactResponse,false),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/contact")
    public ResponseEntity<?> editContact(@RequestBody EditContactRequest editContactRequest){
        EditContactResponse response = new EditContactResponse();
        try{
            contactAppService.editContact(editContactRequest);
            response.setMessage("contact has been updated");
            return new ResponseEntity<>(new ApiResponse(response,true),HttpStatus.ACCEPTED);
        }
        catch(ContactAppException exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(response,false),HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/profile")
    public ResponseEntity<?> editProfile(@RequestBody EditProfileRequest editProfileRequest){
        ProfileResponse response = new ProfileResponse();
        try{
            contactAppService.editProfile(editProfileRequest);
            response.setMessage("profile updated");
            return new ResponseEntity<>(new ApiResponse(response,true),HttpStatus.OK);
        }catch (ContactAppException exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(response,false),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/contacts/{id}")
    public ResponseEntity<?> viewAllContacts(@PathVariable("id") Long id){
        ViewContactResponse response = new ViewContactResponse();
        try{
            response.setMessage(contactAppService.findAllContact(id));
            return new ResponseEntity<>(new ApiResponse(response,true),HttpStatus.ACCEPTED);
        }
        catch (ContactAppException exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(response,false),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/contact/{id}")
    public ResponseEntity<?> viewAContact(@PathVariable("id") Long id,@RequestParam (name = "name")String name){
        ViewAContactResponse response = new ViewAContactResponse();
        try{
            response.setMessage(contactAppService.findContact(id,name));
            return new ResponseEntity<>(new ApiResponse(response,true),HttpStatus.OK);
        }
        catch (ContactAppException exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(response,false),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/profile/{id}")
    public  ResponseEntity <?> viewProfile(@PathVariable("id")Long id){
        ViewProfileResponse response = new ViewProfileResponse();
        try {
            response.setMessage(contactAppService.viewProfile(id));
            return new ResponseEntity<>(new ApiResponse(response,true),HttpStatus.OK);
        }
        catch (ContactAppException exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(response,false),HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/password/{id}")
    public ResponseEntity<?> resetPassword(@PathVariable("id")Long id,@RequestBody ResetPasswordRequest request){
        ResetPasswordResponse response = new ResetPasswordResponse();
        try{
            contactAppService.resetPassword(id,request.getOldPassword(),request.getNewPassword());
            response.setMessage("password has been updated");
            return new ResponseEntity<>(new ApiResponse(response,true),HttpStatus.ACCEPTED);
        }
        catch (ContactAppException exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(response,false),HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/email/{id}")
    public ResponseEntity<?> resetEmail(@PathVariable("id")Long id, @RequestBody ResetEmailRequest emailRequest){
        ResetEmailResponse response = new ResetEmailResponse();
        try{
            contactAppService.resetEmail(id,emailRequest.getOldEmail(), emailRequest.getNewEmail());
            response.setMessage("Your email has been updated successfully");
            return new ResponseEntity<>(new ApiResponse(response,true),HttpStatus.ACCEPTED);
        }
        catch (ContactAppException exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(response,false),HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/contact/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable("id")Long id, @RequestParam(name = "contactName")String contactName){
        DeleteContactResponse deleteContactResponse = new DeleteContactResponse();
        try{
            contactAppService.deleteContact(id,contactName);
            deleteContactResponse.setMessage("Contact has been deleted");
            return new ResponseEntity<>(new ApiResponse(deleteContactResponse,true),HttpStatus.ACCEPTED);
        }
        catch (ContactAppException exception){
            deleteContactResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(deleteContactResponse,false),HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("contacts/{id}")
    public ResponseEntity<?> deleteAllContacts(@PathVariable("id")Long id){
        DeleteAllResponse response = new DeleteAllResponse();
        try{
            contactAppService.deleteAllContact(id);
            response.setMessage("All contacts Deleted successfully");
            return new ResponseEntity<>(new ApiResponse(response,true),HttpStatus.ACCEPTED);
        }
        catch (ContactAppException exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(response,false),HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id")Long id){
        DeleteAccountResponse response = new DeleteAccountResponse();
        try{
            contactAppService.deleteAccount(id);
            response.setMessage("Account has been Deleted");
            return new ResponseEntity<>(new ApiResponse(response,true),HttpStatus.ACCEPTED);
        }
        catch (ContactAppException ex){
            response.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(response,false),HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/contact/{id}")
    public ResponseEntity<?> blockContact(@PathVariable("id")Long id, @RequestParam(name= "contactName")String contactName){
        BlockContactResponse  response = new BlockContactResponse();
        try{
            contactAppService.blockedContact(id,contactName);
            response.setMessage("contact is Blocked");
            return new ResponseEntity<>(new ApiResponse(response,true),HttpStatus.ACCEPTED);
        }
        catch(ContactAppException ex){
            response.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(response,false),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/contact/{id}")
    public ResponseEntity<?> unBlockContact(@PathVariable("id")Long id,@RequestParam(name = "contactName")String contactName){
        UnblockContactResponse response = new UnblockContactResponse();
        try{
            contactAppService.unBlockContact(id,contactName);
            response.setMessage("Contact has been unblocked");
            return new ResponseEntity<>(new ApiResponse(response,true),HttpStatus.ACCEPTED);
        }
        catch (ContactAppException exception){
            response.setMessage(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(response,false),HttpStatus.BAD_REQUEST);
        }
    }



}
