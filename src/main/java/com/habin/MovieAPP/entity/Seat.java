package com.habin.MovieAPP.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.habin.MovieAPP.entity.enums.SeatRow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Seat {

    @Column(nullable = false, length = 3)
    private String seatNo;

    @Column(nullable = false, length = 2)
    private Integer seatCol;

    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private SeatRow seatRow;

    public void buildSeatNo() {
        this.seatNo = new StringBuilder()
        .append(seatRow.toString())
        .append(String.valueOf(seatCol))
        .toString();
    }

}