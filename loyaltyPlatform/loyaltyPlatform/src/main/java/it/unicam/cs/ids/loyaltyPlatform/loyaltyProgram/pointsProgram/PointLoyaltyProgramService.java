package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.pointsProgram;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PointLoyaltyProgramService {

    //#region DEPENDENCIES AND CONSTRUCTOR
   private final PointLoyaltyProgramRepository pointLoyaltyProgramRepository;

    public PointLoyaltyProgramService(PointLoyaltyProgramRepository pointLoyaltyProgramRepository) {
        this.pointLoyaltyProgramRepository = pointLoyaltyProgramRepository;
    }


    //#endregion

    //#region METHODS
    /**
     * Returns all {@link PointLoyaltyProgram} from a datasource.
     * @return all PointLoyaltyProgram.
     */
    public Set<PointLoyaltyProgram> getAllPointsLoyaltyPrograms() {
        Iterable<PointLoyaltyProgram> itr = pointLoyaltyProgramRepository.findAll();
        return StreamSupport.stream(itr.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns a {@link PointLoyaltyProgram} from a datasource.
     * @param id PointLoyaltyProgram path variable identifier.
     * @return a PointLoyaltyProgram.
     */
    public Optional<PointLoyaltyProgram> getPointLoyaltyProgramByID(Long id) {
        return pointLoyaltyProgramRepository.findById(id);
    }

    /**
     * Creates a new {@link PointLoyaltyProgram} entry in a datasource.
     * @param pointLoyaltyProgram entry to be created.
     * @return newly created entry.
     */
    public PointLoyaltyProgram createPointLoyaltyProgram(PointLoyaltyProgram pointLoyaltyProgram) {
        return pointLoyaltyProgramRepository.save(pointLoyaltyProgram);
    }

    /**
     * updates a {@link PointLoyaltyProgram} in a datasource.
     * @param id Path variable from Http.
     * @param pointLoyaltyProgram new updated LevelLoyaltyProgram to be saved.
     * @return the newly saved PointLoyaltyProgram.
     */
    public PointLoyaltyProgram updatePointLoyaltyProgram(Long id, PointLoyaltyProgram pointLoyaltyProgram){
        if (pointLoyaltyProgramRepository.findById(id).isEmpty()) return null;
        pointLoyaltyProgram.setId(id);
        return pointLoyaltyProgramRepository.save(pointLoyaltyProgram);
    }

    /**
     * Deletes a {@link  PointLoyaltyProgram} entry by means of its ID from a datasource.
     * @param id path variable from Http.
     * @return httpStatus.GONE if entry is found and deleted. Else httpStatus.NOT_FOUND.
     */
    public ResponseEntity<PointLoyaltyProgram> deletePointLoyaltyProgram(Long id) {
        return pointLoyaltyProgramRepository
                .findById(id)
                .map(pointLoyaltyProgram -> {
                    pointLoyaltyProgramRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.OK).body(pointLoyaltyProgram);
                }).orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
    //#endregion
}
