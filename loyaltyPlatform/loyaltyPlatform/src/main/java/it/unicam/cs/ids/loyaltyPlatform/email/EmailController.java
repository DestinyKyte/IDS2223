package it.unicam.cs.ids.loyaltyPlatform.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/emailMessages")
    public Iterable<Email> getAllEmailMessages(){
        return this.emailService.getAllEmailMessages();
    }

    @PostMapping("/emailMessages")
    public Email createEmailMessage(@RequestBody Email email){
        return this.emailService.createEmailMessage(email);
    }

    @GetMapping("/emailMessages/{id}")
    public Email getEmailMessage(@PathVariable Long id){
        return this.emailService.getEmailMessage(id);
    }

    @PutMapping("/emailMessages/{id}")
    public Email modifyEmailMessage(@PathVariable Long id, @RequestBody Email email){
        return this.emailService.modifyEmailMessage(id, email);
    }

    @DeleteMapping("/emailMessages/{id}")
    public void deleteEmailMessage(@PathVariable Long id){
        this.emailService.deleteEmailMessage(id);
    }
}
