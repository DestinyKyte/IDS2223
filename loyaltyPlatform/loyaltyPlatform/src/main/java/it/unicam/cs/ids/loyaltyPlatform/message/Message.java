package it.unicam.cs.ids.loyaltyPlatform.message;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Period;
import java.util.Calendar;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // genera in sequenza gli id
    private Long id;

    private Set<Long> target;

    private String content;

    private boolean willBeSentAgain;

    private Period frequency;

    private boolean willBeSentImmediately;

    private Calendar date;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getTarget() {
        return this.target;
    }

    public void setTarget(Set<Long> target) {
        this.target = target;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean willBeSentAgain() {
        return this.willBeSentAgain;
    }

    public void willBeSentAgain(boolean willBeSentAgain) {
        this.willBeSentAgain = willBeSentAgain;
    }

    public Period getFrequency() {
        return this.frequency;
    }

    public void setFrequency(Period frequency) {
        this.frequency = frequency;
    }

    public boolean willBeSentImmediately() {
        return this.willBeSentImmediately;
    }

    public void willBeSentImmediately(boolean willBeSentImmediately) {
        this.willBeSentImmediately = willBeSentImmediately;
    }

    public Calendar getDate() {
        return this.date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
