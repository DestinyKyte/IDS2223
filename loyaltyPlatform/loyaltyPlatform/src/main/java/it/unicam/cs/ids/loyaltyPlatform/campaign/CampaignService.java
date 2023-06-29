package it.unicam.cs.ids.loyaltyPlatform.campaign;

import it.unicam.cs.ids.loyaltyPlatform.util.Sender;
import it.unicam.cs.ids.loyaltyPlatform.message.Message;
import it.unicam.cs.ids.loyaltyPlatform.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private MessageService messageService;

    private Sender sender;

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

        if(campaignToUpdate.isPublished()){
            // If campaignToUpdate was public, the method unsubscribes the old messages from the sender
            for(Long messageId : campaign.getMessages()){
                this.sender.unschedule(id, messageId);
            }
            // The method substitutes the old messages with the new ones
            campaignToUpdate.setMessages(campaign.getMessages());
            // the method subscribes the new messages to the sender
            Message auxMessage;
            for(Long messageId : campaignToUpdate.getMessages()){
                auxMessage = this.messageService.getMessage(messageId);
                if(auxMessage.willBeSentAgain() || !auxMessage.willBeSentImmediately()){
                    this.sender.schedule(id, messageId);
                }
                if(auxMessage.willBeSentImmediately()){
                    this.sender.sendNow(messageId);
                }
            }
        } else {
            // If the campaign was private, the method simply substitutes the old messages with the new ones
            campaignToUpdate.setMessages(campaign.getMessages());
        }

        campaignToUpdate.setShops(campaign.getShops());
        return this.campaignRepository.save(campaignToUpdate);
    }

    public ResponseEntity<Campaign> deleteCampaign(Long id){
        Campaign campaign = this.campaignRepository.findById(id).orElseThrow();

        // Before deleting the campaign, the method unsubscribes the messages from the sender
        for(Long messageId : campaign.getMessages()){
            this.sender.unschedule(id, messageId);
        }

        this.campaignRepository.deleteById(id);
        return new ResponseEntity<>(campaign, HttpStatus.OK);
    }

    public Campaign publishCampaign(Long id, List<Long> shops){
        Campaign campaignToUpdate = this.campaignRepository.findById(id).orElseThrow();
        campaignToUpdate.setShops(shops);
        campaignToUpdate.setPublished(true);

        // Right after publishing the campaign, the method subscribes the messages to the sender
        Message auxMessage;
        for(Long messageId : campaignToUpdate.getMessages()){
            auxMessage = this.messageService.getMessage(messageId);
            if(auxMessage.willBeSentAgain() || !auxMessage.willBeSentImmediately()){
                this.sender.schedule(id, messageId);
            }
            if(auxMessage.willBeSentImmediately()){
                this.sender.sendNow(messageId);
            }
        }

        return this.campaignRepository.save(campaignToUpdate);
    }
}
