package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.pointsProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/loyalty-platform/points-program")
public class PointLoyaltyProgramController {
    private final PointLoyaltyProgramService pointLoyaltyProgramService;

    public PointLoyaltyProgramController(PointLoyaltyProgramService pointLoyaltyProgramService) {
        this.pointLoyaltyProgramService = pointLoyaltyProgramService;
    }


    @GetMapping("/programs")
    public Set<PointLoyaltyProgram> getAllPointLoyaltyProgram(){
        return pointLoyaltyProgramService.getAllPointsLoyaltyPrograms();
    }
    @GetMapping("/program/{id}")
    public Optional<PointLoyaltyProgram> getPointsLoyaltyProgramByID
            (@PathVariable Long id){
        return pointLoyaltyProgramService.getPointLoyaltyProgramByID(id);
    }

    @PostMapping("/program/create")
    public PointLoyaltyProgram createPointsLoyaltyProgram
            (@RequestBody PointLoyaltyProgram loyaltyProgram){
        return pointLoyaltyProgramService.createPointLoyaltyProgram(loyaltyProgram);
    }

    @PutMapping("/program/update/{id}")
    public LoyaltyProgram updatePointLoyaltyProgram
            (@PathVariable Long id, @RequestBody PointLoyaltyProgram loyaltyProgram){
        return pointLoyaltyProgramService.updatePointLoyaltyProgram(id,  loyaltyProgram);
    }

    @DeleteMapping("/program/delete/{id}")
    public ResponseEntity<PointLoyaltyProgram> deletePointLoyaltyProgram(@PathVariable long id){
        return pointLoyaltyProgramService.deletePointLoyaltyProgram(id);
    }
}
