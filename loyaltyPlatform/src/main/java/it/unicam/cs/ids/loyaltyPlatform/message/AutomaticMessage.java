package it.unicam.cs.ids.loyaltyPlatform.message;

import java.util.Date;

public class AutomaticMessage extends Message{
    //mettiamo una data.
    // Se il campo frequenza è specificato allora il messaggio verrà mandato con quella frequenza,
    // altrimenti viene mandato per la data specificata e basta.
   // private Optional frequency;
    private Date sendDate;
}
