package it.unicam.cs.ids.loyaltyPlatform.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public Iterable<Email> getAllEmails(){
        return this.emailRepository.findAll();
    }

    public Email createEmail(Email email){
        return this.emailRepository.save(email);
    }

    public Email getEmail(Long id){
        return this.emailRepository.findById(id).orElseThrow();
    }

    public Email modifyEmail(Long id, Email email){
        Email emailToUpdate = this.emailRepository.findById(id).orElseThrow();
        emailToUpdate.setTarget(email.getTarget());
        emailToUpdate.setContent(email.getContent());
        emailToUpdate.setFrequency(email.getFrequency());
        emailToUpdate.setDate(email.getDate());
        emailToUpdate.setStyling(email.getStyling());
        return this.emailRepository.save(emailToUpdate);
    }

    public ResponseEntity<Email> deleteEmail(Long id){
        Email email = this.emailRepository.findById(id).orElseThrow();
        this.emailRepository.deleteById(id);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }
}
