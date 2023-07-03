package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@MappedSuperclass
@Data
public abstract class LoyaltyProgram {

    //#region FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyaltyProgram_id_generator")
    @SequenceGenerator(name = "loyaltyProgram_id_generator", allocationSize = 1)
    @Column(name = "loyalty_program_id", nullable = false)
    protected Long id;

    @Basic(fetch = FetchType.LAZY)
    protected boolean publishedStatus;
    @Temporal(TemporalType.DATE)
    @Column(nullable = true, columnDefinition = "DateTime")
    protected Date programExpirationDate; //YYYY-MM-DD


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "loyaltyprogram_id_to_shop")
    private Set<Shop> assignedToShop;

}
