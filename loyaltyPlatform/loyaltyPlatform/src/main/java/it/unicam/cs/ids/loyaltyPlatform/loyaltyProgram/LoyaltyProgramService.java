package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.customDTOs.BonusCreationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LoyaltyProgramService {
    private final LoyaltyProgramRepository loyaltyProgramRepository;

    public LoyaltyProgramService(LoyaltyProgramRepository loyaltyProgramRepository) {
        this.loyaltyProgramRepository = loyaltyProgramRepository;
    }

    //TODO Complete Crud Operations. Even though they exist in specific services,
    // it's best to have them here

    public Optional<LoyaltyProgram> getLoyaltyProgram(Long loyaltyProgramID) {
        return this.loyaltyProgramRepository.findById(loyaltyProgramID);
    }

    public List<LoyaltyProgram> getAllLoyaltyPrograms(){
        Iterable<LoyaltyProgram> itr = loyaltyProgramRepository.findAll();
        return StreamSupport.stream(itr.spliterator(), false).collect(Collectors.toList());
    }

    public List<BonusCreationDTO> getBonusCreationDTOsFromProgram(Long id){
        return loyaltyProgramRepository.findLoyaltyProgramBonusCreationDTOValuesByID(id);
    }

    public boolean verifyIfExists(Long programId){
        return loyaltyProgramRepository.programExists(programId);
    }

    public boolean publishLoyaltyProgram(Long id){
        loyaltyProgramRepository.findById(id).ifPresent(loyaltyProgram -> loyaltyProgram.setPublishedStatus(true));
        return false;
    }
}
