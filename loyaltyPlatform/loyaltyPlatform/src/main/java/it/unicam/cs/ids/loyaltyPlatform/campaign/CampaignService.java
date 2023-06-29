package it.unicam.cs.ids.loyaltyPlatform.campaign;

import it.unicam.cs.ids.loyaltyPlatform.message.Message;
import it.unicam.cs.ids.loyaltyPlatform.message.MessageService;
import it.unicam.cs.ids.loyaltyPlatform.plan.Deliver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private MessageService messageService;

    private Deliver deliver;

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
        if(!campaignToUpdate.isPublished()){
            campaignToUpdate.setMessages(campaign.getMessages());
            campaignToUpdate.setShops(campaign.getShops());
            return this.campaignRepository.save(campaignToUpdate);
        }
        return null;
    }

    public Campaign deleteCampaign(Long id){
        Campaign campaign = this.campaignRepository.findById(id).orElseThrow();

        // Before deleting the campaign, the method unschedules the messages from the sender
        for(Long messageId : campaign.getMessages()){
            this.deliver.removeFromPlan(id, messageId);
        }

        this.campaignRepository.deleteById(id);
        return campaign;
    }

    public Campaign publishCampaign(Long id, List<Long> shops){
        Campaign campaignToUpdate = this.campaignRepository.findById(id).orElseThrow();
        campaignToUpdate.setShops(shops);
        campaignToUpdate.setPublished(true);

        // Right after publishing the campaign, the method schedules the messages to the sender
        Message auxMessage;
        for(Long messageId : campaignToUpdate.getMessages()){
            auxMessage = this.messageService.getMessage(messageId);
            if(auxMessage.willBeSentAgain() || !auxMessage.willBeSentImmediately()){
                this.deliver.putIntoPlan(id, messageId);
            }
            if(auxMessage.willBeSentImmediately()){
                this.deliver.deliverNow(messageId);
            }
        }

        return this.campaignRepository.save(campaignToUpdate);
    }
}
