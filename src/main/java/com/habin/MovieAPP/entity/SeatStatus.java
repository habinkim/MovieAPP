package com.habin.MovieAPP.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.habin.MovieAPP.entity.enums.Yn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SeatStatus {

    @Column(nullable = false, length = 3)
    @Enumerated(EnumType.STRING)
    private Yn reserveYn; // 예매 여부

    @Column(nullable = false)
    @Embedded
    private Seat seat;
    
}
