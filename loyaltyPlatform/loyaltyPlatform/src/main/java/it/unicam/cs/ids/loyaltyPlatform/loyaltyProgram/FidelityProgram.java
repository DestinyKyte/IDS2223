package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "FIDELITY_PROGRAM")
public class FidelityProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fidelity_id")
    private int fidelityId;

    @Column(name = "expiration_start", nullable = false)
    private Date expirationStart;

    @Column(name = "expiration_finish", nullable = false)
    private Date expirationFinish;

    @Column(name = "status", columnDefinition = "boolean default false")
    private Boolean status;

    @Column(name = "program_type", nullable = false)
    private String programType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fidelityProgram")
    private List<Shop> shopList;

    public FidelityProgram() {
    }

    public FidelityProgram(Date expirationStart, Date expirationFinish, String programType) {
        this.expirationStart = expirationStart;
        this.expirationFinish = expirationFinish;
        this.programType = programType;
    }

    public int getFidelityId() {
        return fidelityId;
    }

    public void setFidelityId(int fidelityId) {
        this.fidelityId = fidelityId;
    }

    public Date getExpirationStart() {
        return expirationStart;
    }

    public void setExpirationStart(Date expirationStart) {
        this.expirationStart = expirationStart;
    }

    public Date getExpirationFinish() {
        return expirationFinish;
    }

    public void setExpirationFinish(Date expirationFinish) {
        this.expirationFinish = expirationFinish;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}