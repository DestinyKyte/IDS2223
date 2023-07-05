package it.unicam.cs.ids.loyaltyPlatform.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> createQuestion(
            @RequestParam String sender,
            @RequestParam String receiver,
            @RequestParam Date senderDate,
            @RequestParam Long pendingAnswersId) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(questionService.createQuestion(sender, receiver, senderDate, pendingAnswersId));
    }

    @GetMapping("{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long questionId) {
        Question question = questionService.getQuestionById(questionId);
        return question != null ? ResponseEntity.ok(question) : ResponseEntity.notFound().build();
    }

    @GetMapping("{sender}")
    public ResponseEntity<List<Question>> getQuestionBySender(@PathVariable String sender) {
        List<Question> questions = questionService.getQuestionBySender(sender);
        return !questions.isEmpty() ? ResponseEntity.ok(questions) : ResponseEntity.notFound().build();
    }

    @GetMapping("{receiver}")
    public ResponseEntity<List<Question>> getQuestionByReceiver(@PathVariable String receiver) {
        List<Question> questions = questionService.getQuestionByReceiver(receiver);
        return !questions.isEmpty() ? ResponseEntity.ok(questions) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @DeleteMapping("{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {
        return questionService.deleteQuestion(questionId) ? ResponseEntity.ok("Question deleted") :
                ResponseEntity.notFound().build();
    }

    @PostMapping("{questionId}/displayed")
    public ResponseEntity<String> markQuestionAsDisplayed(@PathVariable Long questionId, @RequestParam Date displayed) {
        return questionService.itHasBeenDisplayed(questionId, displayed) ?
                ResponseEntity.ok("Question marked as displayed") : ResponseEntity.notFound().build();
    }

    @PostMapping("{questionId}/answer")
    public ResponseEntity<String> answerQuestion(@PathVariable Long questionId, @RequestParam boolean answer) {
        return questionService.answerQuestion(questionId, answer) ? ResponseEntity.ok("Question answered") :
                ResponseEntity.notFound().build();
    }

}