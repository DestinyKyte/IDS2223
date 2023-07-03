package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.premiumProgram;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public abstract class PremiumLoyaltyProgram{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private double price;

}
