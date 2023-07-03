package it.unicam.cs.ids.loyaltyPlatform.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping("/campaigns")
    public Iterable<Campaign> getAllCampaigns(){
        return this.campaignService.getAllCampaigns();
    }

    @PostMapping("/campaigns")
    public Campaign createCampaign(@RequestBody Campaign campaign){
        return this.campaignService.createCampaign(campaign);
    }

    @GetMapping("/campaigns/{id}")
    public ResponseEntity<Campaign> getCampaign(@PathVariable Long id){
        return this.campaignService.getCampaign(id);
    }

    @PutMapping("/campaigns/{id}")
    public ResponseEntity<Campaign> modifyCampaign(@PathVariable Long id, @RequestBody Campaign campaign){
        return this.campaignService.modifyCampaign(id, campaign);
    }

    @DeleteMapping("/campaigns/{id}")
    public ResponseEntity<Campaign> deleteCampaign(@PathVariable Long id){
        return this.campaignService.deleteCampaign(id);
    }

    @PutMapping("/publishCampaign/{id}")
    public ResponseEntity<Campaign> publishCampaign(@PathVariable Long id, @RequestBody Campaign campaign){
        return this.campaignService.publishCampaign(id, campaign);
    }
}
