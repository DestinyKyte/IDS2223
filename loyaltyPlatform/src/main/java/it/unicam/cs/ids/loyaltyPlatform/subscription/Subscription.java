package it.unicam.cs.ids.loyaltyPlatform.subscription;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.levelProgram.Level;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.pointsProgram.Points;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.awt.*;

@Entity
@Table(name = "subscription")
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(generator = "Subscription_id_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "Subscription_id_generator")
    private Long id;
    private Long consumerID;
    private Long LoyaltyProgramID;
    private int points;
    @ManyToOne
    private Level level;


    //#region GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsumerID() {
        return consumerID;
    }

    public void setConsumerID(Long consumerID) {
        this.consumerID = consumerID;
    }

    public Long getLoyaltyProgramID() {
        return LoyaltyProgramID;
    }

    public void setLoyaltyProgramID(Long loyaltyProgramID) {
        LoyaltyProgramID = loyaltyProgramID;
    }

    /*public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }*/
    //#endregion
}
