package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.levelProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.programRatio.ProgramRatio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "levelloyaltyprogram")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LevelLoyaltyProgram extends LoyaltyProgram {

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Level> levels;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<ProgramRatio> ratios;

}
