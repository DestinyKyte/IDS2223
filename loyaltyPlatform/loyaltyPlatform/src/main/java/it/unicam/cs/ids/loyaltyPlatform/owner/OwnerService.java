package it.unicam.cs.ids.loyaltyPlatform.owner;

import it.unicam.cs.ids.loyaltyPlatform.LoyaltyPlatformApplication;
import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import it.unicam.cs.ids.loyaltyPlatform.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ShopService shopService;

    public Iterable<Owner> getAllOwners(){
        return this.ownerRepository.findAll();
    }

    // TODO
    // prima d'iscriversi l'owner deve aver pagato
    // IDEA: controlla la lista pagamenti
    public ResponseEntity<Owner> createOwner(Owner owner){
        try{
            Objects.requireNonNull(owner.getVatNumber());
        } catch (NullPointerException e){
            return new ResponseEntity<>(new Owner(), HttpStatus.NOT_ACCEPTABLE);
        }
        if (this.checkCredentials(owner.getUsername(), owner.getPassword()) && owner.getShops().size()>=2){
            for(Shop shop : owner.getShops()){
                this.shopService.createShop(shop);
            }
            return new ResponseEntity<>(this.ownerRepository.save(owner), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Owner(), HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<Owner> getOwner(String vatNumber){
        try{
            return new ResponseEntity<>(this.ownerRepository.findById(vatNumber).orElseThrow(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Owner(), HttpStatus.NOT_FOUND);
        }
    }

    // TODO se voglio modificare gli shops mettendone meno di 2?
    public ResponseEntity<Owner> modifyOwner(String vatNumber, Owner owner){
        Owner ownerToUpdate;
        try{
            ownerToUpdate = this.ownerRepository.findById(vatNumber).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Owner(), HttpStatus.NOT_FOUND);
        }
        ownerToUpdate.setVatNumber(owner.getVatNumber());
        // The new username gets checked only if it's different form the old one
        if(!owner.getUsername().equals(ownerToUpdate.getUsername())) {
            if (this.checkUsername(owner.getUsername())) {
                ownerToUpdate.setUsername(owner.getUsername());
            } else {
                return new ResponseEntity<>(new Owner(), HttpStatus.NOT_ACCEPTABLE);
            }
        }
        if(LoyaltyPlatformApplication.checkPassword(owner.getPassword()) && owner.getShops().size() >= 2){
            ownerToUpdate.setPassword(owner.getPassword());
            ownerToUpdate.setShops(owner.getShops());
        } else {
            return new ResponseEntity<>(new Owner(), HttpStatus.NOT_ACCEPTABLE);
        }
        ownerToUpdate.setPayments(owner.getPayments());
        ownerToUpdate.setEmployeeAccount(owner.getEmployeeAccount());
        ownerToUpdate.setName(owner.getName());
        ownerToUpdate.setSurname(owner.getSurname());
        return new ResponseEntity<>(this.ownerRepository.save(ownerToUpdate), HttpStatus.OK);
    }

    public ResponseEntity<Owner> deleteOwner(String vatNumber){
        Owner owner;
        try {
            owner = this.ownerRepository.findById(vatNumber).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Owner(), HttpStatus.NOT_FOUND);
        }
        this.ownerRepository.deleteById(vatNumber);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    private boolean checkCredentials(String username, String password){
        return this.checkUsername(username) && LoyaltyPlatformApplication.checkPassword(password);
    }

    private boolean checkUsername(String username){
        for (Owner owner : this.getAllOwners()) {
            if (owner.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}
