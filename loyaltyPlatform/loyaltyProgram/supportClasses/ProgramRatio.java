package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.supportClasses;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "ratios")
public class ProgramRatio{
    @Id
    @GeneratedValue(generator = "ratios_id_generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ratios_id_generator")
    @Column(name = "ratio_id", nullable = false)
    private Long id;

    // In accordance with what is spent, a percentage of benefit is returned. Example, if you spend 100 euros
    // on a ratio of 10,
    private int programBenefitToExpenseRatio;


    //Credo che sarà necessario mettere
    // un oggetto loyaltyProgram in questa classe
    // per la referenza bidirezionale.
    @Column(name = "ratioParameter")
    @Enumerated(EnumType.STRING)
    private ProgramParameter parameter;

    @Column(name = "ratioParameterDiscountPercentage")
    private int parameterValueOfDiscountInPercentage;
    @Column(name = "categoryAppliedTo")
    private String applicableToCategoryOfProduct;
    @Temporal(TemporalType.DATE)
    private Date applicableDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProgramBenefitToExpenseRatio() {
        return programBenefitToExpenseRatio;
    }

    public void setProgramBenefitToExpenseRatio(int programBenefitToExpenseRatio) {
        this.programBenefitToExpenseRatio = programBenefitToExpenseRatio;
    }

    public ProgramParameter getParameter() {
        return parameter;
    }

    public void setParameter(ProgramParameter parameter) {
        this.parameter = parameter;
    }

    public int getParameterValueOfDiscountInPercentage() {
        return parameterValueOfDiscountInPercentage;
    }

    public void setParameterValueOfDiscountInPercentage(int parameterValueOfDiscountInPercentage) {
        this.parameterValueOfDiscountInPercentage = parameterValueOfDiscountInPercentage;
    }

    public String getApplicableToCategoryOfProduct() {
        return applicableToCategoryOfProduct;
    }

    public void setApplicableToCategoryOfProduct(String applicableToCategoryOfProduct) {
        this.applicableToCategoryOfProduct = applicableToCategoryOfProduct;
    }

    public Date getApplicableDate() {
        return applicableDate;
    }

    public void setApplicableDate(Date applicableDate) {
        this.applicableDate = applicableDate;
    }
}
