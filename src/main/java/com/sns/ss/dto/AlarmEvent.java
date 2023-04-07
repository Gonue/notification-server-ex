package com.sns.ss.dto;


//     public static Alarm of(Member member, AlarmType alarmType, AlarmArgs alarmArgs){

import com.sns.ss.entity.Alarm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmEvent {
    private String receiveMemberId;
    private Alarm.AlarmType alarmType;
    private AlarmArgs args;

}
