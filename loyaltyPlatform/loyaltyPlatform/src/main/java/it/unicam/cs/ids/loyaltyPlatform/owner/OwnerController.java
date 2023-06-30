package it.unicam.cs.ids.loyaltyPlatform.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/owners")
    public Iterable<Owner> getAllOwners(){
        return this.ownerService.getAllOwners();
    }

    @PostMapping("/owners")
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner){
        return this.ownerService.createOwner(owner);
    }

    @GetMapping("owners/{vatNumber}")
    public ResponseEntity<Owner> getOwner(@PathVariable String vatNumber){
        return this.ownerService.getOwner(vatNumber);
    }

    @PutMapping("/owners/{vatNumber}")
    public ResponseEntity<Owner> modifyOwner(@PathVariable String vatNumber, @RequestBody Owner owner){
        return this.ownerService.modifyOwner(vatNumber, owner);
    }

    @DeleteMapping("/owners/{vatNumber}")
    public ResponseEntity<Owner> deleteOwner(@PathVariable String vatNumber){
        return this.ownerService.deleteOwner(vatNumber);
    }

    // MANCANO DUE METODI IMPLEMENTATI DA DUMITRU E CIO CHE SERVE PER IMPLEMENTARLI
}
