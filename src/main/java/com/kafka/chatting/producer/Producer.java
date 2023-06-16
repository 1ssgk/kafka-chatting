package com.kafka.chatting.producer;

import com.kafka.chatting.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Message data) {
        kafkaTemplate.send("kafkaChatting", data);
    }
}
