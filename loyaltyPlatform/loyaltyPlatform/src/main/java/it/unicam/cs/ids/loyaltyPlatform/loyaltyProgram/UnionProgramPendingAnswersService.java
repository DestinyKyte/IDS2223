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
        return unionProgramPendingAnswersRepository.save(unionProgramPendingAnswers) != null;
    }

    public boolean modifyUnionProgramPendingAnswers(Integer unionPending_id, List<Owner> listOwner) {
        List<Owner> listOwnerSending;
        for (:listOwner) {

        }
        sendNotification(unionPending_id, listOwnerSending);
        UnionProgram unionProgram = getUnionProgram(unionId);
        if (unionProgram != null) {
            unionProgram.setMembers(listOwner);
            return unionProgramRepository.save(unionProgram) != null;
        }
        return false;
    }
    public void storePendingAnswer(UnionProgram unionProgram, Answer answer) {
        // Implementa qui la logica per memorizzare una risposta in sospeso per un programma di unione
    }

    public void processPendingAnswers(Integer unionPending_id,List<Owner> listOwner) {
        // Implementa qui la logica per elaborare le risposte in sospeso di un programma di unione
    }

    public void sendNotification(Integer unionPending_id,List<Owner> listOwner) {
        // Implementa qui la logica per inviare una notifica relativa a una risposta in sospeso
    }

    public void deletePendingAnswer(UnionProgram unionProgram, Answer answer) {
        // Implementa qui la logica per eliminare una risposta in sospeso per un programma di unione
    }

    // Aggiungi altri metodi se necessario
}
