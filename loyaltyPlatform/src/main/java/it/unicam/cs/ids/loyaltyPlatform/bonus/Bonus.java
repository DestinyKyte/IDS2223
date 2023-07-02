package it.unicam.cs.ids.loyaltyPlatform.bonus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
public class Bonus {
    @Id
    @GeneratedValue(generator = "bonus_id_generator",strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private float discountPercentage;
    private Date bonusApplicableDate;
    private Long loyaltyProgramId;
    private float bonusPointsToExpenditureRatio;

    public Bonus() {
    }

    public Bonus(float bonusDiscountPercentage, Date bonusApplicableDate) {
        this.discountPercentage = bonusDiscountPercentage;
        this.bonusApplicableDate = bonusApplicableDate;
    }
    public Bonus(float bonusDiscountPercentage, Date bonusApplicableDate, float bonusPointsToExpenditureRatio) {
        this.bonusPointsToExpenditureRatio = bonusPointsToExpenditureRatio;
        this.discountPercentage = bonusDiscountPercentage;
        this.bonusApplicableDate = bonusApplicableDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getBonusApplicableDate() {
        return bonusApplicableDate;
    }

    public void setBonusApplicableDate(Date bonusApplicableDate) {
        this.bonusApplicableDate = bonusApplicableDate;
    }

    public Long getLoyaltyProgramId() {
        return loyaltyProgramId;
    }

    public void setLoyaltyProgramId(Long loyaltyProgramId) {
        this.loyaltyProgramId = loyaltyProgramId;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public float getBonusPointsToExpenditureRatio() {
        return bonusPointsToExpenditureRatio;
    }

    public void setBonusPointsToExpenditureRatio(float bonusPointsToExpenditureRatio) {
        this.bonusPointsToExpenditureRatio = bonusPointsToExpenditureRatio;
    }
}
