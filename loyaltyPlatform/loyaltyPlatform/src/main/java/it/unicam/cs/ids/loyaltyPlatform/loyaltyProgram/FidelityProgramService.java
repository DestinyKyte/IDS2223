package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FidelityProgramService {
    private final FidelityProgramRepository fidelityProgramRepository;

    @Autowired
    public FidelityProgramService(FidelityProgramRepository fidelityProgramRepository) {
        this.fidelityProgramRepository = fidelityProgramRepository;
    }

    public FidelityProgram createFidelityProgram(FidelityProgram fidelityProgram) {
        return fidelityProgramRepository.save(fidelityProgram);
    }

    public FidelityProgram getFidelityProgramById(Long fidelityId) {
        return fidelityProgramRepository.findById(fidelityId).orElse(null);
    }

    public Iterable<FidelityProgram> getAllFidelityPrograms() {
        return fidelityProgramRepository.findAll();
    }

    public boolean deleteFidelityProgram(Long fidelityId) {
        if (fidelityProgramRepository.existsById(fidelityId)) {
            fidelityProgramRepository.deleteById(fidelityId);
            return true;
        }
        return false;
    }
}
