package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.pointsProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramRatio;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Entity
@Table(name = "pointsloyaltyprogram")
@NoArgsConstructor
public class PointLoyaltyProgram extends LoyaltyProgram {

   //I'd rather join the column to points than let it create a separate table for the association for now.
   //Might change later
   @JoinColumn(name = "loyaltyprogram")
   @OneToMany(targetEntity = Points.class, fetch = FetchType.EAGER)
   private Set<Points> allAttributedPoints;

   @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
   private Set<ProgramRatio> ratios;

    public Set<ProgramRatio> getRatios() {
        return ratios;
    }

    public Set<Points> getAllAttributedPoints() {
      return allAttributedPoints;
   }

   public void setAllAttributedPoints(Set<Points> allAttributedPoints) {
      this.allAttributedPoints = allAttributedPoints;
   }

}
