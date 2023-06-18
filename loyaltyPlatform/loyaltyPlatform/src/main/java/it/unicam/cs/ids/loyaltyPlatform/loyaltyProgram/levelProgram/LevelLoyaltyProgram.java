package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.levelProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "levelprogram")
public class LevelLoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    /*  @OneToMany(cascade = CascadeType.ALL)
      //@JoinColumn(columnDefinition = "CHAR(20)")
      private List<ProgramRatio> ratios;*/
    @OneToMany()
    //@JoinColumn()
    private List<Level> levels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
