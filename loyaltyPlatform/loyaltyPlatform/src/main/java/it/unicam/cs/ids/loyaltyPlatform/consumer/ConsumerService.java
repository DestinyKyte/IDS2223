package it.unicam.cs.ids.loyaltyPlatform.consumer;

import it.unicam.cs.ids.loyaltyPlatform.LoyaltyPlatformApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    public Iterable<Consumer> getAllConsumers(){
        return this.consumerRepository.findAll();
    }

    public ResponseEntity<Consumer> createConsumer(Consumer consumer){
        if(this.checkCredentials(consumer.getUsername(), consumer.getPassword())){
            return new ResponseEntity<Consumer>(this.consumerRepository.save(consumer), HttpStatus.OK);
        }
        return new  ResponseEntity<Consumer>(new Consumer(), HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<Consumer> getConsumer(Long id){
        try{
            return new ResponseEntity<>(this.consumerRepository.findById(id).orElseThrow(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Consumer>(new Consumer(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Consumer> modifyConsumer(Long id, Consumer consumer){
        Consumer consumerToUpdate;
        try{
            consumerToUpdate = this.consumerRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<Consumer>(new Consumer(), HttpStatus.NOT_FOUND);
        }
        if(!consumer.getUsername().equals(consumerToUpdate.getUsername())){
            if(this.checkUsername(consumer.getUsername())){
                consumerToUpdate.setUsername(consumer.getUsername());
            } else {
                return new ResponseEntity<>(new Consumer(), HttpStatus.NOT_ACCEPTABLE);
            }
        }
        if(LoyaltyPlatformApplication.checkPassword(consumer.getPassword())){
            consumerToUpdate.setPassword(consumer.getPassword());
        } else {
            return new ResponseEntity<>(new Consumer(), HttpStatus.NOT_ACCEPTABLE);
        }
        consumerToUpdate.setPhoneNumber(consumer.getPhoneNumber());
        consumerToUpdate.setEmailAddress(consumer.getEmailAddress());
        consumerToUpdate.setPayments(consumer.getPayments());
        consumerToUpdate.setName(consumer.getName());
        consumerToUpdate.setSurname(consumer.getSurname());
        return new ResponseEntity<>(this.consumerRepository.save(consumerToUpdate), HttpStatus.OK);
    }

    public ResponseEntity<Consumer> deleteConsumer(Long id){
        Consumer consumer;
        try{
            consumer = this.consumerRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<Consumer>(new Consumer(), HttpStatus.NOT_FOUND);
        }
        this.consumerRepository.deleteById(id);
        return new ResponseEntity<>(consumer, HttpStatus.OK);
    }

    private boolean checkCredentials(String username, String password){
        return this.checkUsername(username) && LoyaltyPlatformApplication.checkPassword(password);
    }

    private boolean checkUsername(String username){
        for (Consumer consumer : this.getAllConsumers()) {
            if (consumer.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

}
