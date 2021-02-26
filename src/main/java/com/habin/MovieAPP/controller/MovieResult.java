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
public class MovieResult {

    private String DOCID;
    private String movieId;
    private String movieSeq;
    private String title;
    private String titleEng;
    private String titleOrg;
    private String titleEtc;
    private String prodYear;
    private DirectorA directors;
    private ActorA actors;
    private String nation;
    private String company;
    private PlotA plots;
    private String runtime;
    private String rating;
    private String genre;
    private String kmdbUrl;

}
