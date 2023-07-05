package it.unicam.cs.ids.loyaltyPlatform.PendingAnswers;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.UnionProgram.UnionProgram;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UNION_PROGRAM_PENDING_ANSWERS")
public class UnionProgramPendingAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "union_pending_id")
    private Long unionPendingId;

    // false = invito; true = richiesta.
    @Column(name = "type_question")
    private Boolean typeQuestion;

    @Column(name = "total_number_question")
    private Integer totalNumberQuestion;

    @Column(name = "total_answers")
    private Integer totalAnswers;

    @Column(name = "block")
    private Boolean block;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "union_program")
    private UnionProgram unionProgram;

    public UnionProgramPendingAnswers(Boolean typeQuestion, UnionProgram unionProgram, Integer totalNumberQuestion) {
        this.typeQuestion = typeQuestion;
        this.unionProgram = unionProgram;
        this.totalAnswers = 0;
        this.totalNumberQuestion = totalNumberQuestion;
        this.block = false;
    }

    public Long getUnionPendingId() {
        return unionPendingId;
    }

    public void setUnionPendingId(Long unionPendingId) {
        this.unionPendingId = unionPendingId;
    }

    public Boolean getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(Boolean typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public Integer getTotalNumberQuestion() {
        return totalNumberQuestion;
    }

    public void setTotalNumberQuestion(Integer totalNumberQuestion) {
        this.totalNumberQuestion = totalNumberQuestion;
    }

    public Integer getTotalAnswers() {
        return totalAnswers;
    }

    public void setTotalAnswers(Integer totalAnswers) {
        this.totalAnswers = totalAnswers;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public UnionProgram getUnionProgram() {
        return unionProgram;
    }

    public void setUnionProgram(UnionProgram unionProgram) {
        this.unionProgram = unionProgram;
    }
}