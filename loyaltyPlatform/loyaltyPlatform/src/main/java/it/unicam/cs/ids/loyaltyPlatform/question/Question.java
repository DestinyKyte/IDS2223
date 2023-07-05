package it.unicam.cs.ids.loyaltyPlatform.question;

import it.unicam.cs.ids.loyaltyPlatform.PendingAnswers.UnionProgramPendingAnswers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "QUESTION")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "sender", length = 11)
    private String sender;

    @Column(name = "receiver", length = 11)
    private String receiver;

    @Column(name = "data_sender")
    private Date senderDate;

    @Column(name = "displayed", columnDefinition = "Date default null")
    private Date displayed;

    @Column(name = "answer", columnDefinition = "Boolean default false")
    private Boolean answer;

    @Column(name = "has_answer", columnDefinition = "Boolean default false")
    private Boolean hasAnswer;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "pending_answers")
    private UnionProgramPendingAnswers pendingAnswers;

    public Question(String sender, String receiver, Date senderDate, UnionProgramPendingAnswers pendingAnswers, String message){
        this.sender = sender;
        this.receiver = receiver;
        this.senderDate = senderDate;
        this.pendingAnswers = pendingAnswers;
        this.message = message;
        this.hasAnswer = false;
        this.answer =false;
    }
}