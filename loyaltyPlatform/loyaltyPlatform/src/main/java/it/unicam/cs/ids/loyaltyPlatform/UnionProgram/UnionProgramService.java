package it.unicam.cs.ids.loyaltyPlatform.UnionProgram;

import it.unicam.cs.ids.loyaltyPlatform.PendingAnswers.UnionProgramPendingAnswers;
import it.unicam.cs.ids.loyaltyPlatform.PendingAnswers.UnionProgramPendingAnswersService;
import it.unicam.cs.ids.loyaltyPlatform.Question.QuestionService;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.FidelityProgram;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.FidelityProgramService;
import it.unicam.cs.ids.loyaltyPlatform.owner.Owner;
import it.unicam.cs.ids.loyaltyPlatform.owner.OwnerService;
import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class UnionProgramService {

    private final UnionProgramRepository unionProgramRepository;
    private final FidelityProgramService fidelityProgramService;
    private final OwnerService ownerService;
    private final UnionProgramPendingAnswersService unionProgramPendingAnswersService;
    private final QuestionService questionService;

    @Autowired
    public UnionProgramService(@Lazy UnionProgramRepository unionProgramRepository, @Lazy FidelityProgramService fidelityProgramService,
                               @Lazy OwnerService ownerService, @Lazy UnionProgramPendingAnswersService unionProgramPendingAnswersService,
                               @Lazy QuestionService questionService) {
        this.unionProgramRepository = unionProgramRepository;
        this.fidelityProgramService = fidelityProgramService;
        this.ownerService = ownerService;
        this.unionProgramPendingAnswersService = unionProgramPendingAnswersService;

        this.questionService = questionService;
    }

    public UnionProgram createUnionProgram(List<FidelityProgram> programList, List<Owner> members,
                                      Date expirationStart, Date expirationFinish,
                                      String programType, List<Shop> shopList, Owner owner) {
        UnionProgram unionProgram = new UnionProgram(programList, members,
                expirationStart, expirationFinish, programType, shopList, owner);
        sendInvitation(unionProgram);
        return unionProgramRepository.save(unionProgram);
    }

    public Boolean modifyUnionProgram(Long unionId, List<Owner> listOwner, List<FidelityProgram> fidelityProgramList) {
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
            FidelityProgram fidelityProgram = fidelityProgramService.getFidelityProgramById(unionProgram.getFidelityId());
            unionProgramRepository.delete(unionProgram);
            if (fidelityProgram != null) {
                fidelityProgramService.deleteFidelityProgram(unionProgram.getFidelityId());
                return true;
            }
        }
        return false;
    }

    public UnionProgram getUnionProgramById(Long unionId) {
        return unionProgramRepository.findById(unionId).orElse(null);
    }

    public List<UnionProgram> getAllUnionPrograms() {
        return (List<UnionProgram>) unionProgramRepository.findAll();
    }

    public Boolean addOwnerUnionProgram(@NotNull UnionProgram unionProgram, @NotNull String sender) {
        List<Owner> ownerList = unionProgram.getMembers();
        Owner owner = ownerService.getOwnerByPartitaIva(sender);
        ownerList.add(owner);
        unionProgram.setMembers(ownerList);
        unionProgramRepository.save(unionProgram);
        return true;
    }

    public void sendInvitation(@NotNull UnionProgram unionProgram){
        List<Owner> owners = unionProgram.getMembers();
        UnionProgramPendingAnswers unionProgramPendingAnswers = unionProgramPendingAnswersService.
                createUnionProgramPendingAnswers(false, unionProgram.getFidelityId());
        String sender = unionProgram.getOwner().getPartitaIva();
        for (Owner owner : owners) {
            questionService.createQuestion(sender, owner.getPartitaIva(),
                    Date.valueOf(LocalDate.now()), unionProgramPendingAnswers.getUnionPendingId());
        }
    }
}