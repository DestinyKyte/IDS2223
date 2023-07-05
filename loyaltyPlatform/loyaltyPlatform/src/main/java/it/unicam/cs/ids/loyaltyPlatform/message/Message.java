package it.unicam.cs.ids.loyaltyPlatform.message;

import it.unicam.cs.ids.loyaltyPlatform.consumer.Consumer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Period;
import java.util.Calendar;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // genera in sequenza gli id
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Consumer> target;

    private String content;

    private boolean toDeliverAgain;

    private Period frequency;

    private boolean toDeliverImmediately;

    private Calendar date;

    // TODO il getAllMessages restituisce anche le email a causa della relazione di ereditarieta'
}
