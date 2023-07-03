package it.unicam.cs.ids.loyaltyPlatform.bonus;

import it.unicam.cs.ids.loyaltyPlatform.customDTOs.BonusCreationDTO;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.programRatio.ProgramRatioParameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface BonusRepository extends CrudRepository<Bonus, Long> {

    @Query(value =
            "SELECT" +
            "* FROM bonus " +
            "WHERE loyalty_program_id = :id", nativeQuery = true)
    List<Bonus> findBonusByLoyaltyProgramID(Long id);

    List<Bonus> findBonusBy();

    /**
     * It selects loyalty programs from a unified table of all three basic loyalty program types by id
     *
     * @param id
     * @return
     */
    @Query(value =
            " SELECT" +
                    " applicable_date date," +
                    " category_applied_to category, \n" +
                    " program_benefit_to_expense_ratio benefit," +
                    " ratio_parameter_discount_percentage discountPercent, \n" +
                    " ratio_parameter ratio" +
                    " FROM (\n" +
                    "SELECT loyalty_program_id, applicable_date, category_applied_to, program_benefit_to_expense_ratio, ratio_parameter_discount_percentage, ratio_parameter \n" +
                    "FROM cashbackloyaltyprogram as P \n" +
                    "JOIN cashbackloyaltyprogram_ratios as CR ON P.loyalty_program_id = CR.cashback_loyalty_program_loyalty_program_id\n" +
                    "JOIN ratios as R ON R.ratio_id=CR.ratios_ratio_id\n" +
                    "\n" +
                    "UNION\n" +
                    "\n" +
                    "SELECT loyalty_program_id, applicable_date, category_applied_to, program_benefit_to_expense_ratio, ratio_parameter_discount_percentage, ratio_parameter \n" +
                    "FROM levelloyaltyprogram as P \n" +
                    "JOIN levelloyaltyprogram_ratios as LR ON P.loyalty_program_id = LR.level_loyalty_program_loyalty_program_id\n" +
                    "JOIN ratios as R ON R.ratio_id=LR.ratios_ratio_id\n" +
                    "\n" +
                    "UNION\n" +
                    "\n" +
                    "SELECT loyalty_program_id, applicable_date, category_applied_to, program_benefit_to_expense_ratio, ratio_parameter_discount_percentage, ratio_parameter  \n" +
                    "FROM pointsloyaltyprogram as P \n" +
                    "JOIN pointsloyaltyprogram_ratios as PR ON P.loyalty_program_id = PR.point_loyalty_program_loyalty_program_id\n" +
                    "JOIN ratios as R ON R.ratio_id=PR.ratios_ratio_id\n" +
                    ") as table1\n" +
                    "WHERE loyalty_program_id= :id",
            nativeQuery = true)
    List<Object[]> findLoyaltyProgramBonusCreationValuesByIDNative(Long id);

    /**
     * Searches for a loyalty program in the datasource that has the same ID as the one given
     * and extracts its necessary values to create a {@link BonusCreationDTO} which in turn allows to create {@link Bonus}.
     * @param id ID belonging to an existing {@link LoyaltyProgram#getId()}.
     * @return A collection of BonusCreationDTOs.
     */
    default List<BonusCreationDTO> findLoyaltyProgramBonusCreationDTOValuesByID(Long id) {
        List<Object[]> results = findLoyaltyProgramBonusCreationValuesByIDNative(id);
        List<BonusCreationDTO> bonusCreationDTOs = new ArrayList<>();

        for (Object[] result : results) {
            BonusCreationDTO bonusCreationDTO = new BonusCreationDTO();
            bonusCreationDTO.setDate((Date) result[0]);
            bonusCreationDTO.setCategory((String) result[1]);
            bonusCreationDTO.setBenefit((int) result[2]);
            bonusCreationDTO.setDiscountPercentage((int) result[3]);
            bonusCreationDTO.setRatio(ProgramRatioParameter.valueOf((String) result[4]));
            bonusCreationDTOs.add(bonusCreationDTO);
        }

        return bonusCreationDTOs;
    }
}
