package it.unicam.cs.ids.loyaltyPlatform.shop;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // genera in automatico i getter/setter
@NoArgsConstructor // genera in automatico il costruttore che inizializza tutti i campi
@AllArgsConstructor // genera in automatico il costruttore vuoto
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // genera in sequenza gli id
    private Long id;

    private String ownerVatNumber;

    private String address;

    private String phoneNumber;

    // TODO upload del file per i legal terms

}