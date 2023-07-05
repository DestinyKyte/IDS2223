package it.unicam.cs.ids.loyaltyPlatform.plan;

import it.unicam.cs.ids.loyaltyPlatform.message.Message;
import jakarta.persistence.*;
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

    @OneToMany
    private List<Message> plannedMessages;
}
