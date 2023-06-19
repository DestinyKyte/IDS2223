package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.cashback;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class CashbackLoyaltyProgramService {
    private final CashbackLoyaltyProgramRepository cashbackLoyaltyProgramRepository;

    public CashbackLoyaltyProgramService(CashbackLoyaltyProgramRepository cashbackLoyaltyProgramRepository) {
        this.cashbackLoyaltyProgramRepository = cashbackLoyaltyProgramRepository;
    }

    /**
     * Returns all {@link CashbackLoyaltyProgram} from a datasource.
     * @return all CashbackLoyaltyProgram.
     */
    public Set<CashbackLoyaltyProgram> getAllPrograms(){
        Iterable<CashbackLoyaltyProgram> iterable = cashbackLoyaltyProgramRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns a {@link CashbackLoyaltyProgram} from a datasource.
     * @param id CashbackLoyaltyProgram path variable identifier.
     * @return a CashbackLoyaltyProgram.
     */
    public Optional<CashbackLoyaltyProgram> getLoyaltyProgramByID(Long id){
         return cashbackLoyaltyProgramRepository.findById(id);
    }

    /**
     * Creates a new {@link CashbackLoyaltyProgram} entry in a datasource.
     * @param cashbackLoyaltyProgram entry to be created.
     * @return newly created entry.
     */
    public CashbackLoyaltyProgram createCashbackProgram(CashbackLoyaltyProgram cashbackLoyaltyProgram) {
        return cashbackLoyaltyProgramRepository.save(cashbackLoyaltyProgram);
    }

    /**
     * updates a {@link CashbackLoyaltyProgram} in a datasource.
     * @param id Path variable from Http.
     * @param cashbackLoyaltyProgram new updated CashbackLoyaltyProgram to be saved.
     * @return the newly saved CashbackLoyaltyProgram.
     */
    public ResponseEntity<CashbackLoyaltyProgram> updateCashbackProgram
    (Long id, CashbackLoyaltyProgram cashbackLoyaltyProgram) {
        // I HAVE TO ASSIGN THE ID BEFORE SAVING IT ELSE IT WILL CREATE A NEW PROGRAM
        //WITH AUTO GENERATED ID.
        if (!cashbackLoyaltyProgramRepository.existsById(id))
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        cashbackLoyaltyProgram.setId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cashbackLoyaltyProgramRepository.save(cashbackLoyaltyProgram));
    }


    /**
     * Deletes a {@link  CashbackLoyaltyProgram} entry by means of its ID from a datasource.
     * @param id path variable from Http.
     * @return httpStatus.GONE if entry is found and deleted. Else httpStatus.NOT_FOUND.
     */
    public ResponseEntity<CashbackLoyaltyProgram> deleteCashbackLoyaltyProgram(Long id) {
        return cashbackLoyaltyProgramRepository.findById(id).map(cashbackLoyaltyProgram ->
        {
            cashbackLoyaltyProgramRepository.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(cashbackLoyaltyProgram);
        }).orElse(
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null));

    }
}
