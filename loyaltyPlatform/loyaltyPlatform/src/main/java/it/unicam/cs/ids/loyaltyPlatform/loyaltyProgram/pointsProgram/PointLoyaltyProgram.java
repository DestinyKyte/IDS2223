package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.pointsProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramType;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pointsprogram")
@NoArgsConstructor
public class PointLoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
