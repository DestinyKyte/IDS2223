package it.unicam.cs.ids.loyaltyPlatform.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Iterable<Message> getAllMessages(){
        return this.messageRepository.findAll();
    }

    public Message createMessage(Message message){
        return this.messageRepository.save(message);
    }

    public Message getMessage(Long id){
        return this.messageRepository.findById(id).orElseThrow();
    }

    public Message modifyMessage(Long id, Message message){
        // TODO il messaggio si puo' modificare solo se non appartiene ad alcuna campagna
        Message messageToUpdate = this.messageRepository.findById(id).orElseThrow();
        messageToUpdate.setTarget(message.getTarget());
        messageToUpdate.setContent(message.getContent());
        messageToUpdate.setFrequency(message.getFrequency());
        messageToUpdate.setDate(message.getDate());
        return this.messageRepository.save(messageToUpdate);
    }

    public ResponseEntity<Message> deleteMessage(Long id){
        // TODO il messaggio si puo' eliminare solo se non appartiene ad alcuna campagna
        Message message = this.messageRepository.findById(id).orElseThrow();
        this.messageRepository.deleteById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
