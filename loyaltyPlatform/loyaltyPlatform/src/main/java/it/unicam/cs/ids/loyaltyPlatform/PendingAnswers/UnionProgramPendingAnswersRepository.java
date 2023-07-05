package it.unicam.cs.ids.loyaltyPlatform.PendingAnswers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnionProgramPendingAnswersRepository extends CrudRepository<UnionProgramPendingAnswers, Long> {
}
