package it.unicam.cs.ids.loyaltyPlatform.subscription;

import it.unicam.cs.ids.loyaltyPlatform.consumer.Consumer;
import it.unicam.cs.ids.loyaltyPlatform.consumer.ConsumerService;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubscriptionService {
    private final ConsumerService consumerService;
    private final SubscriptionRepository subscriptionRepository;
    public SubscriptionService(ConsumerService consumerService, SubscriptionRepository subscriptionRepository){
        this.consumerService = consumerService;
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * If both the proposed subscription has both the consumer and the program that are valid
     * then a {@link Subscription} will be created.
     * Else nothing is created.
     * @param subscription A newly proposed subscription to be made.
     * @return A new subscription if it is valid. Meaning both party's IDs exists. Else return null.
     */
    public Subscription createSubscription(Subscription subscription){
        if(subscriptionRepository.findById(subscription.getConsumerID()).isPresent()
                && subscriptionRepository.findById(subscription.getLoyaltyProgramID()).isPresent())
            return subscriptionRepository.save(subscription);
        return null;
    }

    @PostMapping("/create-subscription")
    public Subscription createSubscription(@RequestBody LoyaltyProgram program, Consumer consumer){
        return null;
    }

    /**
     * Returns a subscription with the give id.
     * @param id id of subscription to be returned.
     * @return A subscription with the given id.
     */
    public Optional<Subscription> getSubscription(Long id) {
        return subscriptionRepository.findById(id);
    }

    /**
     * Gets all subscriptions from the datasource.
     * @return All subscriptions from the datasource.
     */
    public Set<Subscription> getAllSubscriptions() {
        Iterable<Subscription> itr = subscriptionRepository.findAll();
        return StreamSupport.stream(itr.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Deletes a subscription with the given id from the datasource.
     * @param id ID of subscription to delete.
     * @return The deleted subscription.
     */
    public ResponseEntity<Subscription> deleteSubscription(Long id) {
            return subscriptionRepository.findById(id).map(sub -> {
                subscriptionRepository.delete(sub);
                return ResponseEntity.status(HttpStatus.OK).body(sub);

            }).orElse(
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
            );
    }

    /**
     * Updates the subscription associated with the given ID.
     * @param id ID of subscription to update.
     * @param subscription The updated subscription to replace the previous one.
     * @return The newly updated subscription.
     */
    public Subscription updateSubscription(Long id, Subscription subscription) {
        if(subscriptionRepository.findById(id).isEmpty())
            return null;
        subscription.setId(id);
        return subscriptionRepository.save(subscription);
    }

    public Set<LoyaltyProgram> getLoyaltyProgramsOfConsumer(Long id){
        return consumerService.getLoyaltyProgramsOfConsumer(id);
    }
}
