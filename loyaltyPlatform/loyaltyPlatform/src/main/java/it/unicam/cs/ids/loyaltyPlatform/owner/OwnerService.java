package it.unicam.cs.ids.loyaltyPlatform.owner;

import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner createOwner(String partitaIva,String username, String password, String personalInfo, List<Shop> shopList) {
        return ownerRepository.save(new Owner(partitaIva, username, password, personalInfo, shopList));

    }

    public Owner getOwnerByPartitaIva(String partitaIva) {
        return ownerRepository.findById(partitaIva).orElse(null);
    }

    public List<Owner> getAllOwners() {
        return (List<Owner>) ownerRepository.findAll();
    }

    public boolean deleteOwner(String partitaIva) {
        ownerRepository.deleteById(partitaIva);
        return true;
    }

}
