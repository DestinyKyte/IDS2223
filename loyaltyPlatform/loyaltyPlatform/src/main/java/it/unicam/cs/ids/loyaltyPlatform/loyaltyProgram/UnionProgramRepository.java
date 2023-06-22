package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnionProgramRepository extends CrudRepository<UnionProgram, Integer> {

}