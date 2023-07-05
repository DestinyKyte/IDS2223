package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyProgramRepository extends CrudRepository<LoyaltyProgram, Long> {
}
