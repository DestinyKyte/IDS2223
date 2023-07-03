package it.unicam.cs.ids.loyaltyPlatform.PendingAnswers;

import it.unicam.cs.ids.loyaltyPlatform.Question.Question;
import it.unicam.cs.ids.loyaltyPlatform.UnionProgram.UnionProgram;
import it.unicam.cs.ids.loyaltyPlatform.UnionProgram.UnionProgramService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UnionProgramPendingAnswersService {

    private final UnionProgramPendingAnswersRepository unionProgramPendingAnswersRepository;
    private final UnionProgramService unionProgramService;

    @Autowired
    public UnionProgramPendingAnswersService(@Lazy UnionProgramPendingAnswersRepository unionProgramPendingAnswersRepository,
                                             @Lazy UnionProgramService unionProgramService) {
        this.unionProgramPendingAnswersRepository = unionProgramPendingAnswersRepository;
        this.unionProgramService = unionProgramService;
    }

    public UnionProgramPendingAnswers createUnionProgramPendingAnswers(Boolean typeQuestion, @NotNull Long unionProgramId) {
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

    public boolean modifyTotalAnswers(@NotNull Question question) {
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
            // TODO Notificare il fatto che è già bloccato quando si risponde, cioè verrà inviato una notifica
            //  dopo l'invio della risposta, la quale notifica il fatto che la creazione di questa Union non può avvenire
            //  perché utente x ha rifiutato. Oppure che il richiedente è stato rifiutato da x membro del consorzio.
        }
    }

    private void checkTotalAnswers(@NotNull UnionProgramPendingAnswers pendingAnswers, Question question){
        if(Objects.equals(pendingAnswers.getTotalNumberQuestion(), pendingAnswers.getTotalAnswers())){
            pendingAnswers.setBlock(Boolean.TRUE);
            unionProgramPendingAnswersRepository.save(pendingAnswers);
            if(!pendingAnswers.getTypeQuestion()) { // Tipo invito.
                unionProgramService.getUnionProgramById(pendingAnswers.getUnionPendingId()).setCanBePublished(Boolean.TRUE);
                // TODO Notifica che può essere pubblicato, a tutti i membri.
            } else {
                // TODO Modificare per l'invio del FIDELITY Program.
                unionProgramService.addOwnerUnionProgram(pendingAnswers.getUnionProgram(), question.getSender());
                // TODO notifica che è stato accettato questo membro.
            }
        }
    }
}
