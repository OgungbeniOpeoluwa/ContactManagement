package org.example.dto.request;

import lombok.Data;

@Data
public class ResetEmailRequest {
    private String oldEmail;
    private String newEmail;
}
