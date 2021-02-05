package com.habin.MovieAPP.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.habin.MovieAPP.entity.enums.Hall;
import com.habin.MovieAPP.entity.history.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Entity
@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_THEATER")
public class Theater extends BaseEntity {

    @Id
    @Column(length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thId;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Hall hall;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="VIEW_SEAT_INFO")
    @Builder.Default
    @Embedded
    private List<Seat> seatInfo = new ArrayList<>();

}