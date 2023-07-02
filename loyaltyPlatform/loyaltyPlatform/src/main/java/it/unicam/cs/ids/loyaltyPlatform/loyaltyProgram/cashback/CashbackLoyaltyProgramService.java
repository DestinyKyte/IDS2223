package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.cashback;

import it.unicam.cs.ids.loyaltyPlatform.bonus.Bonus;
import it.unicam.cs.ids.loyaltyPlatform.bonus.BonusRepository;
import it.unicam.cs.ids.loyaltyPlatform.bonus.BonusService;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramParameter;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramRatio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class CashbackLoyaltyProgramService{
    private final CashbackLoyaltyProgramRepository cashbackLoyaltyProgramRepository;
    private final BonusRepository bonusRepository;
    private final BonusService bonusService;

    public CashbackLoyaltyProgramService(CashbackLoyaltyProgramRepository cashbackLoyaltyProgramRepository, BonusRepository bonusRepository, BonusService bonusService) {
        this.cashbackLoyaltyProgramRepository = cashbackLoyaltyProgramRepository;
        this.bonusRepository = bonusRepository;
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
        // TODO do not save bonuses, but create them at the moment of need and dispose of them after use.
        CashbackLoyaltyProgram program = cashbackLoyaltyProgramRepository.save(cashbackLoyaltyProgram);
        bonusRepository.saveAll(bonusService.createBonuses(program.getId()));
        return program;
    }

    /**
     * updates a {@link CashbackLoyaltyProgram} in a datasource.
     * @param id Path variable from Http.
     * @param cashbackLoyaltyProgram new updated CashbackLoyaltyProgram to be saved.
     * @return the newly saved CashbackLoyaltyProgram.
     */
    public ResponseEntity<CashbackLoyaltyProgram> updateCashbackProgram(Long id, CashbackLoyaltyProgram cashbackLoyaltyProgram) {
        // I HAVE TO ASSIGN THE ID BEFORE SAVING IT ELSE IT WILL CREATE A NEW PROGRAM
        //WITH AUTO GENERATED ID.
        if (!cashbackLoyaltyProgramRepository.existsById(id))
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        cashbackLoyaltyProgram.setId(id);
        CashbackLoyaltyProgram program = cashbackLoyaltyProgramRepository.save(cashbackLoyaltyProgram);
        //DELETE ALL THE OLD BONUSES
        bonusRepository.deleteAll(bonusRepository.findBonusByLoyaltyProgramID(id));
        //AND CREATE THEM AGAIN
        bonusRepository.saveAll(createBonuses(id));
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

    //#region EXTRA OPERATIONS REGION

    public boolean isApplicable(Long id) {
        if (cashbackLoyaltyProgramRepository.findById(id).isEmpty()) return false;
        CashbackLoyaltyProgram cashbackLoyaltyProgram = cashbackLoyaltyProgramRepository.findById(id).get();
        for (ProgramRatio ratio : cashbackLoyaltyProgram.getRatios()) {
            if (ratio.getParameter() == ProgramParameter.DATETIME)
                return LocalDate.now().equals(ChronoLocalDate.from(ratio.getApplicableDate().toInstant()));
            else if (ratio.getParameter() == ProgramParameter.PRODUCT)
                //return ratio.getApplicableToCategoryOfProduct();
                //COME PARAGONI LA CATEGORIA DEL PRODOTTO?
                return false;
        }
        return false;
    }

    /**
     * It creates a Set of bonuses from the given {@link LoyaltyProgram} ID if it exists.
     * It then sets the bonus's ID to that of the program it is created from.
     * @param programID LoyaltyProgram ID.
     * @return a Set of Bonuses if the LoyaltyProgram exists. Else null.
     */
    private Set<Bonus> createBonuses(Long programID){
        Set<Bonus> bonuses = new HashSet<>();
        return cashbackLoyaltyProgramRepository.findById(programID).map(cashbackLoyaltyProgram -> {
            Set<ProgramRatio> allRatios = cashbackLoyaltyProgram.getRatios();
            for(ProgramRatio ratio : allRatios){
                Bonus bonus = new Bonus(((float) ratio.getParameterValueOfDiscountInPercentage()/100), ratio.getApplicableDate());
                bonus.setLoyaltyProgramId(programID);
                bonuses.add(bonus);
            }
            return bonuses;
        }).orElse(null);
    }



    //#endregion
}
