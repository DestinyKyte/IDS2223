package it.unicam.cs.ids.loyaltyPlatform.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public Iterable<Shop> getAllShops(){
        return this.shopRepository.findAll();
    }

    public Shop createShop(Shop shop){
        return this.shopRepository.save(shop);
    }

    public ResponseEntity<Shop> getShop(Long id){
        try{
            return new ResponseEntity<>(this.shopRepository.findById(id).orElseThrow(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Shop(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Shop> modifyShop(Long id, Shop shop){
        Shop shopToUpdate;
        try {
            shopToUpdate = this.shopRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Shop(), HttpStatus.NOT_FOUND);
        }
        shopToUpdate.setOwnerVatNumber(shop.getOwnerVatNumber());
        shopToUpdate.setAddress(shop.getAddress());
        shopToUpdate.setPhoneNumber(shop.getPhoneNumber());
        return new ResponseEntity<>(this.shopRepository.save(shopToUpdate), HttpStatus.OK);
    }

    public ResponseEntity<Shop> deleteShop(Long id){
        Shop shop;
        try {
           shop = this.shopRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Shop(), HttpStatus.NOT_FOUND);
        }
        this.shopRepository.deleteById(id);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

}