package it.unicam.cs.ids.loyaltyPlatform.campaign;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Campaign getCampaign(@PathVariable Long id){
        return this.campaignService.getCampaign(id);
    }

    @PutMapping("/campaigns/{id}")
    public Campaign modifyCampaign(@PathVariable Long id, @RequestBody Campaign campaign){
        return this.campaignService.modifyCampaign(id, campaign);
    }

    @DeleteMapping("/campaigns/{id}")
    public void deleteCampaign(@PathVariable Long id){
        this.campaignService.deleteCampaign(id);
    }

    @PutMapping("/publishCampaign/{id}")
    public Campaign changeStatusCampaign(@PathVariable Long id){
        return this.campaignService.changeStatusCampaign(id);
    }
}
