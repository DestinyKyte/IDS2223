package it.unicam.cs.ids.loyaltyPlatform.shop;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//da aggiungere sono i termini legali e informazioni essenziali
}
