package com.southwind.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Cinema {
    private long id;
    private Timestamp registerDate;
    private String username;
    private String password;
    private String cinemaName;
    private String picture;
    private long amount;
    private String telephone;
    private String address;
    private String description;
}

