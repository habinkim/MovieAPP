package com.habin.MovieAPP.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.habin.MovieAPP.entity.enums.Gender;
import com.habin.MovieAPP.entity.enums.Nationality;
import com.habin.MovieAPP.entity.history.BaseEntity;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 배우 정보 Entity 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 11
* @Time : 17:05:58
*/

@SuperBuilder(toBuilder = true)
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ACTOR")
public class Actor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
    private Long actorId; // 아이디

    @Column(nullable = false, length = 40)
    private String actorNm; // 배우명

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Nationality nationality; // 국적

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate birthDate; // 생년월일

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate debutDate; // 데뷔일

    @JsonManagedReference // 양방향 관계에서의 JSON 부참조 객체 파싱
	@OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieCast> movieCast; // 출연 정보
    
}
