package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.owner.Owner;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
@Table(name = "UnionProgramPendingAnswers")
public class UnionProgramPendingAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unionPending_id")
    private Integer unionPending_id;

    @Column(name = "pending")
    private Integer numberPendingAnswers;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "UNION_PROGRAM_MEMBERS_Pending",
            joinColumns = @JoinColumn(name = "unionPending_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id")
    )
    private List<Owner> membersPending;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "union_program_id")
    private UnionProgram unionProgram;


    public UnionProgramPendingAnswers(){}

    public UnionProgramPendingAnswers(@NotNull List<Owner> membersPending) {
        this.membersPending = membersPending;
        this.numberPendingAnswers = membersPending.size();
    }

    public Integer getUnionPending_id() {
        return unionPending_id;
    }

    public void setUnionPending_id(Integer unionPending_id) {
        this.unionPending_id = unionPending_id;
    }

    public List<Owner> getMembersPending() {
        return membersPending;
    }

    public void setMembersPending(List<Owner> members) {
        this.membersPending = members;
    }

    public Integer getNumberPendingAnswers() {
        return numberPendingAnswers;
    }

    public void setNumberPendingAnswers(Integer numberPendingAnswers) {
        this.numberPendingAnswers = numberPendingAnswers;
    }
}
