package com.sns.ss.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
//알람을 발생시킨 사람
public class AlarmArgs {
    private Long fromMemberId;
    private Long targetId;
}
