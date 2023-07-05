package it.unicam.cs.ids.loyaltyPlatform.subscription;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

    Subscription findSubscriptionByConsumerIDAndAndLoyaltyProgramID(Long consumerID, Long programID);
}
