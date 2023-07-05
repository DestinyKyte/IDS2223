package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/loyalty-platform/loyalty-program")
public class LoyaltyProgramController {
    private final LoyaltyProgramService loyaltyProgramService;

    public LoyaltyProgramController(LoyaltyProgramService loyaltyProgramService) {
        this.loyaltyProgramService = loyaltyProgramService;
    }

    @GetMapping("/get/{id}")
    public Optional<LoyaltyProgram> getProgram(@PathVariable Long id) {
        return loyaltyProgramService.getLoyaltyProgram(id);
    }

    @PostMapping("/publish/{id}")
    public boolean publishLoyaltyProgram(@PathVariable Long id){
        return loyaltyProgramService.publishLoyaltyProgram(id);
    }
}
