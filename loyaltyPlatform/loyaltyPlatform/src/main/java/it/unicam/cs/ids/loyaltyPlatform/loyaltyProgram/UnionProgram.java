package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.owner.Owner;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "UNION_PROGRAM")
public class UnionProgram extends FidelityProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "union_id")
    private Integer id;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "program_list_id")
    private List<FidelityProgram> programList;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "UNION_PROGRAM_MEMBERS",
            joinColumns = @JoinColumn(name = "union_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id")
    )
    private List<Owner> members;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "unionProgram")
    private List<UnionProgramPendingAnswers> pendingAnswersList;

    public UnionProgram() {
    }

    public UnionProgram(List<FidelityProgram> programList) {
        this.programList = programList;
    }

    public int getId() {
        return id;
    }

    public List<FidelityProgram> getProgramList() {
        return programList;
    }

    public void setProgramList(List<FidelityProgram> programList) {
        this.programList = programList;
    }

    public List<Owner> getMembers() {
        return members;
    }

    public void setMembers(List<Owner> members) {
        this.members = members;
    }
}

