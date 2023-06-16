package com.kafka.chatting.consumer;

import com.kafka.chatting.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageConsumer {

    private final SimpMessageSendingOperations simpMessageSendingOperations;


    @KafkaListener(topics = "kafkaChatting",groupId = "consumerGroup1")
    public void consumeMessage(Message data){

        log.info("data ={} ",data);
        simpMessageSendingOperations.convertAndSend("/sub/channel/"+data.getChannelId(),data);
    }



}
