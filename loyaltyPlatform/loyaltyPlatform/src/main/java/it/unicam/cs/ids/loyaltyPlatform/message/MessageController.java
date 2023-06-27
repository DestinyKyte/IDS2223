package it.unicam.cs.ids.loyaltyPlatform.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public Iterable<Message> getAllMessages(){
        return this.messageService.getAllMessages();
    }

    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message message){
        return this.messageService.createMessage(message);
    }

    @GetMapping("messages/{id}")
    public Message getMessage(@PathVariable Long id){
        return this.messageService.getMessage(id);
    }

    @PutMapping("/messages/{id}")
    public Message modifyMessage(@PathVariable Long id, @RequestBody Message message){
        return this.messageService.modifyMessage(id, message);
    }

    @DeleteMapping("/messages/{id}")
    public void deleteMessage(@PathVariable Long id){
        this.messageService.deleteMessage(id);
    }
}
