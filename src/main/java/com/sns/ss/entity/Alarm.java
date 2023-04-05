package com.sns.ss.entity;

import com.sns.ss.dto.AlarmArgs;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Getter
@Setter
@Table(indexes = {
        @Index(columnList = "member_id"),
})
@Entity
public class Alarm extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alarmId;


    @ManyToOne
    @JoinColumn(name= "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;

    @Type(type = "jsonb")
    @Column(columnDefinition = "json")
    private AlarmArgs alarmArgs;


    @RequiredArgsConstructor
    @Getter
    public enum AlarmType{
        NEW_COMMENT_ON_POST("new comment"),
        NEW_LIKE_ON_POST("new like"),
        ;
        private final String alarmText;
    }


    public static Alarm of(Member member, AlarmType alarmType, AlarmArgs alarmArgs){
        Alarm entity = new Alarm();
        entity.setMember(member);
        entity.setAlarmType(alarmType);
        entity.setAlarmArgs(alarmArgs);
        return entity;
    }
}
