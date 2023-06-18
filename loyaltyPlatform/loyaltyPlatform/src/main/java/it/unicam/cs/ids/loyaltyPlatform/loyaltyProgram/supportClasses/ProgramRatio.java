package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses;

import jakarta.persistence.*;

@Entity(name = "loyalty_program_ratio")
public class ProgramRatio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "loyalty_program_ratio_id", nullable = false)
    private Long id;

    //Se enum o meno è ancora da concludere.

    //Credo che sarà necessario mettere
    // un oggetto loyaltyProgram in questa classe
    // per la referenza bidirezionale.
    @Column(columnDefinition = "CHAR(20)")
    @Enumerated(EnumType.ORDINAL)
    private ProgramParameter parameter;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
