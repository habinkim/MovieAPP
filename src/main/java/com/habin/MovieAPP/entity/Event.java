package com.habin.MovieAPP.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.habin.MovieAPP.entity.enums.EventType;
import com.habin.MovieAPP.entity.history.BaseEntity;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 이벤트 Entity 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 17
* @Time : 20:46:56
*/

@SuperBuilder(toBuilder = true)
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_EVENT")
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @ElementCollection(targetClass = EventType.class)
    @CollectionTable(name = "VIEW_EVENT_TYPE")
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private List<EventType> eventType;

    @Column(nullable = false, length = 700)
    private String eventDesc;
    
}
