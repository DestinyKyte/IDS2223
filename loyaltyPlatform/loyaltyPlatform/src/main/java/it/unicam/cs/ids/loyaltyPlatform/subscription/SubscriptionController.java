package it.unicam.cs.ids.loyaltyPlatform.subscription;

import it.unicam.cs.ids.loyaltyPlatform.consumer.Consumer;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/loyalty-platform/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/get-subscription/{id}")
    public Optional<Subscription> getSubscription(@PathVariable Long id){
        return subscriptionService.getSubscription(id);
    }
    @GetMapping("/get-all-subscriptions")
    public Set<Subscription> getAllSubscriptions(){
        return subscriptionService.getAllSubscriptions();
    }

    @PostMapping("/create-subscription")
    public Subscription createSubscription(@RequestBody Subscription subscription){
        return subscriptionService.createSubscription(subscription);
    }

    @PostMapping("/create-subscription/sub")
    public Subscription createSubscriptionViaID(@RequestParam Long programID, @RequestParam Long consumerID){
        return subscriptionService.createSubscriptionFromID(programID, consumerID);
    }

    @PutMapping("/update-subscription/{id}")
    public Subscription updateSubscription(@PathVariable Long id, @RequestBody Subscription subscription){
        return subscriptionService.updateSubscription(id, subscription);
    }

    @DeleteMapping("/delete-subscription/{id}")
    public ResponseEntity<Subscription> deleteSubscription(@PathVariable Long id){
        return subscriptionService.deleteSubscription(id);
    }
}
