package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.premiumProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramType;
import jakarta.persistence.*;

@Entity
public abstract class PremiumLoyaltyProgram{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    // frequenza potrebbe essere un tipo enum: MENSILE, ANNUALE...
}
