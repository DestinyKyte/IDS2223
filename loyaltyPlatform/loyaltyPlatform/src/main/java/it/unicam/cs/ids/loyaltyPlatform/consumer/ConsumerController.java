package it.unicam.cs.ids.loyaltyPlatform.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/consumers")
    public Iterable<Consumer> getAllConsumers(){
        return this.consumerService.getAllConsumers();
    }

    @PostMapping("/consumers")
    public Consumer createConsumer(@RequestBody Consumer consumer){
        return this.consumerService.createConsumer(consumer);
    }

    @GetMapping("/consumers/{id}")
    public Consumer getConsumer(@PathVariable Long id){
        return this.consumerService.getConsumer(id);
    }

    @PutMapping("/consumers/{id}")
    public Consumer modifyConsumer(@PathVariable Long id, @RequestBody Consumer consumer){
        return this.consumerService.modifyConsumer(id, consumer);
    }

    @DeleteMapping("/consumers/{id}")
    public void deleteConsumer(@PathVariable Long id){
        this.consumerService.deleteConsumer(id);
    }
}
