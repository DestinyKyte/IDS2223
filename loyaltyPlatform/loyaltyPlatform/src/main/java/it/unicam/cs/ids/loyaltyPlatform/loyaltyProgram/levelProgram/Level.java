package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.levelProgram;

import jakarta.persistence.*;

@Entity(name = "level")
public class Level {
    @Id
    private Long id;

    private float accessPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
