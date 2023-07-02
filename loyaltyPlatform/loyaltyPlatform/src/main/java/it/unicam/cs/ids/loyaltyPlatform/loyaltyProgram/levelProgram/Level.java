package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.levelProgram;

import jakarta.persistence.*;

@Entity(name = "level")
public class Level{
    @Id
    @GeneratedValue(generator = "level_id_generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "level_id_generator")
    private Long id;

    //the effective level, say 1-5
    private int level;
    //the points it takes to reach the level
    private float accessPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
