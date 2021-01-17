package com.habin.MovieAPP.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.habin.MovieAPP.entity.history.BaseEntity;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
* @author : 김하빈(hbkim@bpnsolution.com)
* * @description : 매점 메뉴 Entity 클래스
* ! 
* ? 
* TODO : 
* @Date : 2021. 01. 17
* @Time : 20:33:29
*/

@SuperBuilder(toBuilder = true)
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_CAFE_MENU")
public class CafeMenu extends BaseEntity {

    @Id
    private Long menuId;

    @Column(nullable = false, length = 50)
    private String menuNm;

    @Column(nullable = false, length = 10)
    private Long menuPrice;

    @JsonManagedReference // 양방향 관계에서의 JSON 부참조 객체 파싱
    @OneToMany(mappedBy = "eventId", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = true)
    private List<Event> event;
    
}
