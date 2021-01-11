package com.habin.MovieAPP.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.habin.MovieAPP.entity.enums.Cast;
import com.habin.MovieAPP.entity.history.BaseEntity;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 영화 출연진 정보 매핑 Entity 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 11
* @Time : 16:52:06
*/
@SuperBuilder(toBuilder = true)
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_MOVIE_CAST")
public class MovieCast extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
    private Long movieCastId;

    @JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY) // N : 1 관계 (셀프 조인)
    private Movie movie; // 영화

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Actor actor; // 배우

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Cast castType; // 관람등급

}
