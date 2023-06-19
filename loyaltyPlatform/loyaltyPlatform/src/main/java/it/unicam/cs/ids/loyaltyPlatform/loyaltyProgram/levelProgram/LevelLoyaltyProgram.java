package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.levelProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "levelloyaltyprogram")
@NoArgsConstructor
@AllArgsConstructor
public class LevelLoyaltyProgram extends LoyaltyProgram {

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Level> levels;


    //#region GETTERS AND SETTERS METHOD
    public Set<Level> getLevels() {
        return levels;
    }

    public void setLevels(Set<Level> levels) {
        this.levels = levels;
    }
    //#endregion
}
