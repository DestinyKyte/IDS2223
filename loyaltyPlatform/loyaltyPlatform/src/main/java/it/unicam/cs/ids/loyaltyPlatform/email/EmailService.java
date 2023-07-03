package it.unicam.cs.ids.loyaltyPlatform.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

    public ResponseEntity<Email> getEmail(Long id){
        try {
            return new ResponseEntity<>(this.emailRepository.findById(id).orElseThrow(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Email(), HttpStatus.NOT_FOUND);
        }
    }

    // TODO l'email si puo' modificare solo se non appartiene ad alcuna campagna oppure se appartiene ad una campagna privata
    public ResponseEntity<Email> modifyEmail(Long id, Email email){
        Email emailToUpdate;
        try{
            emailToUpdate = this.emailRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Email(), HttpStatus.NOT_FOUND);
        }
        emailToUpdate.setTarget(email.getTarget());
        emailToUpdate.setContent(email.getContent());
        emailToUpdate.setToDeliverAgain(email.isToDeliverAgain());
        emailToUpdate.setFrequency(email.getFrequency());
        emailToUpdate.setToDeliverImmediately(email.isToDeliverImmediately());
        emailToUpdate.setDate(email.getDate());
        emailToUpdate.setStyle(email.getStyle());
        return new ResponseEntity<>(this.emailRepository.save(emailToUpdate), HttpStatus.OK);
    }

    // TODO l'email si puo' modificare solo se non appartiene ad alcuna campagna oppure se appartiene ad una campagna privata
    public ResponseEntity<Email> deleteEmail(Long id){
        Email email;
        try{
            email = this.emailRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<Email>(new Email(), HttpStatus.NOT_FOUND);
        }
        this.emailRepository.deleteById(id);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }
}
