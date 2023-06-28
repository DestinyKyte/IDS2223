package it.unicam.cs.ids.loyaltyPlatform.message;

import java.util.List;

public class Sender {
    private final List<Long> subscribers;

    public Sender(List<Long> subscribers) {
        this.subscribers = subscribers;
        // TODO dopo la creazione, Sender rimane sempre attivo per controllare se ci sono messaggi da inviare
    }

    public void subscribe(Long messageId){
        this.subscribers.add(messageId);
    }

    public void unsubscribe(Long messageId){
        this.subscribers.remove(messageId);
    }
}
