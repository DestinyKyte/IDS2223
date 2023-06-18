package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses.ProgramRatio;
import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@MappedSuperclass
public abstract class LoyaltyProgram {

    //#region FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "loyalty_program_id", nullable = false)
    protected Long id;

    protected boolean status;
    protected Date expirationDate;

    //private ProgramParameter parameter;
    // WE MUST UNDERSTAND HOW TO HAVE A SUPERCLASS IN PROGRAMTYPE WITHOUT BREAKING OUR CODE

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "loyalty_program_id")
    private Set<ProgramRatio> ratios;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_shop")
    private Set<Shop> assignedToShop;
    //endregion

    //region CONSTRUCTORS: REPLACEABLE WITH @ALLARGSCONSTRUCTOR AND @NOARGSCONSTRUCTOR
    public LoyaltyProgram() {
        super();
    }

    public LoyaltyProgram(Date expirationDate) {
        this.expirationDate = expirationDate;
        this.status = false;
    }

    public LoyaltyProgram(Date expirationDate, Set<ProgramRatio> ratios) {
        this.expirationDate = expirationDate;
        this.ratios = ratios;
        this.status = false;
    }

    public LoyaltyProgram(Date expirationDate, Set<ProgramRatio> ratios, Set<Shop> assignedToShop) {
        this.expirationDate = expirationDate;
        this.ratios = ratios;
        this.assignedToShop = assignedToShop;
        this.status = false;
    }
    //endregion

    //#region ADDITIONAL METHODS
    public boolean addShop(Shop shop) {
        return this.assignedToShop.add(shop);
    }

    public boolean addRatio(ProgramRatio ratio) {
        return this.ratios.add(ratio);
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


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Set<ProgramRatio> getRatios() {
        return ratios;
    }

    public void setRatios(Set<ProgramRatio> ratios) {
        this.ratios = ratios;
    }

    public Set<Shop> getAssignedToShop() {
        return assignedToShop;
    }

    public void setAssignedToShop(Set<Shop> assignedToShop) {
        this.assignedToShop = assignedToShop;
    }
    //endregion
}
