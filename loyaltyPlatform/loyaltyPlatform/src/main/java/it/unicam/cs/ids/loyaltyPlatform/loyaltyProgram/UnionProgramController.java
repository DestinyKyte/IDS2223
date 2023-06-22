package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.owner.Owner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/unionPrograms")
public class UnionProgramController {

    private final UnionProgramService unionProgramService;

    public UnionProgramController(UnionProgramService unionProgramService) {
        this.unionProgramService = unionProgramService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUnionProgram(List<FidelityProgram> fidelityProgramList) {
        return unionProgramService.createUnionProgram(fidelityProgramList) ?
                ResponseEntity.status(HttpStatus.CREATED).body("Union program created successfully.") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create union program.");
    }

    @PutMapping("/modify/{unionId}")
    public ResponseEntity<String> modifyUnionProgram(@PathVariable int unionId, List<Owner> listOwner) {
        return unionProgramService.modifyUnionProgram(unionId, listOwner) ?
                ResponseEntity.ok("Union program modified successfully.") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{unionId}")
    public ResponseEntity<String> deleteUnionProgram(@PathVariable int unionId) {
        return unionProgramService.deleteUnionProgram(unionId) ?
                ResponseEntity.ok("Union program deleted successfully.") : ResponseEntity.notFound().build();
    }

    @GetMapping("/get/{unionId}")
    public ResponseEntity<UnionProgram> getUnionProgram(@PathVariable int unionId) {
        UnionProgram unionProgram = unionProgramService.getUnionProgram(unionId);
        return unionProgram != null ? ResponseEntity.ok(unionProgram) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UnionProgram>> getAllUnionPrograms() {
        List<UnionProgram> unionPrograms = unionProgramService.getAllUnionPrograms();
        return !unionPrograms.isEmpty() ? ResponseEntity.ok(unionPrograms) : ResponseEntity.noContent().build();
    }



    private void notify(CompletableFuture<?> result) {
        // Implement notification logic here
    }

    private void publish(CompletableFuture<?> result) {
        // Implement publish logic here
    }
}
