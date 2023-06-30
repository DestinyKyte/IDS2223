package it.unicam.cs.ids.loyaltyPlatform.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer){
        return this.consumerService.createConsumer(consumer);
    }

    @GetMapping("/consumers/{id}")
    public ResponseEntity<Consumer> getConsumer(@PathVariable Long id){
        return this.consumerService.getConsumer(id);
    }

    @PutMapping("/consumers/{id}")
    public ResponseEntity<Consumer> modifyConsumer(@PathVariable Long id, @RequestBody Consumer consumer){
        return this.consumerService.modifyConsumer(id, consumer);
    }

    @DeleteMapping("/consumers/{id}")
    public ResponseEntity<Consumer> deleteConsumer(@PathVariable Long id){
        return this.consumerService.deleteConsumer(id);
    }
}
