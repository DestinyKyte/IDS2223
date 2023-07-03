package it.unicam.cs.ids.loyaltyPlatform.campaign;

import it.unicam.cs.ids.loyaltyPlatform.message.Message;
import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // genera in sequenza gli id
    private Long id;
    @OneToMany
    private List<Message> messages;
    @OneToMany
    private List<Shop> shops;

    @Value("$some.key:false")
    private boolean isPublished;
}
