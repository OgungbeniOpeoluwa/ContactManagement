package org.example.dto;

import lombok.Data;

@Data
public class AddContactRequest {
    private Long id;
    private String name;
    private String phoneNumber;

}
