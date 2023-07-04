package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.UnionProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import it.unicam.cs.ids.loyaltyPlatform.owner.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/union-programs")
public class UnionProgramController {

    @Autowired
    private UnionProgramService unionProgramService;

    @PostMapping
    public UnionProgram createUnionProgram(@RequestBody UnionProgram unionProgram) {
        return this.unionProgramService.createUnionProgram(unionProgram);
    }

    @PutMapping("/{unionId}")
    public ResponseEntity<Boolean> modifyUnionProgram(@PathVariable Long unionId, @RequestBody List<Owner> members,
                                                      @RequestBody List<LoyaltyProgram> programList) {
        return unionProgramService.modifyUnionProgram(unionId, members, programList) ?
                ResponseEntity.ok(Boolean.TRUE) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{unionId}")
    public ResponseEntity<Boolean> deleteUnionProgram(@PathVariable Long unionId) {
        return unionProgramService.deleteUnionProgram(unionId) ? ResponseEntity.ok(Boolean.TRUE)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{unionId}")
    public ResponseEntity<UnionProgram> getUnionProgramById(@PathVariable Long unionId) {
        UnionProgram unionProgram = unionProgramService.getUnionProgramById(unionId);
        return unionProgram != null ? ResponseEntity.ok(unionProgram) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public Iterable<UnionProgram> getAllUnionPrograms() {
        return this.unionProgramService.getAllUnionPrograms();
    }

    @PostMapping("/{unionId}/add-owner")
    public ResponseEntity<Boolean> addOwnerToUnionProgram(@PathVariable Long unionId, @RequestParam String sender) {
        UnionProgram unionProgram = unionProgramService.getUnionProgramById(unionId);
        if (unionProgram != null) {
            Boolean success = unionProgramService.addOwnerUnionProgram(unionProgram, sender);
            if (success) return ResponseEntity.ok(true);
            else return ResponseEntity.badRequest().build();
        } else return ResponseEntity.notFound().build();
    }
}
