package com.kafka.chatting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.chatting.dto.Message;
import com.kafka.chatting.producer.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    private final Producer producer;

    @MessageMapping("/chat/enter")
    public void enter(Message message) {
        log.info("/chat/enter  ={}",message);
        //simpMessageSendingOperations.convertAndSend("/sub/channel/" + message.getChannelId(), message);
    }

    @MessageMapping("/chat/quit")
    public void quit() {
        log.info("/chat/quit");

    }

    @MessageMapping("/chat/message")
    public void message(Message message) throws JsonProcessingException {
        log.info("/chat/message ={}",message.getChannelId());
        // 메세지가 들어오면 소켓이 바로 전송하는게 아니라 카프카를 이용해서 처리

        producer.sendMessage(message);
    }
}
