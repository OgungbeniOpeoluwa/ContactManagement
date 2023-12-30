package org.example.dto.request;

import lombok.Data;

@Data
public class EditProfileRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
