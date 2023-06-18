package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.cashback;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/loyalty-platform/cashback-program")
public class CashbackLoyaltyProgramController {
    @Autowired
    CashbackLoyaltyProgramService cashbackLoyaltyProgramService;

    @GetMapping("/programs")
    public Set<CashbackLoyaltyProgram> getAllPrograms() {
        return cashbackLoyaltyProgramService.getAllPrograms();
    }

    @GetMapping("/program/{id}")
    public Optional<CashbackLoyaltyProgram> getProgramByID(@PathVariable Long id) {
        return cashbackLoyaltyProgramService.getLoyaltyProgramByID(id);
    }

    @PostMapping("/program/create")
    public CashbackLoyaltyProgram createCashbackProgram(@RequestBody CashbackLoyaltyProgram cashbackLoyaltyProgram) {
        return cashbackLoyaltyProgramService.createCashbackProgram(cashbackLoyaltyProgram);
    }

    @PutMapping("/program/update/{id}")
    public Optional<CashbackLoyaltyProgram> updateCashbackProgram(@PathVariable long id, @RequestBody CashbackLoyaltyProgram cashbackLoyaltyProgram) {
        return cashbackLoyaltyProgramService.updateCashbackProgram(id, cashbackLoyaltyProgram);
    }

    @DeleteMapping("/program/delete/{id}")
    public ResponseEntity<String> deleteCashbackLoyaltyProgram(@PathVariable Long id) {
        return cashbackLoyaltyProgramService.deleteCashbackLoyaltyProgram(id);
    }

}
