package com.sns.ss.service;

import com.sns.ss.exception.ErrorCode;
import com.sns.ss.exception.SnsApplicationException;
import com.sns.ss.repository.EmitterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
@Service
public class AlarmService {

    private final static Long DEFAULT_TIMEOUT = 60L * 1000 * 60;
    private final static String ALARM_NAME = "alarm";
    //front handle name

    private final EmitterRepository emitterRepository;

    public AlarmService(EmitterRepository emitterRepository) {
        this.emitterRepository = emitterRepository;
    }

    public void send(Long alarmId, String email){
        emitterRepository.get(email).ifPresentOrElse(sseEmitter -> {
            try {
                sseEmitter.send(SseEmitter.event().id(alarmId.toString()).name(ALARM_NAME));
            }catch (IOException e){
                emitterRepository.delete(email);
                throw new SnsApplicationException(ErrorCode.ALARM_CONNECT_ERROR);
            }
        }, () -> log.info("No emitter found"));
    }

    public SseEmitter connectAlarm(String email){
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(email, sseEmitter);
        sseEmitter.onCompletion(() -> emitterRepository.delete(email));
        sseEmitter.onTimeout(() ->emitterRepository.delete(email));


        try {
            sseEmitter.send(SseEmitter.event().id("").name(ALARM_NAME).data("connect completed"));
        }catch (IOException e){
            throw new SnsApplicationException(ErrorCode.ALARM_CONNECT_ERROR);
        }
        return sseEmitter;
    }
}
