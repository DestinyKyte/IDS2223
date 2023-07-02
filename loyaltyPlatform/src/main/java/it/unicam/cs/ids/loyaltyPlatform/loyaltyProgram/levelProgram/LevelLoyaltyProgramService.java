package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.levelProgram;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class LevelLoyaltyProgramService {
    //#region DEPENDENCIES AND CONSTRUCTOR
    private final LevelLoyaltyProgramRepository levelLoyaltyProgramRepository;
    public LevelLoyaltyProgramService(LevelLoyaltyProgramRepository levelLoyaltyProgramRepository) {
        this.levelLoyaltyProgramRepository = levelLoyaltyProgramRepository;
    }


    //#endregion

    //#region METHODS
    /**
     * Returns all {@link LevelLoyaltyProgram} from a datasource.
     * @return all PointLoyaltyProgram.
     */
    public Set<LevelLoyaltyProgram> getAllLevelsLoyaltyPrograms() {
        Iterable<LevelLoyaltyProgram> itr = levelLoyaltyProgramRepository.findAll();
        return StreamSupport.stream(itr.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns a {@link LevelLoyaltyProgram} from a datasource.
     * @param id LevelLoyaltyProgram path variable identifier.
     * @return a LevelLoyaltyProgram.
     */
    public Optional<LevelLoyaltyProgram> getLevelsLoyaltyProgramByID(Long id) {
        return levelLoyaltyProgramRepository.findById(id);
    }

    /**
     * Creates a new {@link LevelLoyaltyProgram} entry in a datasource.
     * @param levelLoyaltyProgram entry to be created.
     * @return newly created entry.
     */
    public LevelLoyaltyProgram createLevelsLoyaltyProgram(LevelLoyaltyProgram levelLoyaltyProgram) {
        return levelLoyaltyProgramRepository.save(levelLoyaltyProgram);
    }

    /**
     * updates a {@link LevelLoyaltyProgram} in a datasource.
     * @param id Path variable from Http.
     * @param levelLoyaltyProgram new updated LevelLoyaltyProgram to be saved.
     * @return the newly saved LevelLoyaltyProgram.
     */
    public LevelLoyaltyProgram updateLevelLoyaltyProgram(Long id, LevelLoyaltyProgram levelLoyaltyProgram){
        if (levelLoyaltyProgramRepository.findById(id).isEmpty()) return null;
        levelLoyaltyProgram.setId(id);
        return levelLoyaltyProgramRepository.save(levelLoyaltyProgram);
    }

    /**
     * Deletes a {@link  LevelLoyaltyProgram} entry by means of its ID from a datasource.
     * @param id path variable from Http.
     * @return httpStatus.GONE if entry is found and deleted. Else httpStatus.NOT_FOUND.
     */
    public ResponseEntity<LevelLoyaltyProgram> deleteLevelLoyaltyProgram(Long id) {
        return levelLoyaltyProgramRepository
                .findById(id)
                .map(levelLoyaltyProgram -> {
            levelLoyaltyProgramRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(levelLoyaltyProgram);
        }).orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
    //#endregion
}
