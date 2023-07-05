package it.unicam.cs.ids.loyaltyPlatform.PendingAnswers;

import it.unicam.cs.ids.loyaltyPlatform.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pending-answers")
public class UnionProgramPendingAnswersController {

    @Autowired
    private  UnionProgramPendingAnswersService pendingAnswersService;

    @PostMapping("/{unionProgramId}")
    public ResponseEntity<UnionProgramPendingAnswers> createUnionProgramPendingAnswers(
            @PathVariable("unionProgramId") Long unionProgramId,
            @RequestParam Boolean typeQuestion) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pendingAnswersService.createUnionProgramPendingAnswers(typeQuestion, unionProgramId));
    }

    @GetMapping("/{pendingAnswersId}")
    public ResponseEntity<UnionProgramPendingAnswers> getPendingAnswersById(@PathVariable Long pendingAnswersId) {
        UnionProgramPendingAnswers pendingAnswers = pendingAnswersService.getPendingAnswersById(pendingAnswersId);
        return pendingAnswers != null ? ResponseEntity.ok(pendingAnswers) : ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<UnionProgramPendingAnswers>> getAllPendingAnswers() {
        return ResponseEntity.ok(pendingAnswersService.getAllPendingAnswers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePendingAnswers(@PathVariable("id") Long pendingAnswersId) {
        return pendingAnswersService.deletePendingAnswers(pendingAnswersId) ?
                ResponseEntity.ok("Pending answers deleted") : ResponseEntity.notFound().build();
    }

    @PutMapping("/modify-total-answers/{question}")
    public ResponseEntity<String> modifyTotalAnswers(@PathVariable("question") Question question) {
        return pendingAnswersService.modifyTotalAnswers(question) ?
                ResponseEntity.ok("Modify parameters.") :
                ResponseEntity.notFound().build();
    }
}