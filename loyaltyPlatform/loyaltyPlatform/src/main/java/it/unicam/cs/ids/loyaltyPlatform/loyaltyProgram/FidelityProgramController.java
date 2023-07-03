package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loyalty-programs")
public class FidelityProgramController {
    private final FidelityProgramService fidelityProgramService;

    @Autowired
    public FidelityProgramController(FidelityProgramService fidelityProgramService) {
        this.fidelityProgramService = fidelityProgramService;
    }

    @PostMapping
    public ResponseEntity<FidelityProgram> createFidelityProgram(@RequestBody FidelityProgram fidelityProgram) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fidelityProgramService.createFidelityProgram(fidelityProgram));
    }

    @GetMapping("/{fidelityId}")
    public ResponseEntity<FidelityProgram> getFidelityProgramById(@PathVariable Long fidelityId) {
        FidelityProgram fidelityProgram = fidelityProgramService.getFidelityProgramById(fidelityId);
        return fidelityProgram != null ? ResponseEntity.ok(fidelityProgram) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public Iterable<FidelityProgram> getAllFidelityPrograms() {
        return fidelityProgramService.getAllFidelityPrograms();
    }

    @DeleteMapping("/{fidelityId}")
    public boolean deleteFidelityProgram(@PathVariable Long fidelityId) {
        return fidelityProgramService.deleteFidelityProgram(fidelityId);
    }
}
