package it.unicam.cs.ids.loyaltyPlatform.owner;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Owner createOwner(@RequestBody Owner owner){
        return this.ownerService.createOwner(owner);
    }

    @GetMapping("owners/{vatNumber}")
    public Owner getOwner(@PathVariable String vatNumber){
        return this.ownerService.getOwner(vatNumber);
    }

    @PutMapping("/owners/{vatNumber}")
    public Owner modifyOwner(@PathVariable String vatNumber, @RequestBody Owner owner){
        return this.ownerService.modifyOwner(vatNumber, owner);
    }

    @DeleteMapping("/owners/{vatNumber}")
    public void deleteOwner(@PathVariable String vatNumber){
        this.ownerService.deleteOwner(vatNumber);
    }

    // TODO metodi implementati da Dumitru
    /*
    public void requestToJoinUnion(){}

    public void acceptJoinRequest(){}
     */
}
