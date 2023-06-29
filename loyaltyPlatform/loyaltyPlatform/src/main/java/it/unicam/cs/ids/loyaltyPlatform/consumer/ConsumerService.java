package it.unicam.cs.ids.loyaltyPlatform.consumer;

import it.unicam.cs.ids.loyaltyPlatform.LoyaltyPlatformApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    public Iterable<Consumer> getAllConsumers(){
        return this.consumerRepository.findAll();
    }

    public Consumer createConsumer(Consumer consumer){
        if(this.checkCredentials(consumer.getUsername(), consumer.getPassword())){
            return this.consumerRepository.save(consumer);
        }
        return null;
    }

    public Consumer getConsumer(Long id){
        return this.consumerRepository.findById(id).orElseThrow();
    }

    public Consumer modifyConsumer(Long id, Consumer consumer){
        Consumer consumerToUpdate = this.consumerRepository.findById(id).orElseThrow();
        if(this.checkCredentials(consumer.getUsername(), consumer.getPassword())){
            consumerToUpdate.setUsername(consumer.getUsername());
            consumerToUpdate.setPassword(consumer.getPassword());
            consumerToUpdate.setPhoneNumber(consumer.getPhoneNumber());
            consumerToUpdate.setEmailAddress(consumer.getEmailAddress());
            consumerToUpdate.setPayments(consumer.getPayments());
            consumerToUpdate.setName(consumer.getName());
            consumerToUpdate.setSurname(consumer.getSurname());
            return this.consumerRepository.save(consumerToUpdate);
        }
        return null;
    }

    public ResponseEntity<Consumer> deleteConsumer(Long id){
        Consumer consumer = this.consumerRepository.findById(id).orElseThrow();
        this.consumerRepository.deleteById(id);
        return new ResponseEntity<>(consumer, HttpStatus.OK);
    }

    private boolean checkCredentials(String username, String password){
        Iterator<Consumer> employeeIterator = this.getAllConsumers().iterator();
        while(employeeIterator.hasNext()){
            if(employeeIterator.next().getUsername().equals(username)){
                return false;
            }
        }
        return LoyaltyPlatformApplication.checkPassword(password);
    }

}
