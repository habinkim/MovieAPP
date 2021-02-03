package com.habin.MovieAPP.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.habin.MovieAPP.entity.enums.Hall;
import com.habin.MovieAPP.entity.history.BaseEntity;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 상영관 Entity 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 02. 03
* @Time : 13:00:36
*/

@SuperBuilder(toBuilder = true)
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_THEATER")
public class Theater extends BaseEntity {

	@Id
    @Enumerated(EnumType.STRING)
    private Hall hall;

    @ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	@Column(nullable = false)
    private List<Seat> seat = new ArrayList<>();
    
}
