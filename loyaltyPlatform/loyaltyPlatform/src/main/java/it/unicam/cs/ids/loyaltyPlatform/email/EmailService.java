package it.unicam.cs.ids.loyaltyPlatform.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public Iterable<Email> getAllEmailMessages(){
        return this.emailRepository.findAll();
    }

    public Email createEmailMessage(Email email){
        return this.emailRepository.save(email);
    }

    public Email getEmailMessage(Long id){
        return this.emailRepository.findById(id).orElseThrow();
    }

    public Email modifyEmailMessage(Long id, Email email){
        Email emailToUpdate = this.emailRepository.findById(id).orElseThrow();
        emailToUpdate.setTarget(email.getTarget());
        emailToUpdate.setContent(email.getContent());
        emailToUpdate.setFrequency(email.getFrequency());
        emailToUpdate.setDate(email.getDate());
        emailToUpdate.setStyling(email.getStyling());
        return this.emailRepository.save(emailToUpdate);
    }

    public void deleteEmailMessage(Long id){
        this.emailRepository.deleteById(id);
    }
}
