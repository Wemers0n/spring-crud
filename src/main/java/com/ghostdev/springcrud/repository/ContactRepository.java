package com.ghostdev.springcrud.repository;

import com.ghostdev.springcrud.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
