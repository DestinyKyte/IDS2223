package it.unicam.cs.ids.loyaltyPlatform.email;

import org.springframework.beans.factory.annotation.Autowired;
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
        // TODO l'email si puo' modificare solo se non appartiene a nessuna campagna
        Email emailToUpdate = this.emailRepository.findById(id).orElseThrow();
        emailToUpdate.setTarget(email.getTarget());
        emailToUpdate.setContent(email.getContent());
        emailToUpdate.willBeSentAgain(email.willBeSentAgain());
        emailToUpdate.setFrequency(email.getFrequency());
        emailToUpdate.willBeSentImmediately(email.willBeSentImmediately());
        emailToUpdate.setDate(email.getDate());
        emailToUpdate.setStyling(email.getStyling());
        return this.emailRepository.save(emailToUpdate);
    }

    public Email deleteEmail(Long id){
        // TODO l'email si puo' cancellare solo se non appartiene a nessuna campagna
        Email email = this.emailRepository.findById(id).orElseThrow();
        this.emailRepository.deleteById(id);
        return email;
    }
}
