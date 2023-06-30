package it.unicam.cs.ids.loyaltyPlatform.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanController {

    @Autowired
    private Deliver deliver;

    @GetMapping("/plans")
    public Iterable<Plan> getAllPlans(){
        return this.deliver.getAllPlans();
    }

    @GetMapping("/plans/{campaign}")
    public ResponseEntity<Plan> getPlan(@PathVariable Long campaign){
        return this.deliver.getPlan(campaign);
    }
}
