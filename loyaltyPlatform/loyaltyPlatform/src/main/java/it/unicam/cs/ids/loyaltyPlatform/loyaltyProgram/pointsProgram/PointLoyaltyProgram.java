package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.pointsProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.programRatio.ProgramRatio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Table(name = "pointsloyaltyprogram")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PointLoyaltyProgram extends LoyaltyProgram {

   //I'd rather join the column to points than let it create a separate table for the association for now.
   //Might change later
   @JoinColumn(name = "loyaltyprogram")
   @OneToMany(targetEntity = Points.class, fetch = FetchType.EAGER)
   private Set<Points> allAttributedPoints;

   @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
   private Set<ProgramRatio> ratios;

}
