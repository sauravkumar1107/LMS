package com.lms.lms.repository;

import com.lms.lms.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String>  {
    List<Contact> findByRestId(String restId);
}
