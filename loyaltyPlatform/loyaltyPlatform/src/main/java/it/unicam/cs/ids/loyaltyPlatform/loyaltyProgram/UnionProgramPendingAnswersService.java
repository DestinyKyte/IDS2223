package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.owner.Owner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnionProgramPendingAnswersService {

    private UnionProgramPendingAnswersRepository unionProgramPendingAnswersRepository;

    public UnionProgramPendingAnswersService(UnionProgramPendingAnswersRepository unionProgramPendingAnswersRepository) {
        this.unionProgramPendingAnswersRepository = unionProgramPendingAnswersRepository;
    }

    public boolean createUnionProgramPendingAnswers(List<Owner> members) {
        UnionProgramPendingAnswers unionProgramPendingAnswers = new UnionProgramPendingAnswers(members);
        if (unionProgramPendingAnswers != null) {
            unionProgramPendingAnswersRepository.save(unionProgramPendingAnswers);
            sendNotification(unionProgramPendingAnswers.getUnionPending_id(), members);
            return true;
        }
        return false;
    }

    public void sendNotification(Integer unionPending_id, List<Owner> listOwner) {

    }

    public void processPendingAnswers(Integer unionPending_id, List<Owner> listOwner) {

    }

    public void deletePendingAnswer(UnionProgram unionProgram, Answer answer) {

    }
}
