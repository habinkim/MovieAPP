package com.habin.MovieAPP.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.habin.MovieAPP.entity.enums.Nationality;
import com.habin.MovieAPP.entity.history.BaseEntity;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 감독 정보 Entity 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 11
* @Time : 17:05:47
*/

@SuperBuilder(toBuilder = true)
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_DIRECTOR")
public class Director extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
    private Long directorId; // 아이디

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Nationality nationality; // 국적

    @Column(nullable = false, length = 40)
    private String directorNm; // 감독명

}
