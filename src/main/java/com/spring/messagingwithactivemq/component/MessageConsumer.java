package com.spring.messagingwithactivemq.component;


import com.spring.messagingwithactivemq.model.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MessageConsumer {



    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    List<Object> list = new ArrayList<>();
    @JmsListener(destination = "message-queue")
    public void messageListener(SystemMessage systemMessage) {
        this.list.add(systemMessage);
        LOGGER.info("Message received! {}", systemMessage);
    }


    public List<Object> getMessage(){
        if(list.isEmpty()){
            return null;
        }

        List<Object> temp = new ArrayList<>(list);
        list.removeAll(temp);
        return temp;
    }


}
