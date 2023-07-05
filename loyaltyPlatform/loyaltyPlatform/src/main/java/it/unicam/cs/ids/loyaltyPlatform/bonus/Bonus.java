package it.unicam.cs.ids.loyaltyPlatform.bonus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bonus {
    @Id
    @GeneratedValue(generator = "bonus_id_generator",strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private float discountPercentage;
    private LocalDate bonusApplicableDate;
    private Long loyaltyProgramId;
    private float bonusRewardToExpensePercentage;
    private String itemCategoryTag;
    private int itemID;

    public Bonus(float bonusDiscountPercentage, float bonusRewardToExpensePercentage, LocalDate bonusApplicableDate, String itemCategoryTag) {
        this.bonusRewardToExpensePercentage = bonusRewardToExpensePercentage;
        this.discountPercentage = bonusDiscountPercentage;
        this.bonusApplicableDate = bonusApplicableDate;
        this.itemCategoryTag = itemCategoryTag;
    }
}
