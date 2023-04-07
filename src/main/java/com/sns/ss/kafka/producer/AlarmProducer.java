package com.sns.ss.kafka.producer;


import com.sns.ss.dto.AlarmEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlarmProducer {


    private final KafkaTemplate<String, AlarmEvent> kafkaTemplate;

    @Value("${spring.kafka.topic.alarm}")
    private String topic;

    public void send(AlarmEvent alarmEvent){
        kafkaTemplate.send(topic, alarmEvent.getReceiveMemberId() ,alarmEvent);
        log.info("Send to Kafka finished");
    }
}
