package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.cashback;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.programRatio.ProgramRatio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cashbackloyaltyprogram")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CashbackLoyaltyProgram extends LoyaltyProgram {

    private float cashbackValue;
    /*
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<ProgramRatio> ratios;
*/
}