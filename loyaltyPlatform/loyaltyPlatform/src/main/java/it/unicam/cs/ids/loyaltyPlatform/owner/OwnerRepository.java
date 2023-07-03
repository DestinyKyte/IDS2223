package it.unicam.cs.ids.loyaltyPlatform.owner;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, String> {
}
