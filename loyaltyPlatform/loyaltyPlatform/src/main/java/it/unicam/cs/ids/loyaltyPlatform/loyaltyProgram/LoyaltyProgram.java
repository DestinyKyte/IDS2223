package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram;

import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;

import java.util.Date;
import java.util.List;

public abstract class LoyaltyProgram {
    private boolean status;
    private Date expirationDate;
    private ProgramType programType;
    private Shop assignedToShop;
    //filters by id(programID -> consumerID)
    private List<Integer> filter;
}
