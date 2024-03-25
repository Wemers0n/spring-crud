package com.ghostdev.springcrud.controller;

import com.ghostdev.springcrud.entity.Contact;
import com.ghostdev.springcrud.repository.ContactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private ContactRepository repository;

    ContactController(ContactRepository contactRepository){
        this.repository = contactRepository;
    }

    @GetMapping
    public List findAll(){
        return  repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> findById(@PathVariable long id){
        return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contact create(@RequestBody Contact contact){
        return repository.save(contact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> update(@PathVariable("id") long id, @RequestBody Contact contact){
        return repository.findById(id).map(record -> {
            record.setNome(contact.getNome());
            record.setEmail(contact.getEmail());
            record.setPhone(contact.getPhone());
            Contact updated = repository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        return repository.findById(id).map(record -> {repository.deleteById(id);
        return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
