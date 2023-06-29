package it.unicam.cs.ids.loyaltyPlatform.owner;

import it.unicam.cs.ids.loyaltyPlatform.LoyaltyPlatformApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Iterable<Owner> getAllOwners(){
        return this.ownerRepository.findAll();
    }

    public Owner createOwner(Owner owner){
        if(this.checkCredentials(owner.getUsername(), owner.getPassword()) && owner.getShops().size()>=2){
            // TODO simulare pagamento
            return this.ownerRepository.save(owner);
        }
        return null;
    }

    public Owner getOwner(String vatNumber){
        return this.ownerRepository.findById(vatNumber).orElseThrow();
    }

    public Owner modifyOwner(String vatNumber, Owner owner){
        Owner ownerToUpdate = this.ownerRepository.findById(vatNumber).orElseThrow();
        if(this.checkCredentials(owner.getUsername(), owner.getPassword()) && owner.getShops().size()>=2){
            ownerToUpdate.setUsername(owner.getUsername());
            ownerToUpdate.setPassword(owner.getPassword());
            ownerToUpdate.setVatNumber(owner.getVatNumber());
            ownerToUpdate.setPayments(owner.getPayments());
            ownerToUpdate.setShops(owner.getShops());
            ownerToUpdate.setEmployeeAccount(owner.getEmployeeAccount());
            ownerToUpdate.setName(owner.getName());
            ownerToUpdate.setSurname(owner.getSurname());
            return this.ownerRepository.save(ownerToUpdate);
        }
        return null;
    }

    public Owner deleteOwner(String vatNumber){
        Owner owner = this.ownerRepository.findById(vatNumber).orElseThrow();
        this.ownerRepository.deleteById(vatNumber);
        return owner;
    }

    private boolean checkCredentials(String username, String password){
        Iterator<Owner> employeeIterator = this.getAllOwners().iterator();
        while(employeeIterator.hasNext()){
            if(employeeIterator.next().getUsername().equals(username)){
                return false;
            }
        }
        return LoyaltyPlatformApplication.checkPassword(password);
    }

    // MANCANO DUE METODI IMPLEMENTATI DA DUMITRU E CIO CHE SERVE PER IMPLEMENTARLI
}
