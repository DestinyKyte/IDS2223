package it.unicam.cs.ids.loyaltyPlatform.plan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long campaign;


    private List<Long> plannedMessages;
}
