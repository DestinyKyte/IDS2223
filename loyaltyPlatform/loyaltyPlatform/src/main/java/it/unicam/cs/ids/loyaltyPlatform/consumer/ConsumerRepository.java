package it.unicam.cs.ids.loyaltyPlatform.consumer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ConsumerRepository extends CrudRepository<Consumer, Long> {
    Set<Consumer> findAllById(Long id);
}
