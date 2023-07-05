package it.unicam.cs.ids.loyaltyPlatform.question;

import it.unicam.cs.ids.loyaltyPlatform.PendingAnswers.UnionProgramPendingAnswers;
import it.unicam.cs.ids.loyaltyPlatform.PendingAnswers.UnionProgramPendingAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private  QuestionRepository questionRepository;
    @Autowired
    private  UnionProgramPendingAnswersService unionProgramPendingAnswersService;

    public Question createQuestion(String sender, String receiver, Date senderDate, Long pendingAnswersId) {
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
        if (question != null ) {
            question.setDisplayed(displayed);
            questionRepository.save(question);
            return true;
        }
        return false;
    }

    public boolean answerQuestion(Long questionId, boolean answer) {
        Question question = getQuestionById(questionId);
        if (question != null && !question.getHasAnswer()) {
            question.setAnswer(answer);
            question.setHasAnswer(Boolean.TRUE);
            questionRepository.save(question);
            unionProgramPendingAnswersService.modifyTotalAnswers(question);
            return true;
        }
        return false;
    }

    private String createMessage(String sender, Date senderDate, UnionProgramPendingAnswers pendingAnswers) {
        String programName = pendingAnswers.getUnionProgram().getOwner().getName(); //TODO fidelity
        String programCode = pendingAnswers.getUnionProgram().getId().toString();
        String typeQuestion = !pendingAnswers.getTypeQuestion() ? " ti ha invitato a unirti al consorzio: " :
                " chiedi di unirsi al vostro consorzio: ";

        return sender + typeQuestion + programName + ", codice: " + programCode + ". Data di invio: " + senderDate + ".";
    }
}
