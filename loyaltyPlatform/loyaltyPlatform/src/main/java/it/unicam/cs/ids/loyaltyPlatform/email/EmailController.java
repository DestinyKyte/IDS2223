package it.unicam.cs.ids.loyaltyPlatform.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/emails")
    public Iterable<Email> getAllEmails(){
        return this.emailService.getAllEmails();
    }

    @PostMapping("/emails")
    public Email createEmail(@RequestBody Email email){
        return this.emailService.createEmail(email);
    }

    @GetMapping("/emails/{id}")
    public ResponseEntity<Email> getEmail(@PathVariable Long id){
        return this.emailService.getEmail(id);
    }

    @PutMapping("/emails/{id}")
    public ResponseEntity<Email> modifyEmail(@PathVariable Long id, @RequestBody Email email){
        return this.emailService.modifyEmail(id, email);
    }

    @DeleteMapping("/emails/{id}")
    public ResponseEntity<Email> deleteEmail(@PathVariable Long id){
        return this.emailService.deleteEmail(id);
    }
}
