package it.unicam.cs.ids.loyaltyPlatform.email;

import it.unicam.cs.ids.loyaltyPlatform.message.Message;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.File;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Email extends Message {

    // TODO
    // upload del file per lo style
    private File style;
}
