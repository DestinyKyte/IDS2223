package it.unicam.cs.ids.loyaltyPlatform.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public Iterable<Campaign> getAllCampaigns(){
        return this.campaignRepository.findAll();
    }

    public Campaign createCampaign(Campaign campaign){
        return this.campaignRepository.save(campaign);
    }

    public Campaign getCampaign(Long id){
        return this.campaignRepository.findById(id).orElseThrow();
    }

    public Campaign modifyCampaign(Long id, Campaign campaign){
        Campaign campaignToUpdate = this.campaignRepository.findById(id).orElseThrow();
        campaignToUpdate.setMessages(campaign.getMessages());
        campaignToUpdate.setShops(campaign.getShops());
        return this.campaignRepository.save(campaignToUpdate);
    }

    public void deleteCampaign(Long id){
        this.campaignRepository.deleteById(id);
    }

    public Campaign changeStatusCampaign(Long id){
        Campaign campaignToChange = this.campaignRepository.findById(id).orElseThrow();
        campaignToChange.setPublished(true);
        return this.campaignRepository.save(campaignToChange);
    }
}
