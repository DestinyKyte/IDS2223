package it.unicam.cs.ids.loyaltyPlatform.PendingAnswers;

import it.unicam.cs.ids.loyaltyPlatform.Question.Question;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.UnionProgram.UnionProgram;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.UnionProgram.UnionProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UnionProgramPendingAnswersService {

    @Autowired
    private  UnionProgramPendingAnswersRepository unionProgramPendingAnswersRepository;
    @Autowired
    private  UnionProgramService unionProgramService;

    public UnionProgramPendingAnswers createUnionProgramPendingAnswers(Boolean typeQuestion, Long unionProgramId) {
        UnionProgram unionProgramObj = unionProgramService.getUnionProgramById(unionProgramId);
        Integer totalNumberQuestion = unionProgramObj.getMembers().size();
        return unionProgramPendingAnswersRepository.save(new UnionProgramPendingAnswers(typeQuestion, unionProgramObj, totalNumberQuestion));
    }

    public UnionProgramPendingAnswers getPendingAnswersById(Long unionPendingId) {
        return unionProgramPendingAnswersRepository.findById(unionPendingId).orElse(null);
    }

    public List<UnionProgramPendingAnswers> getAllPendingAnswers() {
        return (List<UnionProgramPendingAnswers>) unionProgramPendingAnswersRepository.findAll();
    }

    public boolean deletePendingAnswers(Long unionPendingId) {
        UnionProgramPendingAnswers pendingAnswers = getPendingAnswersById(unionPendingId);
        if (pendingAnswers != null) {
            unionProgramPendingAnswersRepository.delete(pendingAnswers);
            return true;
        }
        return false;
    }

    public boolean modifyTotalAnswers(Question question) {
        UnionProgramPendingAnswers pendingAnswers = question.getPendingAnswers();
        if (question.getAnswer() && !pendingAnswers.getBlock()){
            pendingAnswers.setTotalAnswers(pendingAnswers.getTotalAnswers() + 1);
            unionProgramPendingAnswersRepository.save(pendingAnswers);
            checkTotalAnswers(pendingAnswers, question);
            return true;
        } else if (!pendingAnswers.getBlock()) {
            pendingAnswers.setTotalAnswers(-1); // Se ha rifiutato.
            pendingAnswers.setBlock(Boolean.TRUE);
            unionProgramPendingAnswersRepository.save(pendingAnswers);
            return true;
        } else {
            return false;
        }
    }

    private void checkTotalAnswers(UnionProgramPendingAnswers pendingAnswers, Question question){
        if(Objects.equals(pendingAnswers.getTotalNumberQuestion(), pendingAnswers.getTotalAnswers())){
            pendingAnswers.setBlock(Boolean.TRUE);
            unionProgramPendingAnswersRepository.save(pendingAnswers);
            if(!pendingAnswers.getTypeQuestion()) { // Tipo invito.
                unionProgramService.getUnionProgramById(pendingAnswers.getUnionPendingId()).setCanBePublished(Boolean.TRUE); //TODO fidelityprogram
            } else {
                unionProgramService.addOwnerUnionProgram(pendingAnswers.getUnionProgram(), question.getSender());
            }
        }
    }
}
