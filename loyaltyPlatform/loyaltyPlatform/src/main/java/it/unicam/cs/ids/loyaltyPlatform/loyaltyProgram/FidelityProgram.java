package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.owner.Owner;
import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FIDELITY_PROGRAM")
@Inheritance(strategy = InheritanceType.JOINED)
public class FidelityProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "fidelity_id")
    private Long fidelityId;

    @Column(name = "expiration_start", nullable = false)
    private Date expirationStart;

    @Column(name = "expiration_finish", nullable = false)
    private Date expirationFinish;

    @Column(name = "status", columnDefinition = "boolean default false")
    private Boolean status;

    @Column(name = "program_type", nullable = false)
    private String programType;

    @Column(name = "can_be_published", columnDefinition = "Boolean default false")
    private Boolean canBePublished;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fidelityProgram")
    private List<Shop> shopList;

    public FidelityProgram(Date expirationStart, Date expirationFinish, String programType, List<Shop> shopList, Owner owner) {
        this.expirationStart = expirationStart;
        this.expirationFinish = expirationFinish;
        this.programType = programType;
        this.shopList = shopList;
        this.owner = owner;
    }

    public void setCanBePublished(Boolean canBePublished) {
        this.canBePublished = canBePublished;
    }

    public String getName() {
        return this.fidelityId.toString();
    }

    public Long getFidelityId() {
        return fidelityId;
    }

    public void setFidelityId(Long fidelityId) {
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

    public Boolean getCanBePublished() {
        return canBePublished;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}