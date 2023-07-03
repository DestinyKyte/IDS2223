package it.unicam.cs.ids.loyaltyPlatform.owner;

import it.unicam.cs.ids.loyaltyPlatform.shop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<Owner> createOwner(
            @RequestParam("partitaIva") String partitaIva,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("personalInfo") String personalInfo,
            @RequestBody List<Shop> shopList) {

        Owner createdOwner = ownerService.createOwner(partitaIva, username, password, personalInfo, shopList);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{partitaIva}")
    public ResponseEntity<Owner> getOwnerByPartitaIva(@PathVariable String partitaIva) {
        Owner owner = ownerService.getOwnerByPartitaIva(partitaIva);
        if (owner != null) {
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @DeleteMapping("/{partitaIva}")
    public ResponseEntity<Void> deleteOwner(@PathVariable String partitaIva) {
        boolean deleted = ownerService.deleteOwner(partitaIva);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}