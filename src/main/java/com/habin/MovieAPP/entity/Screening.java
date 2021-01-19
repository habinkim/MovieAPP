package com.habin.MovieAPP.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.habin.MovieAPP.entity.enums.Hall;
import com.habin.MovieAPP.entity.enums.ScrType;
import com.habin.MovieAPP.entity.history.BaseEntity;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 상영 정보 Entity 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 19
* @Time : 11:27:23
*/

@SuperBuilder(toBuilder = true)
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_SCREENING")
public class Screening extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "movie", referencedColumnName = "movieId")
    private Movie movie;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Hall hall; 

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(nullable = false)
    private LocalDateTime scrStartTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(nullable = false)
    private LocalDateTime scrEndTime;

    @JsonProperty
	public ScrType getScrType() {

        LocalDateTime emStan = LocalDateTime.of(scrStartTime.toLocalDate(), LocalTime.of(10, 0, 1)); // 조조 기준
        LocalDateTime lnStan = LocalDateTime.of(scrStartTime.toLocalDate(), LocalTime.of(23, 30, 1)); // 심야 기준
        LocalDateTime lnEmBound = LocalDateTime.of(scrStartTime.toLocalDate().plusDays(1), LocalTime.of(6, 0, 1)); // 조조, 심야 경계선

		if (scrStartTime.isBefore(emStan) && scrStartTime.isAfter(lnEmBound)) {

            return ScrType.EARLY_MORNING;

        } else if(scrStartTime.isAfter(emStan) && scrStartTime.isBefore(lnStan)) {

            return ScrType.NORMAL;

        } else {

            return ScrType.LATE_NIGHT;

        }

    }
    
    @PrePersist
    public void calcScrEndTime() {
        LocalTime runningTime = movie.getRunningTime();

        scrEndTime = scrStartTime.plus(runningTime.getHour(), ChronoUnit.HOURS)
                                 .plus(runningTime.getMinute(), ChronoUnit.MINUTES)
                                 .plus(runningTime.getSecond(), ChronoUnit.SECONDS);
                                 
    }
    
    
}
