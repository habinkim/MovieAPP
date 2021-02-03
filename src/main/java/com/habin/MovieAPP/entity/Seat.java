package com.habin.MovieAPP.entity;

import com.habin.MovieAPP.entity.enums.SeatRow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    private String seatNo;

    private Integer seatCol;

    private SeatRow seatRow;

    public void setSeatNo() {
        seatNo = new StringBuilder()
        .append(seatRow.toString())
        .append(String.valueOf(seatCol))
        .toString();
    }

}
