package it.unicam.cs.ids.loyaltyPlatform.subscription;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.levelProgram.Level;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.pointsProgram.Points;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.awt.*;

@Entity
@Table(name = "subscription")
@AllArgsConstructor
@NoArgsConstructor
@Data
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

}
