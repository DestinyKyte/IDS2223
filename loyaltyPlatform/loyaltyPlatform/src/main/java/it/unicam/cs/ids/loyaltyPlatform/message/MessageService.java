package it.unicam.cs.ids.loyaltyPlatform.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

    public ResponseEntity<Message> getMessage(Long id){
        try{
            return new ResponseEntity<>(this.messageRepository.findById(id).orElseThrow(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Message(), HttpStatus.NOT_FOUND);
        }
    }

    // TODO il messaggio si puo' modificare solo se non appartiene ad alcuna campagna oppure se appartiene ad una campagna privata
    public ResponseEntity<Message> modifyMessage(Long id, Message message){
        Message messageToUpdate;
        try{
            messageToUpdate = this.messageRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Message(), HttpStatus.NOT_FOUND);
        }
        messageToUpdate.setTarget(message.getTarget());
        messageToUpdate.setContent(message.getContent());
        messageToUpdate.setToDeliverAgain(message.isToDeliverAgain());
        messageToUpdate.setFrequency(message.getFrequency());
        messageToUpdate.setToDeliverImmediately(message.isToDeliverImmediately());
        messageToUpdate.setDate(message.getDate());
        return new ResponseEntity<>(this.messageRepository.save(messageToUpdate), HttpStatus.OK);
    }

    // TODO il messaggio si puo' modificare solo se non appartiene ad alcuna campagna oppure se appartiene ad una campagna privata
    public ResponseEntity<Message> deleteMessage(Long id){
        Message message;
        try {
            message = this.messageRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Message(), HttpStatus.NOT_FOUND);
        }
        this.messageRepository.deleteById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
