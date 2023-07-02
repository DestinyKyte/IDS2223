package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.pointsProgram;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointLoyaltyProgramRepository extends CrudRepository<PointLoyaltyProgram, Long> {
}
