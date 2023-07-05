package it.unicam.cs.ids.loyaltyPlatform.consumer;

import it.unicam.cs.ids.loyaltyPlatform.payment.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;

    private String password;

    private String phoneNumber;

    private String emailAddress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Payment> payments;

    private String name;

    private String surname;
}
