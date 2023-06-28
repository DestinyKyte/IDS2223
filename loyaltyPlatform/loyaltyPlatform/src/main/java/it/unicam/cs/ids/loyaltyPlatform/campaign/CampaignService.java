package it.unicam.cs.ids.loyaltyPlatform.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseEntity<Campaign> deleteCampaign(Long id){
        Campaign campaign = this.campaignRepository.findById(id).orElseThrow();
        this.campaignRepository.deleteById(id);
        return new ResponseEntity<>(campaign, HttpStatus.OK);
    }

    public Campaign publishCampaign(Long id, List<Long> shops){
        Campaign campaignToUpdate = this.campaignRepository.findById(id).orElseThrow();
        campaignToUpdate.setShops(shops);
        campaignToUpdate.setPublished(true);
        // TODO ogni messaggio la cui frequenza e' diversa da Period.ZERO si iscrive al Sender
        // TODO se esiste almeno un messaggio la cui data di spedizione e' istantanea allora il messaggio viene spedito dopo la pubblicazione della campagna
        return this.campaignRepository.save(campaignToUpdate);
    }
}
