package it.unicam.cs.ids.loyaltyPlatform.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Shop getShop(Long id){
        return this.shopRepository.findById(id).orElseThrow();
    }

    public Shop modifyShop(Long id, Shop shop){
        Shop shopToUpdate = this.shopRepository.findById(id).orElseThrow();
        shopToUpdate.setOwnerVatNumber(shop.getOwnerVatNumber());
        shopToUpdate.setAddress(shop.getAddress());
        shopToUpdate.setPhoneNumber(shop.getPhoneNumber());
        return this.shopRepository.save(shopToUpdate);
    }

    public void deleteShop(Long id){
        this.shopRepository.deleteById(id);
    }

}