package com.habin.MovieAPP.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResult {

    public String DOCID;
    public String movieId;
    public String movieSeq;
    public String title;
    public String titleEng;
    public String titleOrg;
    public String titleEtc;
    public String prodYear;
    public DirectorA directors;
    public ActorA actors;
    public String nation;
    public String company;
    public PlotA plots;
    public String runtime;
    public String rating;
    public String genre;
    public String kmdbUrl;

}
