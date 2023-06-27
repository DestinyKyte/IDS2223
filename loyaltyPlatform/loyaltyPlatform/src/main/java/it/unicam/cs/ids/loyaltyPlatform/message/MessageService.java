package it.unicam.cs.ids.loyaltyPlatform.message;

import org.springframework.beans.factory.annotation.Autowired;
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
        Message messageToUpdate = this.messageRepository.findById(id).orElseThrow();
        messageToUpdate.setTarget(message.getTarget());
        messageToUpdate.setContent(message.getContent());
        messageToUpdate.setFrequency(message.getFrequency());
        messageToUpdate.setDate(message.getDate());
        return this.messageRepository.save(messageToUpdate);
    }

    public void deleteMessage(Long id){
        this.messageRepository.deleteById(id);
    }
}
