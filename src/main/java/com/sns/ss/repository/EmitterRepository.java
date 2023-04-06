package com.sns.ss.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class EmitterRepository {

    private Map<String, SseEmitter> emitterMap = new HashMap<>();
    public SseEmitter save(String email, SseEmitter sseEmitter){
        final String key = getKey(email);
        emitterMap.put(key, sseEmitter);
        log.info("Set sseEmitter {}", email);
        return sseEmitter;
    }

    public Optional<SseEmitter> get(String email){
        final String key = getKey(email);
        log.info("Get sseEmitter {}", email);
        return Optional.ofNullable(emitterMap.get(key));
    }

    public void delete(String email){
        emitterMap.remove(getKey(email));
    }

    public String getKey(String email){
        return "Emitter:EMAIL" + email;
    }
}
