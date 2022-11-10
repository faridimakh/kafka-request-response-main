package com.learning.kafkaintegration.producer;

import com.learning.kafkaintegration.messages.ResponseMessage;
import com.learning.kafkaintegration.model.WikiChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
public class ResponseGenerator {
    @Value("${kafka.wikichanges.producer.topic}")
    private String topic;

    private KafkaTemplate<String, List<ResponseMessage>> responseMessageKafkaTemplate;

    @Autowired
    public ResponseGenerator(KafkaTemplate<String, List<ResponseMessage>> responseMessageKafkaTemplate) {
        this.responseMessageKafkaTemplate = responseMessageKafkaTemplate;
    }

    public void sendResponse(List<WikiChange> wikiChangeList, String correlationId) {
        log.info("in sendResponse(), wikiChangeList.size(): {}", wikiChangeList.size());
        log.info("pushing response messages for correlationId: {} to kafka", correlationId);

            Message<List<WikiChange>> message = MessageBuilder
                    .withPayload(wikiChangeList)
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, correlationId)
                    .build();

            sendResponseMessage(message);
    }

    private void sendResponseMessage(Message message) {
        responseMessageKafkaTemplate.send(message);

    }
}
