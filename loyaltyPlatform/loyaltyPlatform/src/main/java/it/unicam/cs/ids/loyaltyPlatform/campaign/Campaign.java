package it.unicam.cs.ids.loyaltyPlatform.campaign;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // genera in sequenza gli id
    private Long id;

    private List<Long> messages;

    private List<Long> shops;

    @Value("$some.key:false")
    private boolean isPublished;
}
