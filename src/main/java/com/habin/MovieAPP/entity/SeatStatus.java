package com.habin.MovieAPP.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.habin.MovieAPP.entity.enums.Yn;
import com.habin.MovieAPP.entity.history.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_SEAT_STATUS")
public class SeatStatus extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatStatusId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Yn reserveYn; // 예매 여부

    @Column(nullable = false)
    @Embedded
    private Seat seat;

    @ManyToOne
    @JoinColumn(nullable = true, name = "screening", referencedColumnName = "scrId")
    private Screening screening;
    
}
