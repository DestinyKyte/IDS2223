package it.unicam.cs.ids.loyaltyPlatform.UnionProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.FidelityProgram;
import it.unicam.cs.ids.loyaltyPlatform.owner.Owner;
import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/union-programs")
public class UnionProgramController {

    private final UnionProgramService unionProgramService;

    @Autowired
    public UnionProgramController(UnionProgramService unionProgramService) {
        this.unionProgramService = unionProgramService;
    }

    @PostMapping
    public ResponseEntity<UnionProgram> createUnionProgram(@RequestBody List<FidelityProgram> programList,
                                                           @RequestBody List<Owner> members,
                                                           @RequestBody Date expirationStart, @RequestBody Date expirationFinish,
                                                           @RequestBody String programType, @RequestBody List<Shop> shopList,
                                                           @RequestBody Owner owner) {
        UnionProgram createdUnionProgram = unionProgramService.createUnionProgram(programList, members,
                expirationStart, expirationFinish, programType, shopList, owner);
        return new ResponseEntity<>(createdUnionProgram, HttpStatus.CREATED);
    }

    @PutMapping("/{unionId}")
    public ResponseEntity<Boolean> modifyUnionProgram(@PathVariable Long unionId, @RequestBody List<Owner> members,
                                                      @RequestParam List<FidelityProgram> programList) {
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
    public ResponseEntity<List<UnionProgram>> getAllUnionPrograms() {
        return ResponseEntity.ok(unionProgramService.getAllUnionPrograms());
    }

    @PostMapping("/{unionId}/add-owner")
    public ResponseEntity<Boolean> addOwnerToUnionProgram(@PathVariable Long unionId, @RequestBody String sender) {
        UnionProgram unionProgram = unionProgramService.getUnionProgramById(unionId);
        if (unionProgram != null) {
            Boolean success = unionProgramService.addOwnerUnionProgram(unionProgram, sender);
            if (success) return ResponseEntity.ok(true);
            else return ResponseEntity.badRequest().build();
        } else return ResponseEntity.notFound().build();
    }
}
