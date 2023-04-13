package com.spring.messagingwithactivemq.controller;


import com.spring.messagingwithactivemq.component.MessageConsumer;
import com.spring.messagingwithactivemq.model.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
public class PublishController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MessageConsumer messageConsumer;

    @PostMapping("/publishMessage")
    public ResponseEntity<String> publishMessage( @Valid @RequestBody SystemMessage systemMessage) {
        try {
            jmsTemplate.convertAndSend("message-queue", systemMessage);

            return new ResponseEntity<>("Sent.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getMessage")

    public ResponseEntity<Object> consumeMessage(){

        Object obj = messageConsumer.getMessage();

        if(obj == null){
            Map<String, Object> response = new HashMap<>();
            response.put("error", "No message is in queue");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }

        return ResponseEntity.ok(obj);
    }
}
