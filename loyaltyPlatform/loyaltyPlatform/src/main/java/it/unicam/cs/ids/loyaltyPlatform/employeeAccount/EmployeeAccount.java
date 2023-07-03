package it.unicam.cs.ids.loyaltyPlatform.employeeAccount;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EmployeeAccount {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
