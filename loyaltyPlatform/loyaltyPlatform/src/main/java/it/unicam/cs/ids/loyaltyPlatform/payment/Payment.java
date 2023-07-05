package it.unicam.cs.ids.loyaltyPlatform.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
}
