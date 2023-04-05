package com.sns.ss.dto.response;

import com.sns.ss.dto.AlarmArgs;
import com.sns.ss.dto.AlarmDto;
import com.sns.ss.dto.MemberDto;
import com.sns.ss.entity.Alarm;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AlarmResponse {

    private final Long alarmId;
    private final Alarm.AlarmType alarmType;
    private final AlarmArgs alarmArgs;
    private String text;
    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime modifiedAt;
    private final String modifiedBy;


    public static AlarmResponse from(AlarmDto dto){
        return new AlarmResponse(
                dto.getAlarmId(),
                dto.getAlarmType(),
                dto.getAlarmArgs(),
                dto.getAlarmType().getAlarmText(),
                dto.getCreatedAt(),
                dto.getCreatedBy(),
                dto.getModifiedAt(),
                dto.getModifiedBy()
        );
    }
}
