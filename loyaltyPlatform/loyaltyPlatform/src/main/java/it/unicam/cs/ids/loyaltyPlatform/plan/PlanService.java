package it.unicam.cs.ids.loyaltyPlatform.plan;

import it.unicam.cs.ids.loyaltyPlatform.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public PlanService(){
        // TODO
        // dopo la creazione rimane attivo su un suo thread per controllare se ci sono messaggi da inviare
    }

    public Iterable<Plan> getAllPlans(){
        return this.planRepository.findAll();
    }

    public ResponseEntity<Plan> getPlan(Long campaign){
        try{
            return new ResponseEntity<>(this.planRepository.findById(campaign).orElseThrow(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(new Plan(), HttpStatus.NOT_FOUND);
        }
    }

    public void deletePlan(Long campaign){
        this.planRepository.deleteById(campaign);
    }

    public void putIntoPlan(Long campaign, Message message){
        if(this.planRepository.findById(campaign).isEmpty()){
            ArrayList<Message> plannedMessages = new ArrayList<>();
            plannedMessages.add(message);
            this.planRepository.save(new Plan(campaign, plannedMessages));
            System.out.println();
            System.out.println("==================================================");
            System.out.println("=> message " + message.getId() + " put into a NEW plan");
            System.out.println("==================================================");
        } else {
            Plan plan = this.planRepository.findById(campaign).orElseThrow();
            plan.getPlannedMessages().add(message);
            System.out.println();
            System.out.println("==================================================");
            System.out.println("=> message " + message.getId() + " put into a plan");
            System.out.println("==================================================");
        }
    }

    public void deliverNow(Long messageId){
        // TODO
        // il messaggio viene inviato
        System.out.println();
        System.out.println("==================================================");
        System.out.println("=> Message " + messageId + " delivered!");
        System.out.println("==================================================");
    }
}
