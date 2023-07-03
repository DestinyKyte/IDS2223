package it.unicam.cs.ids.loyaltyPlatform.UnionProgram;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.FidelityProgram;
import it.unicam.cs.ids.loyaltyPlatform.owner.Owner;
import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UNION_PROGRAM")
public class UnionProgram extends FidelityProgram {

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

    public UnionProgram(List<FidelityProgram> programList, List<Owner> members,
                        Date expirationStart, Date expirationFinish, String programType, List<Shop> shopList, Owner owner) {
        super(expirationStart, expirationFinish, programType, shopList, owner);
        this.members = members;
        this.programList = programList;
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