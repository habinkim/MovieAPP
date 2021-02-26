package com.habin.MovieAPP.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectorB {

    private String directorNm;
    private String directorEnNm;
    private String directorId;

}
