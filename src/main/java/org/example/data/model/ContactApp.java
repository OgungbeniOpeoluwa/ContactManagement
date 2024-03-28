package org.example.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ContactApp {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    @JsonIgnore
    private String  password;
    @JsonIgnore
    private boolean isLocked = true;
    @Override
    public String toString(){
        return String.format("""
                Full Name: %s %s
                Phone Number: %s
                Email: %s""",firstName,lastName,phoneNumber,email);
    }


}
