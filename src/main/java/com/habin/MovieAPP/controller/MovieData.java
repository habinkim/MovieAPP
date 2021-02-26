package com.habin.MovieAPP.controller;

import java.util.List;

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
public class MovieData {

    private String CollName;
    private Long TotalCount;
    private Long Count;
    private List<MovieResult> Result;

}
