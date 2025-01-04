package com.lms.lms.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ContactRequest {
    private String name;
    private String role;
    private String phone;
    private String email;
}
