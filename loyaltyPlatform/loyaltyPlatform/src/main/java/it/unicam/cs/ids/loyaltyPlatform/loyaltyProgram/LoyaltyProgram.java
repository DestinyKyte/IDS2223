package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramParameter;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramRatio;
import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // cannot be used in a mappedsuperclass
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
    @Column(nullable = false, columnDefinition = "DateTime")
    protected Date programExpirationDate; //YYYY-MM-DD


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "loyaltyprogram_id_to_shop")
    private Set<Shop> assignedToShop;
    //endregion


    //#region ADDITIONAL METHODS
    public boolean addShop(Shop shop){
        return this.assignedToShop.add(shop);
    }


    //endregion

    //#region GETTERS AND SETTERS METHODS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //filters by id(programID -> consumerID)


    public boolean isPublishedStatus() {
        return publishedStatus;
    }

    public void setPublishedStatus(boolean publishedStatus) {
        this.publishedStatus = publishedStatus;
    }

    public Date getExpirationDate() {
        return programExpirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.programExpirationDate = expirationDate;
    }


    public Set<Shop> getAssignedToShop() {
        return assignedToShop;
    }

    public void setAssignedToShop(Set<Shop> assignedToShop) {
        this.assignedToShop = assignedToShop;
    }
    //endregion
}
