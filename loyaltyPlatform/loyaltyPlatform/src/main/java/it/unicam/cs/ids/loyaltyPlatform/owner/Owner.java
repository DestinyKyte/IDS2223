package it.unicam.cs.ids.loyaltyPlatform.owner;

import it.unicam.cs.ids.loyaltyPlatform.employeeAccount.EmployeeAccount;
import it.unicam.cs.ids.loyaltyPlatform.payment.Payment;
import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Owner {

    @Id
    private String vatNumber;

    private String username;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Payment> payments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Shop> shops;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EmployeeAccount> employeeAccount;

    private String name;

    private String surname;
}
