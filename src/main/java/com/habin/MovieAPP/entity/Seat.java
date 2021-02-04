package com.habin.MovieAPP.entity;

import javax.persistence.Embeddable;

import com.habin.MovieAPP.entity.enums.SeatRow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Seat {

    private String seatNo;

    private Integer seatCol;

    private SeatRow seatRow;

    public void setSeatNo() {
        this.seatNo = new StringBuilder()
        .append(seatRow.toString())
        .append(String.valueOf(seatCol))
        .toString();
    }

}