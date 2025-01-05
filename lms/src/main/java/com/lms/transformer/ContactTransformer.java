package com.lms.transformer;

import com.lms.model.Contact;
import com.lms.model.Role;
import com.lms.request.ContactRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactTransformer {
    public static List<Contact> contactsFromRequest(List<ContactRequest> contactRequests, String restId) {
        List<Contact> contacts = new ArrayList<>();
        for (ContactRequest contactRequest : contactRequests) {
            contacts.add(Contact.builder()
                            .role(Role.valueOf(contactRequest.getRole()))
                            .name(contactRequest.getName())
                            .phone(contactRequest.getPhone())
                            .email(contactRequest.getEmail())
                            .id(UUID.randomUUID().toString())
                            .restId(restId)
                    .build());
        }

        return contacts;
    }
}
