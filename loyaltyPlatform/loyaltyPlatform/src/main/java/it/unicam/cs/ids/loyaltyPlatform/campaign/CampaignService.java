package it.unicam.cs.ids.loyaltyPlatform.campaign;

import it.unicam.cs.ids.loyaltyPlatform.message.Message;
import it.unicam.cs.ids.loyaltyPlatform.message.MessageService;
import it.unicam.cs.ids.loyaltyPlatform.plan.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PlanService planService;

    public Iterable<Campaign> getAllCampaigns(){
        return this.campaignRepository.findAll();
    }

    public Campaign createCampaign(Campaign campaign){
        return this.campaignRepository.save(campaign);
    }

    public ResponseEntity<Campaign> getCampaign(Long id){
        try{
            return new ResponseEntity<>(this.campaignRepository.findById(id).orElseThrow(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Campaign(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Campaign> modifyCampaign(Long id, Campaign campaign){
        Campaign campaignToUpdate;
        try {
            campaignToUpdate = this.campaignRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Campaign(), HttpStatus.NOT_FOUND);
        }

        // The method allows to modify a campaign only if it hasn't been published
        if(!campaignToUpdate.isPublished()){
            campaignToUpdate.setMessages(campaign.getMessages());
            campaignToUpdate.setShops(campaign.getShops());
            return new ResponseEntity<>(this.campaignRepository.save(campaignToUpdate), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Campaign(), HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<Campaign> deleteCampaign(Long id){
        Campaign campaign;
        try {
            campaign = this.campaignRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Campaign(), HttpStatus.NOT_FOUND);
        }
        this.planService.deletePlan(campaign.getId());
        this.campaignRepository.deleteById(id);
        return new ResponseEntity<>(campaign, HttpStatus.OK);
    }

    public ResponseEntity<Campaign> publishCampaign(Long id, Campaign campaign){
        Campaign campaignToUpdate;
        try {
            campaignToUpdate = this.campaignRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Campaign(), HttpStatus.NOT_FOUND);
        }
        if(campaign.getShops().size() >= 2){
            campaignToUpdate.setShops(campaign.getShops());
        } else {
            return new ResponseEntity<>(new Campaign(), HttpStatus.NOT_ACCEPTABLE);
        }
        campaignToUpdate.setPublished(true);

        // Right after publishing the campaign, the method puts the messages into the plan
        Message auxMessage;
        for(Message message : campaignToUpdate.getMessages()){
            auxMessage = Objects.requireNonNull(this.messageService.getMessage(message.getId()).getBody());
            if(auxMessage.isToDeliverAgain() || !auxMessage.isToDeliverImmediately()){
                this.planService.putIntoPlan(id, message);
            }
            if(auxMessage.isToDeliverImmediately()){
                this.planService.deliverNow(message.getId());
            }
        }

        return new ResponseEntity<>(this.campaignRepository.save(campaignToUpdate), HttpStatus.OK);
    }
}
