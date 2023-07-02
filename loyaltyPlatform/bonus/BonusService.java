package it.unicam.cs.ids.loyaltyPlatform.bonus;

import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.LoyaltyProgram;
import it.unicam.cs.ids.loyaltyPlatform.subscription.SubscriptionService;
import it.unicam.cs.ids.loyaltyPlatform.supermaketCashRegisterSimulator.Product;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BonusService{
    private final SubscriptionService subscriptionService;
    private final BonusRepository bonusRepository;

    public BonusService(SubscriptionService subscriptionService, BonusRepository bonusRepository) {
        this.subscriptionService = subscriptionService;
        this.bonusRepository = bonusRepository;
    }


    /**
     * It creates a Set of bonuses from the given {@link LoyaltyProgram} ID if it exists.
     * It then sets the bonus's ID to that of the program it is created from.
     * @param programID LoyaltyProgram ID.
     * @return a Set of Bonuses if the LoyaltyProgram exists. Else null.
     */
    public Set<Bonus> createBonuses(Long programID) {
        Set<Bonus> bonuses = new HashSet<>();
        bonusRepository.findLoyaltyProgramBonusCreationDTOValuesByID(programID).forEach(dto -> {
                Bonus bonus = new Bonus(dto.getDiscountPercentage() / 100F, dto.getDate(), dto.getBenefit()/100F);
                bonus.setLoyaltyProgramId(programID);
                bonuses.add(bonus);
            });
        return bonuses;
    }

    /**
     * Applies bonuses to products to determine the final price of the product.
     * @param loyaltyProgramID program id.
     * @param products products from the shop.
     * @return the new value of the total cost.
     */
    public float applyBonus(Long loyaltyProgramID, List<Product> products){
      /*  return (float) products.stream().
                flatMap(product -> bonusRepository.findBonusByLoyaltyProgramID(loyaltyProgramID)
                        .stream().map(bonus ->applyDiscount(product, bonus)))
                .mapToDouble(Float::doubleValue)
                .sum();*/
        return (float) bonusRepository.findBonusByLoyaltyProgramID(loyaltyProgramID)
                .stream()
                .flatMap(bonus -> products.stream().map(product -> applyDiscount(product, bonus)))
                .mapToDouble(Float::doubleValue)
                .sum();
    }

    /**
     * It applies the discounts on a bonus to a product.
     * When I complete the proper settings it will filter by category or product id too.
     * @param product product to apply bonus on.
     * @param bonus bonus to apply on product.
     * @return the new price value.
     */
    private float applyDiscount(Product product, Bonus bonus) {
        return product.getPrice() - (bonus.getDiscountPercentage() * product.getPrice());
    }

}
