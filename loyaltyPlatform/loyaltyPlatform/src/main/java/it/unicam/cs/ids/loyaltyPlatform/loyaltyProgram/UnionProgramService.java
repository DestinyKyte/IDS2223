package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.owner.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UnionProgramService {

    private final UnionProgramRepository unionProgramRepository;

    public UnionProgramService(UnionProgramRepository unionProgramRepository) {
        this.unionProgramRepository = unionProgramRepository;
    }

    public boolean createUnionProgram(List<FidelityProgram> fidelityProgramList) {
        UnionProgram unionProgram = new UnionProgram(fidelityProgramList);
        if (unionProgram != null) {
            unionProgramRepository.save(unionProgram);
            CompletableFuture.runAsync(()-- > {

                    });
            return true;
        }
        return false;
    }

    public boolean modifyUnionProgram(int unionId, List<Owner> listOwner) {
        UnionProgram unionProgram = getUnionProgram(unionId);
        if (unionProgram != null) {
            unionProgram.setMembers(listOwner);
            return unionProgramRepository.save(unionProgram) != null;
        }
        return false;
    }

    public boolean deleteUnionProgram(int unionId) {
        UnionProgram unionProgram = getUnionProgram(unionId);
        if (unionProgram != null) {
            unionProgramRepository.delete(unionProgram);
            return true;
        }
        return false;
    }

    public UnionProgram getUnionProgram(int unionId) {
        return unionProgramRepository.findById(unionId).orElse(null);
    }

    public List<UnionProgram> getAllUnionPrograms() {
        return (List<UnionProgram>) unionProgramRepository.findAll();
    }

    private void notify() {
        return CompletableFuture.runAsync(() -> {
            // Implementa la logica di notifica qui
        });
    }

    private CompletableFuture<Void> publishAsync(UnionProgram unionProgram) {
        return CompletableFuture.runAsync(() -> {
            // Implementa la logica di pubblicazione qui
        });
    }
}