package com.southwind.entity;

import lombok.Data;

import java.security.Timestamp;

@Data
public class Movie {
    private long id;
    private Timestamp registerDate;
    private String movieName;
    private String picture;
    private String sort;

    private String director;
    private String role;
    private Timestamp releaseDate;
    private String length;
    private String preview;
    private String description;
    private Timestamp session;
    private String no;
    private String cinemaAccount;
    private String cinemaName;
    private Float price;
    private long numberOfSeat;
    private long numberOfAvail;
    private String selected;
}


