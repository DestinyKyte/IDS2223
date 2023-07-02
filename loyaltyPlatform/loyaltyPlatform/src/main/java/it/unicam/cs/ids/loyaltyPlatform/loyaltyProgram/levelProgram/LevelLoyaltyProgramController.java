package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.levelProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/loyalty-platform/levels-program")
public class LevelLoyaltyProgramController {
    private final LevelLoyaltyProgramService levelLoyaltyProgramService;

    public LevelLoyaltyProgramController(LevelLoyaltyProgramService levelLoyaltyProgramService) {
        this.levelLoyaltyProgramService = levelLoyaltyProgramService;
    }

    @GetMapping("/programs")
    public Set<LevelLoyaltyProgram> getAllLevelsLoyaltyPrograms(){
        return levelLoyaltyProgramService.getAllLevelsLoyaltyPrograms();
    }
    @GetMapping("/program/{id}")
    public Optional<LevelLoyaltyProgram> getLevelsLoyaltyProgramByID
            (@PathVariable Long id){
        return levelLoyaltyProgramService.getLevelsLoyaltyProgramByID(id);
    }

    @PostMapping("/program/create")
    public LevelLoyaltyProgram createLevelsLoyaltyProgram
            (@RequestBody LevelLoyaltyProgram loyaltyProgram){
        return levelLoyaltyProgramService.createLevelsLoyaltyProgram(loyaltyProgram);
    }

    @PutMapping("/program/update/{id}")
    public LoyaltyProgram updateLevelLoyaltyProgram
            (@PathVariable Long id, @RequestBody LevelLoyaltyProgram loyaltyProgram){
        return levelLoyaltyProgramService.updateLevelLoyaltyProgram(id,  loyaltyProgram);
    }

    @DeleteMapping("/program/delete/{id}")
    public ResponseEntity<LevelLoyaltyProgram> deleteLevelLoyaltyProgram(@PathVariable long id){
        return levelLoyaltyProgramService.deleteLevelLoyaltyProgram(id);
    }
}
