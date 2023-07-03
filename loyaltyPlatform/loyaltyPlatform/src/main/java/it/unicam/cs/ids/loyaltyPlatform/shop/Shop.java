package it.unicam.cs.ids.loyaltyPlatform.shop;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.FidelityProgram;
import it.unicam.cs.ids.loyaltyPlatform.owner.Owner;
import jakarta.persistence.*;

@Entity
@Table(name = "SHOP")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private int shopId;

    @Column(name = "legal_terms", nullable = false)
    private String legalTerms;

    @Column(name = "essential_info")
    private String essentialInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fidelity_id", referencedColumnName = "fidelity_id")
    private FidelityProgram fidelityProgram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partita_iva", referencedColumnName = "partita_iva")
    private Owner owner;

    public Shop() {
    }

    public Shop(String legalTerms, String essentialInfo, Owner owner) {
        this.legalTerms = legalTerms;
        this.essentialInfo = essentialInfo;
        this.owner = owner;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getLegalTerms() {
        return legalTerms;
    }

    public void setLegalTerms(String legalTerms) {
        this.legalTerms = legalTerms;
    }

    public String getEssentialInfo() {
        return essentialInfo;
    }

    public void setEssentialInfo(String essentialInfo) {
        this.essentialInfo = essentialInfo;
    }

    public FidelityProgram getFidelityProgram() {
        return fidelityProgram;
    }

    public void setFidelityProgram(FidelityProgram fidelityProgram) {
        this.fidelityProgram = fidelityProgram;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
