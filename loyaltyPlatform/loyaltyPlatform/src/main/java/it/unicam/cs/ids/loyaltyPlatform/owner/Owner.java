package it.unicam.cs.ids.loyaltyPlatform.owner;

import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "OWNER")
public class Owner {

    @Id
    @Column(name = "partita_iva", length = 11)
    private String partitaIva;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "personal_info")
    private String personalInfo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "partitaIva")
    private List<Shop> shopList;

    public Owner() {
    }

    public Owner(String partitaIva, String username, String password, String personalInfo) {
        this.partitaIva = partitaIva;
        this.username = username;
        this.password = password;
        this.personalInfo = personalInfo;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}