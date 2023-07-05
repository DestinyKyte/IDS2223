package it.unicam.cs.ids.loyaltyPlatform.customDTOs;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.programRatio.ProgramRatioParameter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BonusCreationDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    LocalDate date;
    String category;
    int benefit;
    int discountPercentage;
    ProgramRatioParameter ratio;
}
