package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.cashback;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashbackLoyaltyProgramRepository extends CrudRepository<CashbackLoyaltyProgram, Long> {
}
