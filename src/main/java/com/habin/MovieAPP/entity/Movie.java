package com.habin.MovieAPP.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.habin.MovieAPP.entity.enums.Genre;
import com.habin.MovieAPP.entity.enums.Nationality;
import com.habin.MovieAPP.entity.enums.Rate;
import com.habin.MovieAPP.entity.history.BaseEntity;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 영화 Entity 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 11
* @Time : 16:46:37
*/

@SuperBuilder(toBuilder = true)
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_MOVIE")
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
    private Long movieId; // 아이디

    @Column(length = 100, nullable = false)
    private String movieNm; // 제목

    @ManyToOne(fetch = FetchType.LAZY) // N : 1 외래키
	@JoinColumn(nullable = false, name = "directorNm", referencedColumnName = "directorNm")
    private Director director; // 감독

    @JsonManagedReference
    @OneToMany(mappedBy = "movieCastId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieCast> movieCast; // 출연진

    @Column(length = 1000, nullable = true)
    private String synopsis; // 설명

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Nationality nationality; // 국적

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable
	@Enumerated(EnumType.STRING)
    private List<Genre> genre; // 장르

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rate movieRate; // 관람등급

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
	@DateTimeFormat(pattern = "HH:mm:ss")
    @Column(nullable = false)
    private LocalTime runningTime; // 상영시간

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
    private LocalDate releaseDate; // 개봉일
    
}
