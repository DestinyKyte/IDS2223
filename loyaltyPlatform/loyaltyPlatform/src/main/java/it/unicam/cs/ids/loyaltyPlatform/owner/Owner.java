package it.unicam.cs.ids.loyaltyPlatform.owner;

import it.unicam.cs.ids.loyaltyPlatform.employeeAccount.EmployeeAccount;
import it.unicam.cs.ids.loyaltyPlatform.payment.Payment;
import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany
    private List<Payment> payments;

    @OneToMany
    private List<Shop> shops;

    /*@OneToMany
    private List<EmployeeAccount> employeeAccount;
     */

    private String name;

    private String surname;
}
