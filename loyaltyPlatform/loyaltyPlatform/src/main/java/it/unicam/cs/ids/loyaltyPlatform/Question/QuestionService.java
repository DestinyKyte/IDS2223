package it.unicam.cs.ids.loyaltyPlatform.Question;

import it.unicam.cs.ids.loyaltyPlatform.PendingAnswers.UnionProgramPendingAnswers;
import it.unicam.cs.ids.loyaltyPlatform.PendingAnswers.UnionProgramPendingAnswersService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UnionProgramPendingAnswersService unionProgramPendingAnswersService;

    @Autowired
    public QuestionService(@Lazy QuestionRepository questionRepository,
                           @Lazy UnionProgramPendingAnswersService unionProgramPendingAnswersService) {
        this.questionRepository = questionRepository;
        this.unionProgramPendingAnswersService = unionProgramPendingAnswersService;
    }

    public Question createQuestion(String sender, String receiver, Date senderDate, @NotNull Long pendingAnswersId) {
        UnionProgramPendingAnswers pendingAnswersObj = unionProgramPendingAnswersService.getPendingAnswersById(pendingAnswersId);
        String message = createMessage(sender, senderDate, pendingAnswersObj);
        return questionRepository.save(new Question(sender, receiver, senderDate, pendingAnswersObj, message));
    }

    public Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    public List<Question> getQuestionBySender(String sender){
        return questionRepository.findAllBySender(sender);
    }

    public List<Question> getQuestionByReceiver(String receiver){
        return questionRepository.findAllByReceiver(receiver);
    }

    public List<Question> getAllQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    public boolean deleteQuestion(Long questionId) {
        Question question = getQuestionById(questionId);
        if (question != null) {
            questionRepository.delete(question);
            return true;
        }
        return false;
    }
    public boolean itHasBeenDisplayed(Long questionId, Date displayed) {
        Question question = getQuestionById(questionId);
        if (question != null) {
            question.setDisplayed(displayed);
            questionRepository.save(question);
            return true;
        }
        return false;
    }

    public boolean answerQuestion(Long questionId, boolean answer) {
        Question question = getQuestionById(questionId);
        if (question != null) {
            question.setAnswer(answer);
            question.setHasAnswer(Boolean.TRUE);
            questionRepository.save(question);
            unionProgramPendingAnswersService.modifyTotalAnswers(question);
            return true;
        }
        return false;
    }

    private @NotNull String createMessage(String sender, Date senderDate, @NotNull UnionProgramPendingAnswers pendingAnswers) {
        String programName = pendingAnswers.getUnionProgram().getName();
        String programCode = pendingAnswers.getUnionProgram().getFidelityId().toString();
        String typeQuestion = pendingAnswers.getTypeQuestion() ? " ti ha invitato a unirti al consorzio: " :
                " chiedi di unirsi al vostro consorzio: ";

        return sender + typeQuestion + programName + ", codice: " + programCode + ". Data di invio: " + senderDate + ".";
    }
}
