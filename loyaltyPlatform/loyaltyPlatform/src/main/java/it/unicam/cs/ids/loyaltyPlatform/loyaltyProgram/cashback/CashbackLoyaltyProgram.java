package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.cashback;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramRatio;
import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cashbackloyaltyprogram")
@AllArgsConstructor
@NoArgsConstructor
public class CashbackLoyaltyProgram extends LoyaltyProgram {
    private int cashbackValue;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<ProgramRatio> ratios;

    public Set<ProgramRatio> getRatios() {
        return ratios;
    }


    public int getValue() {
        return cashbackValue;
    }

    public void setValue(int value) {
        this.cashbackValue = value;
    }

}
