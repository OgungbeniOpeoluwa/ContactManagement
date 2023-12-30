package org.example.dto.request;

import lombok.Data;

@Data
public class AddContactRequest {
    private Long id;
    private String name;
    private String phoneNumber;

}
