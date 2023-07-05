package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.UnionProgram;

import it.unicam.cs.ids.loyaltyPlatform.PendingAnswers.UnionProgramPendingAnswers;
import it.unicam.cs.ids.loyaltyPlatform.PendingAnswers.UnionProgramPendingAnswersService;
import it.unicam.cs.ids.loyaltyPlatform.question.QuestionService;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import it.unicam.cs.ids.loyaltyPlatform.owner.Owner;
import it.unicam.cs.ids.loyaltyPlatform.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class UnionProgramService {

    @Autowired
    private UnionProgramRepository unionProgramRepository;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    @Lazy
    private UnionProgramPendingAnswersService unionProgramPendingAnswersService;
    @Autowired
    @Lazy
    private QuestionService questionService;

    public UnionProgram createUnionProgram(UnionProgram unionProgram) {
        this.unionProgramRepository.save(unionProgram);
        this.sendInvitation(unionProgram);
        return unionProgram;
    }

    public Boolean modifyUnionProgram(Long unionId, List<Owner> listOwner, List<LoyaltyProgram> fidelityProgramList) {
        UnionProgram unionProgram = getUnionProgramById(unionId);
        if (unionProgram != null) {
            unionProgram.setMembers(listOwner);
            unionProgram.setProgramList(fidelityProgramList);
            unionProgramRepository.save(unionProgram);
            return true;
        }
        return false;
    }

    public Boolean deleteUnionProgram(Long unionId) {
        UnionProgram unionProgram = getUnionProgramById(unionId);
        if (unionProgram != null) {
            unionProgramRepository.delete(unionProgram);
            return true;
        }
        return false;
    }

    public UnionProgram getUnionProgramById(Long unionId) {
        return unionProgramRepository.findById(unionId).orElse(null);
    }

    public Iterable<UnionProgram> getAllUnionPrograms() {
        return this.unionProgramRepository.findAll();
    }

    public Boolean addOwnerUnionProgram(UnionProgram unionProgram, String sender) {
        List<Owner> ownerList = unionProgram.getMembers();
        Owner owner = ownerService.getOwner(sender).getBody();
        ownerList.add(owner);
        unionProgram.setMembers(ownerList);
        unionProgramRepository.save(unionProgram);
        return true;
    }

    public void sendInvitation(UnionProgram unionProgram) {
        List<Owner> owners = unionProgram.getMembers();
        UnionProgramPendingAnswers unionProgramPendingAnswers = unionProgramPendingAnswersService.
                createUnionProgramPendingAnswers(false, unionProgram.getId());

        String sender = unionProgram.getOwner().getName();
        for (Owner owner : owners) {
            questionService.createQuestion(sender, owner.getVatNumber(),
                    Date.valueOf(LocalDate.now()), unionProgramPendingAnswers.getUnionPendingId());
        }
    }
}