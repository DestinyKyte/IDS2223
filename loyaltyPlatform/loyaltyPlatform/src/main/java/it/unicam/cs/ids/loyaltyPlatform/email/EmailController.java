package it.unicam.cs.ids.loyaltyPlatform.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/emailMessages")
    public Iterable<Email> getAllEmails(){
        return this.emailService.getAllEmails();
    }

    @PostMapping("/emailMessages")
    public Email createEmail(@RequestBody Email email){
        return this.emailService.createEmail(email);
    }

    @GetMapping("/emailMessages/{id}")
    public Email getEmail(@PathVariable Long id){
        return this.emailService.getEmail(id);
    }

    @PutMapping("/emailMessages/{id}")
    public Email modifyEmail(@PathVariable Long id, @RequestBody Email email){
        return this.emailService.modifyEmail(id, email);
    }

    @DeleteMapping("/emailMessages/{id}")
    public void deleteEmail(@PathVariable Long id){
        this.emailService.deleteEmail(id);
    }
}
