package com.sns.ss.dto;

import com.sns.ss.entity.Alarm;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AlarmDto {

    private final Long alarmId;
    private final Alarm.AlarmType alarmType;
    private final AlarmArgs alarmArgs;
    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime modifiedAt;
    private final String modifiedBy;


    public static AlarmDto from(Alarm entity){
        return new AlarmDto(
                entity.getAlarmId(),
                entity.getAlarmType(),
                entity.getAlarmArgs(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
