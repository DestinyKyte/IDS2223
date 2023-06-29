package it.unicam.cs.ids.loyaltyPlatform.util;

import it.unicam.cs.ids.loyaltyPlatform.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class Sender {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MessageService messageService;

    public Sender() {
        // TODO
        // dopo la creazione, Sender rimane attivo su un suo thread per controllare se ci sono messaggi da inviare
    }

    public void schedule(Long campaign, Long message){
        if(this.scheduleRepository.findById(campaign).isEmpty()){
            ArrayList<Long> scheduledMessages = new ArrayList<>();
            scheduledMessages.add(message);
            Schedule schedule = new Schedule(campaign, scheduledMessages);
        } else {
            Schedule schedule = this.scheduleRepository.findById(campaign).orElseThrow();
            schedule.getScheduledMessages().add(message);
        }
    }

    public void unschedule(Long campaignId, Long messageId){
        Schedule schedule = this.scheduleRepository.findById(campaignId).orElseThrow();
        schedule.getScheduledMessages().remove(messageId);
    }

    public void sendNow(Long messageId){
        // TODO
        // il messaggio viene inviato
        // simulazione:
        System.out.println(this.messageService.getMessage(messageId).getContent());
    }

}
