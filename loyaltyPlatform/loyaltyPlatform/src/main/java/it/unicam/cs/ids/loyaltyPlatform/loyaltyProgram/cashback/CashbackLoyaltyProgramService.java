package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.cashback;

import it.unicam.cs.ids.loyaltyPlatform.bonus.BonusRepository;
import it.unicam.cs.ids.loyaltyPlatform.bonus.BonusService;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class CashbackLoyaltyProgramService{
    private final CashbackLoyaltyProgramRepository cashbackLoyaltyProgramRepository;
    private final BonusService bonusService;

    public CashbackLoyaltyProgramService(CashbackLoyaltyProgramRepository cashbackLoyaltyProgramRepository,  BonusService bonusService) {
        this.cashbackLoyaltyProgramRepository = cashbackLoyaltyProgramRepository;
        this.bonusService = bonusService;
    }

    //#region CRUD OPERATIONS REGION

    /**
     * Returns all {@link CashbackLoyaltyProgram} from a datasource.
     * @return all CashbackLoyaltyProgram.
     */
    public Set<LoyaltyProgram> getAllPrograms(){
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
     * Stores the bonuses offered by the given program too.
     * @param cashbackLoyaltyProgram entry to be created.
     * @return newly created entry.
     */
    public CashbackLoyaltyProgram createCashbackProgram(CashbackLoyaltyProgram cashbackLoyaltyProgram){

       /* //THIS IS ONLY IF I DECIDE TO EXTRACT THE BONUSES AT THE MOMENT OF CREATION.
       CashbackLoyaltyProgram program = cashbackLoyaltyProgramRepository.save(cashbackLoyaltyProgram);
        bonusRepository.saveAll(bonusService.createBonuses(program.getId()));
        return program;*/
        return cashbackLoyaltyProgramRepository.save(cashbackLoyaltyProgram);
    }

    /**
     * updates a {@link CashbackLoyaltyProgram} in a datasource.
     * @param id Path variable from Http.
     * @param cashbackLoyaltyProgram new updated CashbackLoyaltyProgram to be saved.
     * @return the newly saved CashbackLoyaltyProgram.
     */
    public ResponseEntity<CashbackLoyaltyProgram> updateCashbackProgram(Long id, CashbackLoyaltyProgram cashbackLoyaltyProgram){
        //TODO i should allow for the possibility to modify specific parts of the program without having to create a new program.
        if (!cashbackLoyaltyProgramRepository.existsById(id))
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        cashbackLoyaltyProgram.setId(id);
        CashbackLoyaltyProgram program = cashbackLoyaltyProgramRepository.save(cashbackLoyaltyProgram);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(program);
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

    }//#endregion
}
