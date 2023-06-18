package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.cashback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.http.HttpClient;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CashbackLoyaltyProgramService {
    @Autowired
    CashbackLoyaltyProgramRepository cashbackLoyaltyProgramRepository;

    /**
     * Returns all {@link CashbackLoyaltyProgram} from a datasource.
     *
     * @return all CashbackLoyaltyProgram.
     */
    public Set<CashbackLoyaltyProgram> getAllPrograms() {
        Iterable<CashbackLoyaltyProgram> iterable = cashbackLoyaltyProgramRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a {@link CashbackLoyaltyProgram} from a datasource.
     *
     * @param id CashbackLoyaltyProgram path variable identifier.
     * @return a CashbackLoyaltyProgram.
     */
    public Optional<CashbackLoyaltyProgram> getLoyaltyProgramByID(Long id) {
        return cashbackLoyaltyProgramRepository.findById(id);
    }

    /**
     * Creates a new {@link CashbackLoyaltyProgram} entry in a datasource.
     *
     * @param cashbackLoyaltyProgram entry to be created.
     * @return newly created entry.
     */
    public CashbackLoyaltyProgram createCashbackProgram(CashbackLoyaltyProgram cashbackLoyaltyProgram) {
        return cashbackLoyaltyProgramRepository.save(cashbackLoyaltyProgram);
    }

    /**
     * updates a {@link CashbackLoyaltyProgram} in a datasource.
     *
     * @param id                     Path variable from Http.
     * @param cashbackLoyaltyProgram new updated CashbackLoyaltyProgram to be saved.
     * @return the newly saved CashbackLoyaltyProgram.
     */
    public Optional<CashbackLoyaltyProgram> updateCashbackProgram(Long id, CashbackLoyaltyProgram cashbackLoyaltyProgram) {
        // I HAVE TO ASSIGN THE ID BEFORE SAVING IT ELSE IT WILL CREATE A NEW PROGRAM
        //WITH AUTO GENERATED ID.
        cashbackLoyaltyProgram.setId(id);
        cashbackLoyaltyProgramRepository.findById(id)
                .ifPresent(e -> cashbackLoyaltyProgramRepository.save(cashbackLoyaltyProgram));
        return cashbackLoyaltyProgramRepository.findById(id);
    }


    /**
     * Deletes a {@link  CashbackLoyaltyProgram} entry from a datasource.
     *
     * @param id path variable from Http.
     * @return httpStatus.GONE if entry is found and deleted. Else httpStatus.NOT_FOUND.
     */
    public ResponseEntity<String> deleteCashbackLoyaltyProgram(Long id) {
        if (cashbackLoyaltyProgramRepository.findById(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
        cashbackLoyaltyProgramRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.GONE).body("Entity deleted");
    }
}
