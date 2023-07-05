package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.programRatio.ProgramRatio;
import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class LoyaltyProgram {

    //#region FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyaltyProgram_id_generator")
    @SequenceGenerator(name = "loyaltyProgram_id_generator", allocationSize = 1)
    @Column(name = "loyalty_program_id", nullable = false)
    protected Long id;

    @Basic(fetch = FetchType.EAGER)
    protected boolean publishedStatus;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true, columnDefinition = "DateTime")
    protected Date programExpirationDate; //YYYY-MM-DD


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "loyaltyprogram_id_to_shop")
    protected Set<Shop> assignedToShop;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<ProgramRatio> ratios;

}
