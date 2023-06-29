package it.unicam.cs.ids.loyaltyPlatform.plan;

import it.unicam.cs.ids.loyaltyPlatform.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class Deliver {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private MessageService messageService;

    public Deliver(){
        // TODO
        // dopo la creazione rimane attivo su un suo thread per controllare se ci sono messaggi da inviare
    }

    public void putIntoPlan(Long campaign, Long message){
        if(this.planRepository.findById(campaign).isEmpty()){
            ArrayList<Long> plannedMessages = new ArrayList<>();
            plannedMessages.add(message);
            Plan plan = new Plan(campaign, plannedMessages);
        } else {
            Plan plan = this.planRepository.findById(campaign).orElseThrow();
            plan.getPlannedMessages().add(message);
        }
    }

    public void removeFromPlan(Long campaignId, Long messageId){
        Plan plan = this.planRepository.findById(campaignId).orElseThrow();
        plan.getPlannedMessages().remove(messageId);
    }

    public void deliverNow(Long messageId){
        // TODO
        // il messaggio viene inviato
        // simulazione:
        System.out.println(this.messageService.getMessage(messageId).getContent());
    }
}
