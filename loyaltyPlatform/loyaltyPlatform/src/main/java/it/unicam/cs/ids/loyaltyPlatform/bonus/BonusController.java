package it.unicam.cs.ids.loyaltyPlatform.bonus;

import it.unicam.cs.ids.loyaltyPlatform.supermaketCashRegisterSimulator.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loyalty-platform/bonus")
public class BonusController {
    private final BonusService bonusService;

    public BonusController(BonusService bonusService) {
        this.bonusService = bonusService;
    }
    @PostMapping("/apply/{programID}")
    public float applyBonusFromProgram(@PathVariable Long programID, @RequestBody List<Product> products){
        return bonusService.applyBonus(programID, products);
    }
}
