package it.unicam.cs.ids.loyaltyPlatform.customDTOs;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.programRatio.ProgramRatioParameter;
import jakarta.persistence.*;

import java.util.Date;

public class BonusCreationDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    Date date;
    String category;
    int benefit;
    int discountPercentage;
    ProgramRatioParameter ratio;

    public BonusCreationDTO() {
    }

    public BonusCreationDTO(Date date, String category, int benefit, int discountPercentage, ProgramRatioParameter ratio) {
        this.benefit = benefit;
        this.discountPercentage = discountPercentage;
        this.date = date;
        this.category = category;
        this.ratio = ratio;
    }

    public BonusCreationDTO(Date date, String category, int discountPercentage, ProgramRatioParameter ratio) {
        this.date = date;
        this.category = category;
        this.discountPercentage = discountPercentage;
        this.ratio = ratio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getBenefit() {
        return benefit;
    }

    public void setBenefit(int benefit) {
        this.benefit = benefit;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public ProgramRatioParameter getRatio() {
        return ratio;
    }

    public void setRatio(ProgramRatioParameter ratio) {
        this.ratio = ratio;
    }
}
