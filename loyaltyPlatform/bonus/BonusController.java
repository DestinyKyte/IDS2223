package it.unicam.cs.ids.loyaltyPlatform.bonus;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class BonusController {
    private final BonusService bonusService;

    public BonusController(BonusService bonusService) {
        this.bonusService = bonusService;
    }
}
