package it.unicam.cs.ids.loyaltyPlatform.subscription;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.levelProgram.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long loyaltyProgramID;
    private int points;
    @OneToOne
    private Level level;

    public Subscription(Long programID, Long consumerID){
        this.loyaltyProgramID = programID;
        this.consumerID = consumerID;
    }
}
