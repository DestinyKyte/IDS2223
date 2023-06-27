package it.unicam.cs.ids.loyaltyPlatform.consumer;

import it.unicam.cs.ids.loyaltyPlatform.LoyaltyPlatformApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    // TODO riferimento a paymentManager

    public Iterable<Consumer> getAllConsumers(){
        return this.consumerRepository.findAll();
    }

    public Consumer createConsumer(Consumer consumer){
        if(LoyaltyPlatformApplication.checkCredentials(consumer.getUsername(), consumer.getPassword())){
            return this.consumerRepository.save(consumer);
        }
        return null;
    }

    public Consumer getConsumer(Long id){
        return this.consumerRepository.findById(id).orElseThrow();
    }

    public Consumer modifyConsumer(Long id, Consumer consumer){
        Consumer consumerToUpdate = this.consumerRepository.findById(id).orElseThrow();
        if(LoyaltyPlatformApplication.checkCredentials(consumer.getUsername(), consumer.getPassword())){
            consumerToUpdate.setUsername(consumer.getUsername());
            consumerToUpdate.setPassword(consumer.getPassword());
            consumerToUpdate.setPayments(consumer.getPayments());
            return this.consumerRepository.save(consumerToUpdate);
        }
        return null;
    }

    public void deleteConsumer(Long id){
        this.consumerRepository.deleteById(id);
    }
}
