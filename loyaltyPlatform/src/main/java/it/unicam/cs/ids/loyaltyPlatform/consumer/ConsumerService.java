package it.unicam.cs.ids.loyaltyPlatform.consumer;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
@Service
public class ConsumerService {
    private final ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public Set<LoyaltyProgram> getLoyaltyProgramsOfConsumer(Long id) {
        Consumer consumer = consumerRepository.findById(id).get();
        consumer.getId();
        return null;
    }
}
