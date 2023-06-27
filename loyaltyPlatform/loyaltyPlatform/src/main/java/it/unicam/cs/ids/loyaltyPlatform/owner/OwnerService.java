package it.unicam.cs.ids.loyaltyPlatform.owner;

import it.unicam.cs.ids.loyaltyPlatform.LoyaltyPlatformApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    // TODO riferimento a paymentManager e shopManager

    public Iterable<Owner> getAllOwners(){
        return this.ownerRepository.findAll();
    }

    public Owner createOwner(Owner owner){
        if(LoyaltyPlatformApplication.checkCredentials(owner.getUsername(), owner.getPassword())){
            return this.ownerRepository.save(owner);
        }
        return null;
    }

    public Owner getOwner(String vatNumber){
        return this.ownerRepository.findById(vatNumber).orElseThrow();
    }

    public Owner modifyOwner(String vatNumber, Owner owner){
        Owner ownerToUpdate = this.ownerRepository.findById(vatNumber).orElseThrow();
        if(LoyaltyPlatformApplication.checkCredentials(owner.getUsername(), owner.getPassword())){
            ownerToUpdate.setUsername(owner.getUsername());
            ownerToUpdate.setPassword(owner.getPassword());
            ownerToUpdate.setVatNumber(owner.getVatNumber());
            ownerToUpdate.setPayments(owner.getPayments());
            ownerToUpdate.setShops(owner.getShops());
            ownerToUpdate.setEmployeeAccount(owner.getEmployeeAccount());
            return this.ownerRepository.save(ownerToUpdate);
        }
        return null;
    }

    public void deleteOwner(String vatNumber){
        this.ownerRepository.deleteById(vatNumber);
    }

    // TODO metodi implementati da Dumitru
    /*
    public void requestToJoinUnion(){}

    public void acceptJoinRequest(){}
     */
}
