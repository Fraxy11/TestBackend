package com.mysql.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "imdb")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String Title;

    @Column(name = "rating")
    private Float Rating;

    @Column(name = "year")
    private String Year;

    @Column(name = "Month")
    private String Month;

    @Column(name = "certificate")
    private String Certificate;

    @Column(name = "runtime")
    private String Runtime;

    @Column(name = "director")
    private String Director;

    @Column(name = "stars")
    private String Stars;

    @Column(name = "genre")
    private String Genre;

    @Column(name = "filming_location")
    private String FilmingLocation;

    @Column(name = "budget")
    private int Budget;

    @Column(name = "income")
    private int Income;

    @Column(name = "country_of_origin")
    private String CountryOfOrigin;

}
