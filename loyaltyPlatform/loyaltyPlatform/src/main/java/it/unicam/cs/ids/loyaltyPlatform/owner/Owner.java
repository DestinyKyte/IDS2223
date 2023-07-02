package it.unicam.cs.ids.loyaltyPlatform.owner;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    private List<Long> payments;

    private List<Long> shops;

    private List<Long> employeeAccount;

    private String name;

    private String surname;
}
