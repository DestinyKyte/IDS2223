package it.unicam.cs.ids.loyaltyPlatform.question;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findAllBySender(String sender);
    List<Question> findAllByReceiver(String receiver);
}
